import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { SendEmailOtpComponent } from './pages/send-email-otp/send-email-otp.component';

const routes: Routes = [
  {
    path: '',
    component: AuthComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'sign-in' },
      { path: 'sign-in', pathMatch: 'full', component: SignInComponent },
      { path: 'send-email-otp', pathMatch: 'full', component: SendEmailOtpComponent },
      { path: 'reset-password/:email', pathMatch: 'full', component: ResetPasswordComponent },

    ],
  
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
