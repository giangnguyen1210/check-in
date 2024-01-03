import { NgModule } from '@angular/core';
import { SettingComponent } from './setting.component';
import { SettingRoutingModule } from './setting-routing.module';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    SettingComponent
  ],
  imports: [
    SettingRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class SettingModule { }
