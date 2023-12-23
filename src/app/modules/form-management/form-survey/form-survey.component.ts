import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Option } from 'src/app/core/models/response';
import { CommonService } from 'src/app/core/services/common.service';
import { SurveyService } from 'src/app/core/services/survey.service';
import { UserService } from 'src/app/core/services/users.service';
import { formatDate } from 'src/app/core/utils/format.util';
@Component({
  selector: 'app-form-survey',
  templateUrl: './form-survey.component.html',
  styleUrls: ['./form-survey.component.css']
})
export class FormSurveyComponent implements OnInit{
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
  constructor(private userService: UserService, private surveyService: SurveyService, private commonService: CommonService, private router: Router, private fb: FormBuilder) {}
  nameImage: any;
  formSearch: any;
  formSurvey!: FormGroup;
  formSurveyEdit!: FormGroup;
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
  surveyList: any;
  showModal: boolean=false;
  showModalEdit: boolean = false;
  selectedParent: any;
  selectedChild: any;
  listOption: any;
  childOptions!: { id: number, name: string }[];

  //ng oninit
  ngOnInit(): void {
    this.initFormSearch();
    this.getSurveyService();
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
    this.getSurveyService();
  }
  resetForm(){
    this.formSearch.reset();
    this.getSurveyService();
  }

  //form user
  initFormSurvey(){
    this.formSurvey = this.fb.group({
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

  
  initFormSurveyEdit(){
    this.formSurveyEdit = this.fb.group({
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
    return this.formSurveyEdit.get('option1') as FormArray;
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
    this.formSurveyEdit.get('typeOfQuestion1Id')?.valueChanges.subscribe(value => {
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
  editSurvey(survey:any){
    console.log(survey);
    this.showModalEdit = true;
    this.initFormSurveyEdit();
    console.log(survey);
    if (survey.optionQuestion1!==null) {
      // Transform optionQuestion1 to the expected structure
      // const optionsArray = survey.option1.map((optionText: string) => ({ optionText }));

      // const surveyWithTransformedOptions = { ...survey, option1: optionsArray };
      const text = survey.optionQuestion1;
      this.listOption = text.split('|');
      // this.option1.push(this.fb.group({
      //   optionText: this.listOption.map((list:any)=>list) // 'optionText' should match the control name in your form definition
      //   // this.listOption.
      // }));
      console.log(this.listOption[0]);
      // console.log(survey.option1);
      this.formSurveyEdit.patchValue(survey);
     
    } else if(survey.option1!==null){
      this.formSurveyEdit.patchValue(survey);
    }
  }
  
  updateSurvey(){
    
    console.log(this.formSurveyEdit.value.optionQuestion1);
    // Lấy giá trị của optionText từ một FormGroup trong options1
    // Lấy mảng chứa tất cả các giá trị của optionText từ options1
    const optionTextArray = this.option1.value.map((option: Option) => option.optionText);
    console.log(optionTextArray);
    const resultString: string = optionTextArray.join('|');
    
    // console.log(resultString);
    const json = {
      ...this.formSurveyEdit.value,
      optionQuestion1: resultString,
    }

    console.log(this.formSurveyEdit);
    if(this.formSurveyEdit.valid){
      this.surveyService.updateSurvey(json).subscribe(
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
  createSurvey(){
    this.showModal=true;
    this.initFormSurvey();
    this.getToQService();
    this.getObjectService();
  }
  submitSurvey(){
    // console.log(this.formUser);
    const json = {
      ...this.formSurvey.value,
    };
    console.log(json);
    console.log(this.formSurvey);
    if(this.formSurvey.valid){
      this.surveyService.createSurvey(json).subscribe(
        (data)=>{
          console.log(data);
          if(data.errorCode==="OK"){
            this.showModal = false;
            this.getSurveyService();
          }
        }
      )
    }
  }
  cancel(){
    this.showModal = false;
  }

  resetFormSurvey(){
    this.formSurveyEdit.reset();
  }
  //get service
  
  getSurveyService(){
    const json = {
      code: this.formSearch.get('code').value,
      name: this.formSearch.get('name').value,
      typeOfQuestionId: this.formSearch.get('typeOfQuestionId').value,
      startTime: this.formSearch.get('startTime').value,
      endTime: this.formSearch.get('endTime').value,
    };
    this.surveyService.getSurveyList(json).subscribe(
      (data) => {
        this.surveyList = data;;
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
    return this.formSurvey.controls;
  }

  
 
  
}
