import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { AuthComponent } from './auth.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { SendEmailOtpComponent } from './pages/send-email-otp/send-email-otp.component';

@NgModule({
  declarations: [
    AuthComponent,
    SignInComponent,
    ResetPasswordComponent,
    SendEmailOtpComponent,
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    ReactiveFormsModule
  ],
})
export class AuthModule {}
