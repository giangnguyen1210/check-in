import { NgModule, ɵsetUnknownPropertyStrictMode } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormManagementRoutingModule } from './form-management-routing.module';
import { FormManagementComponent } from './form-management.component';
import { FormSurveyComponent } from './form-survey/form-survey.component';
import { FormRegisterComponent } from './form-register/form-register.component';
import { DropdownComponent } from './dropdown/dropdown.component';


@NgModule({
  declarations: [
    FormManagementComponent,
    FormSurveyComponent,
    FormRegisterComponent,
    DropdownComponent,
],
  imports: [
    CommonModule,
    FormManagementRoutingModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule

  ],

})
export class FormManagementModule {}
