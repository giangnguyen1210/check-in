import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CommonService } from 'src/app/core/services/common.service';
import { DepartmentService } from 'src/app/core/services/department.service';
import { UnitService } from 'src/app/core/services/unit.service';
import * as moment from 'moment';
import { HistoryLoginService } from 'src/app/core/services/history-login.service';
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
  constructor(private historyLoginService: HistoryLoginService, private commonService: CommonService, private unitService: UnitService, private departmentService: DepartmentService, private fb: FormBuilder) {}
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
    this.initFormSearch();
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
    };
    this.historyLoginService.getHistoryLoginList(json).subscribe(
      (data) => {
        this.historyLoginList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
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
