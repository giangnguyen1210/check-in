import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonService } from 'src/app/core/services/common.service';
import { DepartmentService } from 'src/app/core/services/department.service';
import { CheckinCheckoutService } from 'src/app/core/services/history-checkin-checkout.service';
import { UnitService } from 'src/app/core/services/unit.service';
import * as moment from 'moment';
@Component({
  selector: 'app-in-out',
  templateUrl: './in-out.component.html',
  styleUrls: ['./in-out.component.css']
})
export class InOutComponent implements OnInit{
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
  constructor(private checkinCheckoutService: CheckinCheckoutService, private commonService: CommonService, private unitService: UnitService, private departmentService: DepartmentService, private fb: FormBuilder) {}
  formSearch: any;
  isSubmit = false;
  checkinCheckoutList: any;
  roleList: any;
  statusList: any;
  stt: any;
  unitList:any;
  departmentList: any;
  showModalCheckinDetail: boolean = false;
  formDate: any;
  checkinCheckoutDetailList: any;
  employeeCode: any;
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
    this.getCheckinOutService();
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
      date: []
    })
  }
  search(){
    this.getCheckinOutService();
  }
  resetForm(){
    this.formSearch.reset();
    this.getCheckinOutService();
  }
 
  showDetail(employeeCode: any){
    this.showModalCheckinDetail = true;
    this.employeeCode = employeeCode;
    this.getCheckinOutService();
  }
  cancelShow(){
    this.showModalCheckinDetail = false;
  }
  getData(){
    this.getCheckinOutDetailService();
  }
  exportDetail(){

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
  
  getCheckinOutService(){
    const json = {
      keyword: this.formSearch.get('keyword').value,
      statusId: this.formSearch.get('statusId').value,
      date: this.formSearch.get('date').value,
      departmentCode: this.formSearch.get('departmentCode').value,
      unitCode: this.formSearch.get('unitCode').value,
    };
    this.checkinCheckoutService.getCheckinCheckoutList(json).subscribe(
      (data) => {
        console.log(data.totalRecords);
        this.checkinCheckoutList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

  getCheckinOutDetailService(){
    const json={
      employeeCode: this.employeeCode,
      date: this.formDate.get('date')?.value
    }
    this.checkinCheckoutService.getCheckinCheckoutDetailList(json).subscribe(
      (data) => {
        console.log(data.totalRecords);
        this.checkinCheckoutDetailList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

}
