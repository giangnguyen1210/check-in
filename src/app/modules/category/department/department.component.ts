import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Option } from 'src/app/core/models/response';
import { CommonService } from 'src/app/core/services/common.service';
import { DepartmentService } from 'src/app/core/services/department.service';
import { UserService } from 'src/app/core/services/users.service';
@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit{
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
  constructor(private userService: UserService, private departmentService: DepartmentService, private commonService: CommonService, private router: Router, private fb: FormBuilder) {}
  nameImage: any;
  formSearch: any;
  formDepartment!: FormGroup;
  formDepartmentEdit!: FormGroup;
  isSubmit = false;
  userList: any;
  roleList: any;
  statusList: any;
  stt: any;
  unitList:any;
  genderList: any;
  branchList: any;
  positionList: any;
  toqList: any;
  jobTitleList: any;
  departmentList!: any[];
  showModal: boolean=false;
  showModalEdit: boolean = false;
  selectedParent: any;
  selectedChild: any;
  currentPage = 1;
  pageSize = 2;

  //ng oninit
  ngOnInit(): void {
    this.initFormSearch();
    this.getDepartmentService();
    this.getBranchService();
    // this.dataSource.paginator = this.paginator;
    // this.dataSource.sort = this.sort;
    console.log(this.departmentList);
  }


  //form search
  initFormSearch(){
    this.formSearch = this.fb.group({
      name: [],
      branch: []
    })
  }
  search(){
    this.getDepartmentService();
  }
  resetForm(){
    this.formSearch.reset();
    this.getDepartmentService();
  }

  //form user
  initFormDepartment(){
    this.formDepartment = this.fb.group({
      name: ['',Validators.required],
      code: [],
      note: [],
      branch: []
    })
  }

  
  // initFormDepartmentEdit(){
  //   this.formDepartmentEdit = this.fb.group({
  //     name: ['',Validators.required],
  //     code: [],
  //     note: [],
  //     branch: []
  //   })
  // }


  // edit
  editDepartment(department:any){
    console.log(department);
    this.showModalEdit = true;
    this.initFormDepartment();
    console.log(department);
    this.formDepartment.patchValue(department);
  }
  
  updateDepartment(){
    
    const json = {
      ...this.formDepartment.value,
    }

    console.log(this.formDepartment);
    if(this.formDepartment.valid){
     this.departmentService.updateDepartment(json).subscribe(
      (data)=> {
        console.log(data);
        this.showModalEdit = false;
        this.getDepartmentService();
      }
     )
    }
  }

  cancelEdit(){
    this.showModalEdit=false;
  }
  

  // create
  createDepartment(){
    this.showModal=true;
    this.initFormDepartment();
  }
  submitDepartment(){
    const json = {
      ...this.formDepartment.value,
    };
    console.log(json);
    console.log(this.formDepartment);
    if(this.formDepartment.valid){
      this.departmentService.createDepartment(json).subscribe(
        (data)=>{
          console.log(data);
          if(data.errorCode==="OK"){
            this.showModal = false;
            this.getDepartmentService();
          }
        }
      )
    }
  }
  cancel(){
    this.showModal = false;
  }

  resetFormDepartment(){
    this.formDepartment.reset();
  }
  //get service
  
  getDepartmentService(){
    const json = {
      name: this.formSearch.get('name').value,
      branch: this.formSearch.get('branch').value,
    };
    this.departmentService.getDepartmentList(json).subscribe(
      (data) => {
        this.departmentList = data.data;
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
    return this.formDepartment.controls;
  }
}
