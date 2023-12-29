import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BaseResponse } from 'src/app/core/models/response';
import { CommonService } from 'src/app/core/services/common.service';
import { DepartmentService } from 'src/app/core/services/department.service';
import { UnitService } from 'src/app/core/services/unit.service';
import { UserService } from 'src/app/core/services/users.service';
import { formatDate } from 'src/app/core/utils/format.util';
@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {
  // format date
  formatDate(date: any) {
    if (date == null) {
      return null;
    }
    const serverDate = new Date(date);

    // Hàm để định dạng số để luôn có 2 chữ số (vd: 01, 02, ..., 12)
    const formatTwoDigits = (number: number): string => (number < 10 ? `0${number}` : `${number}`);

    // Lấy ngày, tháng, năm từ đối tượng ngày
    const day = serverDate.getDate();
    const month = serverDate.getMonth() + 1; // Tháng bắt đầu từ 0
    const year = serverDate.getFullYear();

    // Định dạng ngày theo "dd/MM/yyyy"
    const formattedDate = `${formatTwoDigits(day)}/${formatTwoDigits(month)}/${year}`;

    return formattedDate;
  }
  //constructor
  constructor(private userService: UserService,
    private commonService: CommonService,
    private unitService: UnitService,
    private departmentService: DepartmentService,
    private router: Router,
    private fb: FormBuilder,
    private toastr: ToastrService) { }
  nameImage: any;
  base64CccdImage: string = '';
  base64FaceImage: string = '';
  formSearch: any;
  formUser: any;
  isSubmit = false;
  userList: any;
  roleList: any;
  statusList: any;
  stt: any;
  unitList: any;
  genderList: any;
  status: any;
  departmentList: any;
  test: any;
  positionList: any;

  jobTitleList: any;
  showModal: boolean = false;
  showModalEdit: boolean = false;
  selectedUserCodes: string[] = [];
  page: any = 1;
  limit: any = 10;
  totalPages: number = 0;
  totalRecords: any;
  response!: BaseResponse;
  //ng oninit
  ngOnInit(): void {
    this.initFormSearch();
    this.getUserService();
    this.getGenderService();
    this.getStatusService();
    this.getJobTitleService();
    this.getDepartmentService();
    this.getUnitService();
    this.getPositionService();
  }

  onCheckboxChange(user: any) {
    if (user.checked) {
      this.selectedUserCodes.push(user.employeeCode);
    }
    else {
      this.selectedUserCodes = this.selectedUserCodes.filter(code => code !== user.employeeCode);
      user.checked = false;
      console.log(this.selectedUserCodes.filter(code => code != user.employeeCode));
    }
    console.log(user.checked);

    // console.log('Selected User Codes:', this.selectedUserCodes);
  }
  //form search
  initFormSearch() {
    this.formSearch = this.fb.group({
      keyword: [''],
      statusId: [],
      genderId: [],
      departmentCode: [],
      jobTitleCode: []
    })
  }
  search() {
    this.getUserService();
  }
  resetForm() {
    this.formSearch.reset();
    this.getUserService();
  }
  addCccdImage(event: any) {

    if (event.target.files.length > 0) {
      const file = event?.target?.files?.[0];


      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.base64CccdImage = e.target.result;
      };

      reader.readAsDataURL(file);
    }
  }

  addFaceImage(event: any) {
    if (event.target.files.length > 0) {
      var reader = new FileReader();
      reader.onload = (event: any) => {
        this.base64FaceImage = event.target.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  //form user
  initFormUser() {
    this.formUser = this.fb.group({
      employeeCode: [''],
      fullname: ['', Validators.required],
      dob: ['', Validators.required],
      genderId: ['', Validators.required],
      departmentCode: ['', Validators.required],
      jobTitleCode: ['', Validators.required],
      unitCode: ['', Validators.required],
      email: ['', Validators.required],
      positionCode: ['', Validators.required],
      faceImage: [],
      cccdImage: [],
      cccd: [],
      phone: []
    })
  }

  // edit
  editUser(user: any) {
    console.log(user);
    this.initFormUser();
    this.showModalEdit = true;
    this.formUser.patchValue(user);
    console.log(this.formUser);
  }
  updateUser() {
    console.log(this.formUser);
    const json = {
      ...this.formUser.value,
      faceImage: this.base64FaceImage,
      cccdImage: this.base64CccdImage,
    }
    console.log(json);
    this.userService.updateUser(json).subscribe(
      (response) => {
        console.log(response);
        this.response = response;
        if(this.response.errorCode==="OK"){
          this.showModalEdit = false;
          this.getUserService();
          this.toastr.success(this.response.errorDesc);

        }else{
          this.toastr.error(this.response.errorDesc);
        }
      }
    )
  }

  cancelEdit() {
    this.showModalEdit = false;
  }


  // create
  createUser() {
    this.showModal = true;
    this.initFormUser();
  }
  submitUser() {
    // console.log(this.formUser);
    const json = {
      ...this.formUser.value,
      faceImage: this.base64FaceImage,
      cccdImage: this.base64CccdImage,
    };
    console.log(json);
    this.userService.createUser(json).subscribe(
      (response) => {
        this.response = response;
        if(this.response.errorCode==="OK"){
          this.getUserService();
          this.toastr.success(this.response.errorDesc);
          this.showModal = false;

        }else{
          this.toastr.error(this.response.errorDesc)
        }
      }
    )
  }
  cancel() {
    this.showModal = false;
  }

  resetFormUser() {
    this.formUser.reset();
  }

  // activate and deactivate user
  activate() {
    const json = {
      employeeCodes: this.selectedUserCodes,
      statusId: 1
    }
    // console.log(this.selectedUserCodes);
    this.selectedUserCodes = [];
    this.userService.activateUser(json).subscribe(
      (response) => {
        this.response = response;
        if(this.response.errorCode==="OK"){
          this.getUserService();
          this.toastr.success(this.response.errorDesc);

        }else{
          this.toastr.error(this.response.errorDesc)
        }
      },
      (error) => {
        console.error('Error updating users', error);
        // Xử lý lỗi nếu cần
      }
    );
  }
  deactivate() {
    const json = {
      employeeCodes: this.selectedUserCodes,
      statusId: 2
    }
    this.selectedUserCodes = [];
    this.userService.deActivateUser(json).subscribe(
      (response) => {
        this.response = response;
        console.log(this.response);
        if(this.response.errorCode==="OK"){
          this.getUserService();
          this.toastr.success(this.response.errorDesc);

        }else{
          this.toastr.error(this.response.errorDesc)
        }
      },
      (error) => {
        console.error('Error updating users', error);
        // Xử lý lỗi nếu cần
      }
    );
  }
  //get service
  getDepartmentService() {
    this.departmentService.getDepartmentList({}).subscribe(
      (data) => {
        this.departmentList = data;
        console.log(this.test);
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getGenderService() {
    this.commonService.getGenderList().subscribe(
      (data) => {
        this.genderList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

  getJobTitleService() {
    this.commonService.getJobTitleList().subscribe(
      (data) => {
        this.jobTitleList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getStatusService() {
    this.commonService.getStatusList().subscribe(
      (data) => {
        this.statusList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getUnitService() {
    this.unitService.getUnitList({}).subscribe(
      (data) => {
        this.unitList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getPositionService() {
    this.commonService.getPositionList().subscribe(
      (data) => {
        this.positionList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

  getUserService() {
    const json = {
      keyword: this.formSearch.get('keyword').value,
      statusId: this.formSearch.get('statusId').value,
      genderId: this.formSearch.get('genderId').value,
      departmentCode: this.formSearch.get('departmentCode').value,
      jobTitleCode: this.formSearch.get('jobTitleCode').value,
      page: this.page,
      limit: this.limit
    };
    console.log(json);
    this.userService.getUserList(json).subscribe(
      (data) => {
        this.response = data;
        if (data && data.data) {

          this.userList = data;
          console.log(data.totalRecords);
          // Kiểm tra nếu có trang tiếp theo
          this.totalPages = data.totalRecords;
        }
        console.log(this.userList);
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

  nextPage(): void {
    if (this.page < this.totalPages) {
      this.page++;
      this.getUserService();
    }
  }

  prevPage(): void {
    if (this.page > 1) {
      this.page--;
      this.getUserService();
    }
  }

  get f() {
    return this.formUser.controls;
  }

}
