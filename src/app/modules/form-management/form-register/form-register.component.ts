import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Option } from 'src/app/core/models/response';
import { RegisterService } from 'src/app/core/services/\u001Dregister.service';
import { CommonService } from 'src/app/core/services/common.service';
import { UserService } from 'src/app/core/services/users.service';
import { formatDate } from 'src/app/core/utils/format.util';
@Component({
  selector: 'app-form-register',
  templateUrl: './form-register.component.html',
  styleUrls: ['./form-register.component.css']
})
export class FormRegisterComponent implements OnInit{
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
  constructor(private registerService: RegisterService, private commonService: CommonService, private router: Router, private fb: FormBuilder) {}
  nameImage: any;
  formSearch: any;
  formRegister!: FormGroup;
  formRegisterEdit!: FormGroup;
  isSubmit = false;
  userList: any;
  roleList: any;
  statusList: any;
  stt: any;
  unitList:any;
  genderList: any;
  objectList: any;
  positionList: any;
  toqList: any;
  jobTitleList: any;
  registerList: any;
  showModal: boolean=false;
  showModalEdit: boolean = false;
  selectedParent: any;
  selectedChild: any;
  listOption: any;
  childOptions!: { id: number, name: string }[];

  //ng oninit
  ngOnInit(): void {
    this.initFormSearch();
    this.getRegisterService();
    this.getToQService();
  }

  //form search
  initFormSearch(){
    this.formSearch = this.fb.group({
      code: [''],
      name: [],
      typeOfQuestionId: [],
      endTime: [],
      startTime: []
    })
  }
  search(){
    this.getRegisterService();
  }
  resetForm(){
    this.formSearch.reset();
    console.log(this.formSearch);
    this.getRegisterService();
  }

  //form user
  initFormRegister(){
    this.formRegister = this.fb.group({
      name: ['',Validators.required],
      question1: ['',Validators.required],
      typeOfQuestion1Id: ['',Validators.required],
      question2: ['',Validators.required],
      typeOfQuestion2Id: ['',Validators.required],
      mandatoryObject: ['',Validators.required],
      completionRate: [],
      startTime: ['',Validators.required],
      endTime: ['',Validators.required],
      optionQuestion1:  [],
      option1: this.fb.array([])
    })
  }

  
  initFormRegisterEdit(){
    this.formRegisterEdit = this.fb.group({
      code: [],
      name: ['',Validators.required],
      question1: [],
      typeOfQuestion1Id: [],
      question2: [],
      typeOfQuestion2Id: [],
      optionQuestion1:  [],
      option1: this.fb.array([])
    })
  }

  get option1() {
    return this.formRegisterEdit.get('option1') as FormArray;
  }

  addOption() {
    this.option1.push(this.fb.group({
      optionText: ['']  // 'optionText' should match the control name in your form definition
    }));
    console.log(this.option1.length);
  }
  
  removeOption(index: number) {
    this.option1.removeAt(index);
  }

  onChangeType(event: any) {
    this.formRegisterEdit.get('typeOfQuestion1Id')?.valueChanges.subscribe(value => {
      if (value === '1') {
        console.log(this.option1);
        if(this.option1.length<2){
          this.addOption();
        }else{
          return;
        }
      } else {
        while (this.option1.length) {
          this.option1.removeAt(0);
        }
      }
    });
  }

  // edit
  editRegister(register:any){
    console.log(register);
    this.showModalEdit = true;
    this.initFormRegisterEdit();
    console.log(register);
    if (register.optionQuestion1!==null) {
      // Transform optionQuestion1 to the expected structure
      // const optionsArray = register.option1.map((optionText: string) => ({ optionText }));

      // const registerWithTransformedOptions = { ...register, option1: optionsArray };
      const text = register.optionQuestion1;
      this.listOption = text.split('|');
      // this.option1.push(this.fb.group({
      //   optionText: this.listOption.map((list:any)=>list) // 'optionText' should match the control name in your form definition
      //   // this.listOption.
      // }));
      console.log(this.listOption[0]);
      // console.log(register.option1);
      this.formRegisterEdit.patchValue(register);
     
      this.formRegisterEdit.patchValue({option1: this.option1});
      console.log(this.formRegisterEdit);
    } else if(register.option1!==null){
      this.formRegisterEdit.patchValue(register);
    }
  }
  
  updateregister(){
    
    console.log(this.formRegisterEdit.value.optionQuestion1);
    // Lấy giá trị của optionText từ một FormGroup trong options1
    // Lấy mảng chứa tất cả các giá trị của optionText từ options1
    const optionTextArray = this.option1.value.map((option: Option) => option.optionText);
    console.log(optionTextArray);
    const resultString: string = optionTextArray.join('|');
    
    // console.log(resultString);
    const json = {
      ...this.formRegisterEdit.value,
      optionQuestion1: resultString,
    }

    console.log(this.formRegisterEdit);
    if(this.formRegisterEdit.valid){
      this.registerService.updateRegister(json).subscribe(
        (data)=>{
          console.log(data);
        }
      )
    }
  }

  cancelEdit(){
    this.showModalEdit=false;
  }
  

  // create
  createRegister(){
    this.showModal=true;
    this.initFormRegister();
    this.getToQService();
    this.getObjectService();
  }
  submitregister(){
    // console.log(this.formUser);
    const json = {
      ...this.formRegister.value,
    };
    console.log(json);
    console.log(this.formRegister);
    if(this.formRegister.valid){
      this.registerService.createRegister(json).subscribe(
        (data)=>{
          console.log(data);
          if(data.errorCode==="OK"){
            this.showModal = false;
            this.getRegisterService();
          }
        }
      )
    }
  }
  cancel(){
    this.showModal = false;
  }

  resetFormRegister(){
    this.formRegisterEdit.reset();
  }
  //get service
  
  getRegisterService(){
    const json = {
      code: this.formSearch.get('code').value,
      name: this.formSearch.get('name').value,
      typeOfQuestionId: this.formSearch.get('typeOfQuestionId').value,
      startTime: this.formSearch.get('startTime').value,
      endTime: this.formSearch.get('endTime').value,
    };
    console.log(this.formSearch);
    this.registerService.getRegisterList(json).subscribe(
      (data) => {
        this.registerList = data;
        // console.log(this.registerList.data);
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

  getToQService(){
    this.commonService.getToQList().subscribe(
      (data) => {
        this.toqList = data;
        console.log(this.toqList);
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }

  getObjectService(){
    this.commonService.getObjectsList().subscribe(
      (data) => {
        this.objectList = data;
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  
  get f() {
    return this.formRegister.controls;
  }
  
}
