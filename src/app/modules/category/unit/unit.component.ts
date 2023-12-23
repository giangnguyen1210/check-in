import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Option } from 'src/app/core/models/response';
import { CommonService } from 'src/app/core/services/common.service';
import { UnitService } from 'src/app/core/services/unit.service';
import { UserService } from 'src/app/core/services/users.service';
@Component({
  selector: 'app-unit',
  templateUrl: './unit.component.html',
  styleUrls: ['./unit.component.css']
})
export class UnitComponent implements OnInit{
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
  constructor(private unitService: UnitService, private commonService: CommonService, private router: Router, private fb: FormBuilder) {}
  nameImage: any;
  formSearch: any;
  formUnit!: FormGroup;
  formUnitEdit!: FormGroup;
  isSubmit = false;
  userList: any;
  roleList: any;
  statusList: any;
  stt: any;
  unitList:any;
  genderList: any;
  branchList: any;
  showModal: boolean=false;
  showModalEdit: boolean = false;
  selectedParent: any;
  selectedChild: any;
  listOption: any;
  childOptions!: { id: number, name: string }[];

  //ng oninit
  ngOnInit(): void {
    this.initFormSearch();
    this.getUnitService();
    this.getBranchService();
  }

  //form search
  initFormSearch(){
    this.formSearch = this.fb.group({
      name: [],
      branch: []
    })
  }
  search(){
    this.getUnitService();
  }
  resetForm(){
    this.formSearch.reset();
    this.getUnitService();
  }

  //form user
  initFormUnit(){
    this.formUnit = this.fb.group({
      name: ['',Validators.required],
      code: [],
      note: [],
      branch: []
    })
  }

  
  // initFormUnitEdit(){
  //   this.formUnitEdit = this.fb.group({
  //     name: ['',Validators.required],
  //     code: [],
  //     note: [],
  //     branch: []
  //   })
  // }


  // edit
  editUnit(unit:any){
    console.log(unit);
    this.showModalEdit = true;
    this.initFormUnit();
    console.log(unit);
    this.formUnit.patchValue(unit);
  }
  
  updateUnit(){
    
    const json = {
      ...this.formUnit.value,
    }

    console.log(this.formUnit);
    if(this.formUnit.valid){
     this.unitService.updateUnit(json).subscribe(
      (data)=> {
        console.log(data);
        this.showModalEdit = false;
        this.getUnitService();
      }
     )
    }
  }

  cancelEdit(){
    this.showModalEdit=false;
  }
  

  // create
  createUnit(){
    this.showModal=true;
    this.initFormUnit();
  }
  submitUnit(){
    const json = {
      ...this.formUnit.value,
    };
    console.log(json);
    console.log(this.formUnit);
    if(this.formUnit.valid){
      this.unitService.createUnit(json).subscribe(
        (data)=>{
          console.log(data);
          if(data.errorCode==="OK"){
            this.showModal = false;
            this.getUnitService();
          }
        }
      )
    }
  }
  cancel(){
    this.showModal = false;
  }

  resetFormUnit(){
    this.formUnit.reset();
  }
  //get service
  
  getUnitService(){
    const json = {
      name: this.formSearch.get('name').value,
      branch: this.formSearch.get('branch').value,
    };
    this.unitService.getUnitList(json).subscribe(
      (data) => {
        this.unitList = data;;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  getBranchService(){
    this.commonService.getBranchList().subscribe(
      (data) => {
        this.branchList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  
  get f() {
    return this.formUnit.controls;
  }

  
 
  
}
