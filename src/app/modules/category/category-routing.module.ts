import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { CategoryComponent } from './category.component';
import { DepartmentComponent } from './department/department.component';
import { UnitComponent } from './unit/unit.component';
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
        }
    ],
    
  },
  
  // Các routes khác nếu có
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CategoryRoutingModule {}
