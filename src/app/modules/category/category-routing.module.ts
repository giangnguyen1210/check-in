import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { CategoryComponent } from './category.component';
import { DepartmentComponent } from './department/department.component';
import { UnitComponent } from './unit/unit.component';
import { HolidayComponent } from './holiday/holiday.component';
import { EventComponent } from './event/event.component';
const routes: Routes = [
  {
    path: '',
    component: CategoryComponent,
    canActivate: [AuthGuard], // Apply the AuthGuard to the DashboardComponent
    children: [
      {
        path: 'department',
        component: DepartmentComponent
      },
      {
        path: 'unit',
        component: UnitComponent
      },
      {
        path: 'holiday',
        component: HolidayComponent
      }, 
      {
        path: 'event',
        component: EventComponent
      }
    ],

  },

  // Các routes khác nếu có
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CategoryRoutingModule { }
