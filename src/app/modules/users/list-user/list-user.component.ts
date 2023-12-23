import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonService } from 'src/app/core/services/common.service';
import { DepartmentService } from 'src/app/core/services/department.service';
import { UserService } from 'src/app/core/services/users.service';
import { formatDate } from 'src/app/core/utils/format.util';
@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit{
  // format date
  formatDate(date: any){
    if(date==null){
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
  constructor(private userService: UserService, private commonService: CommonService, private departmentService: DepartmentService, private router: Router, private fb: FormBuilder) {}
  nameImage: any;
  base64CccdImage: string ='';
  base64FaceImage: string ='';
  formSearch: any;
  formUser: any;
  isSubmit = false;
  userList: any;
  roleList: any;
  statusList: any;
  stt: any;
  unitList:any;
  genderList: any;
  departmentList: any;
  test: any;
  positionList: any;
  jobTitleList: any;
  showModal: boolean=false;
  showModalEdit: boolean = false;

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

  //form search
  initFormSearch(){
    this.formSearch = this.fb.group({
      keyword: [''],
      statusId: [],
      genderId: [],
      departmentCode: [],
      jobTitleCode: []
    })
  }
  search(){
    this.getUserService();
  }
  resetForm(){
    this.formSearch.reset();
    this.getUserService();
  }
  addCccdImage(event: any) {
    if (event.target.files && event.target.files[0]) {
        this.nameImage = event.target.files[0].name;
        var reader = new FileReader();
        reader.onload = (event: any) => {
            this.base64CccdImage = event.target.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    }
  }
  addFaceImage(event: any) {
    if (event.target.files && event.target.files[0]) {
        this.nameImage = event.target.files[0].name;
        var reader = new FileReader();
        reader.onload = (event: any) => {
            this.base64FaceImage = event.target.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    }
  }

  //form user
  initFormUser(){
    this.formUser = this.fb.group({
      // employeeCode: [''],
      fullname: ['',Validators.required],
      dob: ['', Validators.required],
      genderId: ['',Validators.required],
      departmentCode: ['',Validators.required],
      jobTitleCode: ['',Validators.required],
      unitCode: ['',Validators.required],
      email: ['',Validators.required],
      positionCode: ['',Validators.required],
      faceImage: [''],
      cccd: [],
      cccdImage: [''],
      phone: []
    })
  }

  // edit
  editUser(user:any){
    this.getJobTitleService();
    this.getDepartmentService();
    this.getUnitService();
    this.getPositionService();
    this.showModalEdit = true;
    console.log(this.showModalEdit);
    this.initFormUser();
    this.formUser.patchValue(user);
    console.log(this.formUser);
    // console.log(this.formUser.patchValue(user));
  }
  updateUser(){
    const json = {
      ...this.formUser.value,
      faceImage: this.base64FaceImage,
      cccdImage: this.base64CccdImage,
    }
    console.log(json);
    if(this.formUser.valid){
      this.userService.updateUser(json).subscribe(
        (data)=>{
          if(data.errorCode==="OK"){
            this.showModalEdit = false;
            this.getUserService();
          }
        }
      )
    }
  }

  cancelEdit(){
    this.showModalEdit=false;
  }
  

  // create
  createUser(){
    this.showModal=true;
    this.initFormUser();
  }
  submitUser(){
    // console.log(this.formUser);
    const json = {
      ...this.formUser.value,
      faceImage: this.base64FaceImage,
      cccdImage: this.base64CccdImage,
    };
    console.log(this.base64CccdImage);
    console.log(this.base64FaceImage);
    console.log(json);
    if(this.formUser.valid){
      this.userService.createUser(json).subscribe(
        (data)=>{
          console.log(data);
          if(data.errorCode==="OK"){
            this.showModal = false;
            this.getUserService();
          }
        }
      )
    }
  }
  cancel(){
    this.showModal = false;
  }

  resetFormUser(){
    this.formUser.reset();
  }
  //get service
  getDepartmentService(){
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
  getGenderService(){
    this.commonService.getGenderList().subscribe(
      (data) => {
        this.genderList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

  getJobTitleService(){
    this.commonService.getJobTitleList().subscribe(
      (data) => {
        this.jobTitleList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getStatusService(){
    this.commonService.getStatusList().subscribe(
      (data) => {
        this.statusList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getUnitService(){
    this.commonService.getUnitList().subscribe(
      (data) => {
        this.unitList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getPositionService(){
    this.commonService.getPositionList().subscribe(
      (data) => {
        this.positionList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

  getUserService(){
    const json = {
      keyword: this.formSearch.get('keyword').value,
      statusId: this.formSearch.get('statusId').value,
      genderId: this.formSearch.get('genderId').value,
      departmentCode: this.formSearch.get('departmentCode').value,
      jobTitleCode: this.formSearch.get('jobTitleCode').value,
    };
    console.log(json);
    this.userService.getUserList(json).subscribe(
      (data) => {
        this.userList = data;
        console.log(this.userList);
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  get f() {
    return this.formUser.controls;
  }
  
}
