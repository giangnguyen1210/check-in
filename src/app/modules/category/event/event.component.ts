import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { CommonService } from 'src/app/core/services/common.service';
import { EventService } from 'src/app/core/services/event.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit{
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
    const formattedDate = `${formatTwoDigits(day)}/${formatTwoDigits(month)}`;
    
    return formattedDate;
  }

  
  
  
  //constructor
  constructor(private eventService: EventService, private commonService: CommonService, private router: Router, private fb: FormBuilder) {}
 
  formSearch: any;
  formEvent!: FormGroup;
  formEventEdit!: FormGroup;
  statusList: any;
  stt: any;
  eventList:any;
  showModal: boolean=false;
  showModalEdit: boolean = false;

  //ng oninit
  ngOnInit(): void {
    this.initFormSearch();
    this.getEventService();
  }
  formatTime(time: any) {
    return moment(time, 'HH:mm:ss').format('HH:mm');
  }
  
  //form search
  initFormSearch(){
    this.formSearch = this.fb.group({
      name: [],
      startDate: [],
      startTime: [],
      endDate: [],
      endTime: [],
    })
  }
  search(){
    this.getEventService();
  }
  resetForm(){
    this.formSearch.reset();
    this.getEventService();
  }

  initFormEvent(){
    this.formEvent = this.fb.group({
      name: ['',Validators.required],
      code: [],
      note: [],
      startDate: [],
      startTime: [],
      endDate: [],
      endTime: []
    })
  }

  // edit
  editEvent(event:any){
    console.log(event);
    this.showModalEdit = true;
    this.initFormEvent();
    console.log(event);
    this.formEvent.patchValue(event);
    const startTime = this.formatTime(this.formEvent.get('startTime')?.value);
    const endTime = this.formatTime(this.formEvent.get('endTime')?.value);
    this.formEvent.patchValue({
      startTime: startTime,
      endTime: endTime
    });


  }
  
  updateEvent(){
    
    const json = {
      ...this.formEvent.value,
    }
    console.log(this.formEvent);
    if(this.formEvent.valid){
     this.eventService.updateEvent(json).subscribe(
      (data)=> {
        console.log(data);
        this.showModalEdit = false;
        this.getEventService();
      }
     )
    }
  }

  cancelEdit(){
    this.showModalEdit=false;
  }
  

  // create
  createEvent(){
    this.showModal=true;
    this.initFormEvent();
  }
  submitEvent(){
    const json = {
      ...this.formEvent.value,
    };
    console.log(json);
    console.log(this.formEvent);
    if(this.formEvent.valid){
      this.eventService.createEvent(json).subscribe(
        (data)=>{
          console.log(data);
          if(data.errorCode==="OK"){
            this.showModal = false;
            this.getEventService();
          }
        }
      )
    }
  }
  cancel(){
    this.showModal = false;
  }

  resetFormEvent(){
    this.formEvent.reset();
  }
  //get service
  
  getEventService(){

    const json = {
      name: this.formSearch.get('name').value,
      startDate: this.formSearch.get('startDate').value,
      startTime: this.formSearch.get('startTime').value,
      endDate: this.formSearch.get('endDate').value,
      endTime: this.formSearch.get('endTime').value,
    };
    console.log(json);
    this.eventService.getEventList(json).subscribe(
      (data) => {
        this.eventList = data;;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  
  get f() {
    return this.formEvent.controls;
  }

  
 
  
}
