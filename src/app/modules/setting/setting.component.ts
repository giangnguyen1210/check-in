import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { BaseResponse } from 'src/app/core/models/response';
import { SettingService } from 'src/app/core/services/setting.service';

@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.css']
})
export class SettingComponent  implements OnInit{
  tabs = [
    { id: 'session', dataTabsTarget: '#session', label: 'Phiên đăng nhập', isActive: false },
    { id: 'password', dataTabsTarget: '#password', label: 'Mật khẩu', isActive: false },
    { id: 'working_time', dataTabsTarget: '#working_time', label: 'Thời gian làm việc', isActive: false },
    { id: 'language', dataTabsTarget: '#language', label: 'Ngôn ngữ', isActive: false }
  ];

  selectedTab: any;
  tabsId = 'default-tab';
  tabsToggle = '#default-tab-content';
  tabsContentId = 'default-tab-content';
  formPassword: any;
  formSession: any;
  response: any;
  constructor(private fb: FormBuilder,
     private renderer: Renderer2,
     private toastr: ToastrService,
     private settingService: SettingService){

  }
  ngOnInit(): void {
    // Set the initial active tab
    this.tabs[0].isActive = true;
    this.initFormPassword();
    this.initFormSession();
    this.getSettingPasswordService();
    this.getSettingSessionService();
  }

  // Add a method to handle tab clicks
  onTabClick(selectedTab:any) {
    this.tabs.forEach(tab => tab.isActive = (tab === selectedTab));
    console.log(selectedTab);
    this.selectedTab = selectedTab;
  }

  initFormPassword(){
    this.formPassword=this.fb.group({
      id: [],
      maxLength: [{ value: '', disabled: true }],
      minLength: [{ value: '', disabled: true }],
      minChar: [{ value: '', disabled: true }],
      minNumber: [{ value: '', disabled: true }],
      minSpecialChar:[{ value: '', disabled: true }],
      timeExpire:[{ value: '', disabled: true }],
      createdAt: [],
      updatedAt: [],
    })
  }

  initFormSession(){
    this.formSession=this.fb.group({
      id: [],
      intervalTime: [{ value: '', disabled: true }],
      maxWrongTime:[{ value: '', disabled: true }],
      maxTime:[{ value: '', disabled: true }],
      timeLoginAgain:[{ value: '', disabled: true }],
      createdAt: [],
      updatedAt: [],
    })
  }



  editPassword(){
    this.formPassword.enable();
    this.focusOnMinLength();
  }
 
  private focusOnMinLength() {
    const minLengthControl = this.formPassword.get('minLength');
    if (minLengthControl && minLengthControl.enabled) {
      minLengthControl.markAsTouched();
      minLengthControl.markAsDirty();
      minLengthControl.updateValueAndValidity();

      // Use Renderer2 to set focus
      this.renderer.selectRootElement('#minLengthInput').focus();
    }
  }

  submitPassword(){
    const json = {
      ...this.formPassword.value,
    };
    if (this.formPassword.valid) {
      this.settingService.updateSettingPassword(json).subscribe(
        (data) => {
          this.response = data;
          if (this.response.errorCode === "OK") {
            this.toastr.success(this.response.errorDesc);
            this.formPassword.disable();
          }else{
            this.toastr.error(this.response.errorDesc);
          }
        }
      )
    }
  }

  getSettingPasswordService(){
    this.settingService.getSettingPassword({}).subscribe(
      (data) => {
        this.response = data;
        console.log(this.response);
        if (this.response.errorCode === "OK") {
          console.log(this.response.data.id);
          this.formPassword.setValue(this.response.data)
          // this.toastr.success(this.response.errorDesc);
        }else{
          this.toastr.error(this.response.errorDesc);
        }
      }
    )
  }

  getSettingSessionService(){
    this.settingService.getSettingSession({}).subscribe(
      (data) => {
        this.response = data;
        console.log(this.response);
        if (this.response.errorCode === "OK") {
          console.log(this.response.data.id);
          this.formSession.setValue(this.response.data)
          console.log(this.formSession);
          // this.toastr.success(this.response.errorDesc);
        }else{
          this.toastr.error(this.response.errorDesc);
        }
      }
    )
  }
  editSession(){
    this.formSession.enable();
    this.focusOnIntervalTime();
  }
  private focusOnIntervalTime(){
    const intervalTimeControl = this.formSession.get('intervalTime');
    if (intervalTimeControl && intervalTimeControl.enabled) {
      intervalTimeControl.markAsTouched();
      intervalTimeControl.markAsDirty();
      intervalTimeControl.updateValueAndValidity();

      // Use Renderer2 to set focus
      this.renderer.selectRootElement('#intervalTimeInput').focus();
    }
  }
  submitSession(){
    const json = {
      ...this.formSession.value,
    };
    if (this.formSession.valid) {
      this.settingService.updateSettingSession(json).subscribe(
        (data) => {
          this.response = data;
          if (this.response.errorCode === "OK") {
            this.toastr.success(this.response.errorDesc);
            this.formSession.disable();
          }else{
            this.toastr.error(this.response.errorDesc);
          }
        }
      )
    }
  }
}
