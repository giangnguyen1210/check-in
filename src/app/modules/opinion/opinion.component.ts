import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { BaseResponse } from 'src/app/core/models/response';
import { DepartmentService } from 'src/app/core/services/department.service';
import { OpinionService } from 'src/app/core/services/opinion.service';
import { UserService } from 'src/app/core/services/users.service';
@Component({
  selector: 'app-opinion',
  templateUrl: './opinion.component.html',
  styleUrls: ['./opinion.component.css']
})
export class OpinionComponent implements OnInit {
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
  constructor(private opinionService: OpinionService,
    private departmentService: DepartmentService,
    private fb: FormBuilder,
    private toastr: ToastrService) { }
  formSearch: any;
  formOpinion: any;
  opinionList: any;
  response!: BaseResponse;
  departmentList: any;
  showModalResponse: boolean = false;
  opinionName: string = '';
  //ng oninit
  ngOnInit(): void {
    this.initFormSearch();
    this.getOpinionService();
    this.getDepartmentService();
  }

  //form search
  initFormSearch() {
    this.formSearch = this.fb.group({
      code: [''],
      name: [],
      fullname: [],
      departmentCode: [],
      createdDate: []
    })
  }

  initFormOpinion(){
    this.formOpinion = this.fb.group({
      name:[''],
      code: [''],
      content: [''],
      response: ['']
    })
  }
  search() {
    this.getOpinionService();
  }
  resetForm() {
    this.formSearch.reset();
    this.getOpinionService();
  }

  cancelResponse(){
    this.showModalResponse = false;
  }
  
  responseOpinion(opinion: any){
    this.showModalResponse = true;
    this.initFormOpinion();
    this.formOpinion.patchValue(opinion);
    this.opinionName = this.formOpinion.get('name')?.value;
    console.log(this.formOpinion);
  }
  updateResponse(){
    console.log(this.formOpinion);
    const json={
      ...this.formOpinion.value
    }
    console.log(json);
    this.opinionService.responseOpinion(json).subscribe(
      (data) => {
        this.response = data;
        console.log(data);
        if (this.response.errorCode==='OK') {
          this.showModalResponse = false;
          this.toastr.success(this.response.errorDesc);
          this.getOpinionService();
        }
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getOpinionService() {
    const json = {
      code: this.formSearch.get('code')?.value,
      name: this.formSearch.get('name')?.value,
      fullname: this.formSearch.get('fullname')?.value,
      createdDate: this.formSearch.get('createdDate')?.value,
      departmentCode: this.formSearch.get('departmentCode')?.value,
     
    };
    console.log(json);
    this.opinionService.getOpinionList(json).subscribe(
      (data) => {
        this.response = data;
        console.log(data);
        if (data && data.data) {

          this.opinionList = data;
          console.log(data.totalRecords);
          // Kiểm tra nếu có trang tiếp theo
        }
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

  getDepartmentService() {
    this.departmentService.getDepartmentList({}).subscribe(
      (data) => {
        this.departmentList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

}
