import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CommonService } from 'src/app/core/services/common.service';
import { DepartmentService } from 'src/app/core/services/department.service';
import { UnitService } from 'src/app/core/services/unit.service';
import * as moment from 'moment';
import { HistoryLoginService } from 'src/app/core/services/history-login.service';
import { BaseResponse } from 'src/app/core/models/response';
import { ToastrService } from 'ngx-toastr';
import { createFileType, downLoadFile } from 'src/app/core/utils/export.util';
@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit{
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
  constructor(private historyLoginService: HistoryLoginService, 
    private commonService: CommonService, 
    private unitService: UnitService, 
    private departmentService: DepartmentService, 
    private fb: FormBuilder,
    private toastr: ToastrService) {}
  formSearch: any;
  isSubmit = false;
  historyLoginList: any;
  historyLoginDetail: any;
  roleList: any;
  statusList: any;
  stt: any;
  unitList:any;
  departmentList: any;
  genderList: any;
  showModalLoginDetail: boolean = false;
  employeeCode: any;
  formDate: any;
  response!: BaseResponse;
  totalSize = 1;
  pageSize = 5;
  pageNumber = 1;
  page:any;
  formatTime(time: any) {
    if(time==null){
      return '';
    }
    return moment(time, 'HH:mm:ss').format('HH:mm');
  }
  //ng oninit
  ngOnInit(): void {
    this.initFormSearch();
    this.getStatusService();
    this.getDepartmentService();
    this.getUnitService();
    this.getHistoryListService();
    this.getGenderService();
    this.initFromSearchDate();
  }

  initFromSearchDate(){
    this.formDate = this.fb.group({
      date: ['']
    })
  }
  //form search
  initFormSearch(){
    this.formSearch = this.fb.group({
      keyword: [''],
      statusId: [],
      unitCode: [],
      departmentCode: [],
      genderId: []
    })
  }
  search(){
    this.getHistoryListService();
  }
  resetForm(){
    this.formSearch.reset();
    this.getHistoryListService();
  }
 
  showDetail(employeeCode: any){
    this.showModalLoginDetail = true;
    this.employeeCode = employeeCode;
    this.getHistoryDetailService()
  }
  cancelShow(){
    this.showModalLoginDetail=false;
  }
  //get service
  getDepartmentService(){
    this.departmentService.getDepartmentList({}).subscribe(
      (data) => {
        this.departmentList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getData(){
    this.getHistoryDetailService();
  }

  exportDetail(){
    const json={
      employeeCode: this.employeeCode,
      date: this.formDate.get('date')?.value
    }
    this.historyLoginService.exportHistoryLoginDetail(json).subscribe(
      (data: any) => {
          downLoadFile(
            data,
            createFileType('pdf'),
            'History_login_' + new Date().toDateString()
          );
      },
      error => {
        console.error('Error downloading file:', error);
      }
    );
  }

  exportFile(): void {
    const json = {
          keyword: this.formSearch.get('keyword').value,
          statusId: this.formSearch.get('statusId').value,
          genderId: this.formSearch.get('genderId').value,
          departmentCode: this.formSearch.get('departmentCode').value,
          unitCode: this.formSearch.get('unitCode').value,
        };
        this.historyLoginService.exportHistoryLogin(json).subscribe(
          (data: any) => {
            // Handle file path response
            downLoadFile(
              data,
              createFileType('pdf'),
              'History_login_' + new Date().toDateString()
            );
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
    this.unitService.getUnitList({}).subscribe(
      (data) => {
        this.unitList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  
  getHistoryListService(){
    const json = {
      keyword: this.formSearch.get('keyword').value,
      statusId: this.formSearch.get('statusId').value,
      genderId: this.formSearch.get('genderId').value,
      departmentCode: this.formSearch.get('departmentCode').value,
      unitCode: this.formSearch.get('unitCode').value,
      page: this.pageNumber,
      limit: this.pageSize,
    };
    this.historyLoginService.getHistoryLoginList(json).subscribe(
      (data) => {
        this.historyLoginList = data;
        this.totalSize = data.totalRecords;
        this.page = Math.round(this.totalSize / this.pageSize);
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  currentPage(page: number) {
    this.pageNumber = page;
    this.getHistoryListService();
  }

  range(end: number): number[] {
    const result = [];
    for (let i = 1; i <= end; i++) {
      result.push(i);
    }
    return result;
  }

  nextPage(): void {
    if (this.pageNumber < this.totalSize) {
      console.log(this.totalSize);
      this.pageNumber++;
      this.getHistoryListService();
      
    }
  }

  prevPage(): void {
    if (this.pageNumber > 1) {
      this.pageNumber--;
      this.getHistoryListService();
    }
  }
  getHistoryDetailService(){
    const json={
      employeeCode: this.employeeCode,
      date: this.formDate.get('date')?.value
    }
    this.historyLoginService.getHistoryLoginDetail(json).subscribe(
      (data) => {
        this.historyLoginDetail = data;
        console.log(data);
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
}
