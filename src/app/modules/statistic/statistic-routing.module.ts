import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { StatisticComponent } from './statistic.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { InOutComponent } from './in-out/in-out.component';
import { SurveyComponent } from './survey/survey.component';
import { RegisterComponent } from './register/register.component';
import { FeedbackComponent } from './feedback/feedback.component';
const routes: Routes = [
  {
    path: '',
    component: StatisticComponent,
    canActivate: [AuthGuard], // Apply the AuthGuard to the DashboardComponent
    children: [
        {
            path: 'in-out',
            component: InOutComponent,
        },
        {
            path: 'sign-in',
            component: SignInComponent,
        },
        {
          path: 'survey',
          component: SurveyComponent,
      },
      {
          path: 'register',
          component: RegisterComponent,
      },
      {
          path: 'feedback',
          component: FeedbackComponent,
      }
    ],
    
  },
  
  // Các routes khác nếu có
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StatisticRoutingModule {}
