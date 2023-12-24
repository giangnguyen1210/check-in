import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonService } from 'src/app/core/services/common.service';
import { HolidayService } from 'src/app/core/services/holiday.service';
@Component({
  selector: 'app-holiday',
  templateUrl: './holiday.component.html',
  styleUrls: ['./holiday.component.css']
})
export class HolidayComponent implements OnInit{
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
  constructor(private holidayService: HolidayService, private commonService: CommonService, private router: Router, private fb: FormBuilder) {}
  nameImage: any;
  formSearch: any;
  formHoliday!: FormGroup;
  formHolidayEdit!: FormGroup;
  isSubmit = false;
  userList: any;
  roleList: any;
  statusList: any;
  stt: any;
  holidayList:any;
  showModal: boolean=false;
  showModalEdit: boolean = false;
  selectedParent: any;
  selectedChild: any;
  listOption: any;
  childOptions!: { id: number, name: string }[];

  //ng oninit
  ngOnInit(): void {
    this.initFormSearch();
    this.getHolidayService();
  }

  //form search
  initFormSearch(){
    this.formSearch = this.fb.group({
      name: [],
      date: []
    })
  }
  search(){
    this.getHolidayService();
  }
  resetForm(){
    this.formSearch.reset();
    this.getHolidayService();
  }

  initFormHoliday(){
    this.formHoliday = this.fb.group({
      name: ['',Validators.required],
      code: [],
      note: [],
      date: [],
    })
  }


  // edit
  editHoliday(holiday:any){
    console.log(holiday);
    this.showModalEdit = true;
    this.initFormHoliday();
    console.log(holiday);
    this.formHoliday.patchValue(holiday);
  }
  
  updateHoliday(){
    
    const json = {
      ...this.formHoliday.value,
    }
    console.log(this.formHoliday);
    if(this.formHoliday.valid){
     this.holidayService.updateHoliday(json).subscribe(
      (data)=> {
        console.log(data);
        this.showModalEdit = false;
        this.getHolidayService();
      }
     )
    }
  }

  cancelEdit(){
    this.showModalEdit=false;
  }
  

  // create
  createHoliday(){
    this.showModal=true;
    this.initFormHoliday();
  }
  submitHoliday(){
    const json = {
      ...this.formHoliday.value,
    };
    console.log(json);
    console.log(this.formHoliday);
    if(this.formHoliday.valid){
      this.holidayService.createHoliday(json).subscribe(
        (data)=>{
          console.log(data);
          if(data.errorCode==="OK"){
            this.showModal = false;
            this.getHolidayService();
          }
        }
      )
    }
  }
  cancel(){
    this.showModal = false;
  }

  resetFormHoliday(){
    this.formHoliday.reset();
  }
  //get service
  
  getHolidayService(){
    const json = {
      name: this.formSearch.get('name').value,
      date: this.formSearch.get('date').value
    };
    this.holidayService.getHolidayList(json).subscribe(
      (data) => {
        this.holidayList = data;;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  
  get f() {
    return this.formHoliday.controls;
  }

  
 
  
}
