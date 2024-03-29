import { NgModule, ɵsetUnknownPropertyStrictMode } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CategoryRoutingModule } from './category-routing.module';
import { CategoryComponent } from './category.component';
import { DepartmentComponent } from './department/department.component';
import { UnitComponent } from './unit/unit.component';
import { EventComponent } from './event/event.component';
import { AnnouncementComponent } from './announcement/announcement.component';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { HolidayComponent } from './holiday/holiday.component';
import {NgxMatTimepickerModule} from 'ngx-mat-timepicker';  
import { MomentModule } from 'ngx-moment';


@NgModule({
  declarations: [
    CategoryComponent,
    DepartmentComponent,
    UnitComponent,
    EventComponent,
    AnnouncementComponent,
    HolidayComponent
],
  imports: [
    CommonModule,
    CategoryRoutingModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatTableModule,
    MatButtonModule ,
    MatPaginatorModule,
    NgxMatTimepickerModule,
    MomentModule
  ],
  providers: [
  ]

})
export class CategoryManagementModule {}
