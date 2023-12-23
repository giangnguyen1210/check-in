import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { FormManagementComponent } from './form-management.component';
import { FormSurveyComponent } from './form-survey/form-survey.component';
import { FormRegisterComponent } from './form-register/form-register.component';
const routes: Routes = [
  {
    path: '',
    component: FormManagementComponent,
    canActivate: [AuthGuard], // Apply the AuthGuard to the DashboardComponent
    children: [
        {
            path: 'form-survey',
            component: FormSurveyComponent,
        },
        {
            path: 'form-register',
            component: FormRegisterComponent,
        }
    ],
    
  },
  
  // Các routes khác nếu có
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FormManagementRoutingModule {}
