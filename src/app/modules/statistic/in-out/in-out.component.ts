import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CommonService } from 'src/app/core/services/common.service';
import { DepartmentService } from 'src/app/core/services/department.service';
import { CheckinCheckoutService } from 'src/app/core/services/history-checkin-checkout.service';
import { UnitService } from 'src/app/core/services/unit.service';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { BaseResponse } from 'src/app/core/models/response';
import { createFileType, downLoadFile } from 'src/app/core/utils/export.util';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import {MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import { MY_FORMATS } from 'src/app/core/utils/format-date.util';
import { Moment } from 'moment';
import { MatDatepicker } from '@angular/material/datepicker';
@Component({
  selector: 'app-in-out',
  templateUrl: './in-out.component.html',
  styleUrls: ['./in-out.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateAdapter` can be automatically provided by importing `MomentDateModule` in your
    // application's root module. We provide it at the component level here, due to limitations of
    // our example generation script.
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
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
  constructor(private checkinCheckoutService: CheckinCheckoutService, 
    private commonService: CommonService, 
    private unitService: UnitService, 
    private departmentService: DepartmentService, 
    private fb: FormBuilder,
    private toastr: ToastrService) {}
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
    this.getCheckinOutService();
    this.initFromSearchDate();
  }
  initFromSearchDate(){
    this.formDate = this.fb.group({
      date: ['']
    })
  }
  setMonthAndYear(normalizedMonthAndYear: Moment, datepicker: MatDatepicker<Moment>) {
    const ctrlValue = this.formSearch.get('month').value ?? moment();
    ctrlValue.month(normalizedMonthAndYear.month());
    ctrlValue.year(normalizedMonthAndYear.year());
    this.formSearch.get('month').setValue(ctrlValue);
    datepicker.close();
  }

  //form search
  initFormSearch(){
    this.formSearch = this.fb.group({
      keyword: [''],
      statusId: [],
      unitCode: [],
      departmentCode: [],
      date: [],
      month :  []
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
    this.employeeCode  = '';
    this.getCheckinOutDetailService();
  }
  getData(){
    this.getCheckinOutDetailService();
  }


  exportDetail(){
    const json={
      employeeCode: this.employeeCode,
      date: this.formDate.get('date')?.value
    }
    this.checkinCheckoutService.exportHistoryCheckinDetail(json).subscribe(
      (data: any) => {
          downLoadFile(
            data,
            createFileType('pdf'),
            'History_checkin_detail_' + new Date().toDateString()
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
      date: this.formSearch.get('date').value,
      departmentCode: this.formSearch.get('departmentCode').value,
      unitCode: this.formSearch.get('unitCode').value,
      month: this.formSearch.get('month').value
    };
        this.checkinCheckoutService.exportHistoryCheckin(json).subscribe(
          (data: any) => {
            // Handle file path response
            downLoadFile(
              data,
              createFileType('pdf'),
              'History_checkin_' + new Date().toDateString()
            );
          }
          
        );
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
      month: this.formSearch.get('month').value,
      page: this.pageNumber,
      limit: this.pageSize,
    };
    this.checkinCheckoutService.getCheckinCheckoutList(json).subscribe(
      (data) => {
        this.checkinCheckoutList = data;
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
    this.getCheckinOutService();
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
      this.getCheckinOutService();
      
    }
  }

  prevPage(): void {
    if (this.pageNumber > 1) {
      this.pageNumber--;
      this.getCheckinOutService();
    }
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
