import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OpinionRoutingModule } from './opinion-routing.module';
import { OpinionComponent } from './opinion.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';


@NgModule({
  declarations: [
    OpinionComponent
  ],
  imports: [
    OpinionRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule
  ]
})
export class OpinionModule { }
