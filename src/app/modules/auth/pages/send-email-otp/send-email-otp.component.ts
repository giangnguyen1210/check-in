import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AsyncValidatorFn, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, catchError, map, of } from 'rxjs';
import { AuthResponse, BaseResponse } from 'src/app/core/models/response';
import { AuthService } from 'src/app/core/services/auth.service';
import { DataService } from 'src/app/core/services/data.service';
@Component({
  selector: 'app-send-email-otp',
  templateUrl: './send-email-otp.component.html',
  styleUrls: ['./send-email-otp.component.css']
})
export class SendEmailOtpComponent {
  otpForm: FormGroup;
  // response: any;
  response: BaseResponse;


  constructor(private formBuilder: FormBuilder, 
    private authService: AuthService, 
    private readonly _router: Router,
    private dataService: DataService
    ) {
    this.otpForm = this.formBuilder.group({
      email: ['', {
        validators: [Validators.required, Validators.email],
        asyncValidators: [this.emailAsyncValidator()],
        // updateOn: 'blur'
      }],
    });
    this.response = {
      data:'',
      errorCode: '',
      errorDesc: '',
      totalRecords: 0
    }
  }

  
  // ngOnInit() {
  //  if(this.authService.isLoggedIn()){
  //   this._router.navigate(['/'])
  //     return ;
  //  }
  // }


  emailAsyncValidator(): AsyncValidatorFn {
    return (control): Observable<{ [key: string]: any } | null> => {
      const email = control.value;

      // Simulate an asynchronous operation (e.g., checking email availability)
      return this.checkEmailAvailability(email).pipe(
        map(available => (available ? null : { emailTaken: true })),
        catchError(() => of(null)) // Handle errors gracefully
      );
    };
  }

  // Simulated asynchronous operation (replace with your actual API call)
  private checkEmailAvailability(email: string): Observable<boolean> {
    return of(true);
  }


  onSubmit(route: string) {
    if (this.otpForm.valid) {
      const user = {
        email: this.otpForm.value.email
      };
      console.log(user);
      
      this.authService.sendEmailOTP(user).subscribe(
        (res) => {
          // this.response = res;
          console.log(res);
          if(res.errorCode == 'OK'){
            this.response.errorCode = "OK";
            this.response.errorDesc = "Send email thành công";
          // this._router.navigate(['/auth/reset-password'],user.email);
          this._router.navigate([route]);


          }else{
            this.response.errorCode= "NOT_FOUND";
            this.response.errorDesc = "Email không tồn tại";
          }
        },
        (error) => {
          console.error('OTP sending email error', error);
        }
      );
      
      // this._router.navigate(['/home']);
    }
  }
}
