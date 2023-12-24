import { NgModule, ɵsetUnknownPropertyStrictMode } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {  StatisticRoutingModule } from './statistic-routing.module';
import { StatisticComponent } from './statistic.component';
import { InOutComponent } from './in-out/in-out.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SurveyComponent } from './survey/survey.component';
import { RegisterComponent } from './register/register.component';
import { FeedbackComponent } from './feedback/feedback.component';

@NgModule({
  declarations: [
   StatisticComponent,
   InOutComponent,
   SignInComponent,
   SurveyComponent,
   RegisterComponent,
   FeedbackComponent
],
  imports: [
    CommonModule,
    StatisticRoutingModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule

  ],

})
export class StatisticModule {}
