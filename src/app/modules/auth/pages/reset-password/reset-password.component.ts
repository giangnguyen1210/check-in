import { Component, OnInit } from '@angular/core';
import { AsyncValidatorFn, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, catchError, map, of } from 'rxjs';
import { AuthResponse, BaseResponse } from 'src/app/core/models/response';
import { AuthService } from 'src/app/core/services/auth.service';
import { DataService } from 'src/app/core/services/data.service';
@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit{
  resetPasswordForm: FormGroup;
  response: BaseResponse;
  error!: string;
  email: string;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, 
    private readonly _router: Router, 
    private route: ActivatedRoute,
    private toastr: ToastrService
    ) {
    this.email = (this.route.snapshot.params['email']);
    this.resetPasswordForm = this.formBuilder.group({
      email: this.email,
      otp: ['', {}],
      password: ['', [Validators.required, Validators.minLength(6)]],
      rePassword: ['', [Validators.required, Validators.minLength(6)]],
    });
    this.response = {
      data:'',
      errorCode: '',
      errorDesc: '',
      totalRecords: 0
    }
  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }


 


  onSubmit() {
    // this.getData()
    if (this.resetPasswordForm.valid) {
      const user = {
        ...this.resetPasswordForm.value
      };
      console.log(user);
      this.authService.resetPassword(user).subscribe(
        (res) => {
          // this.response = res;
          console.log(res);
          if(res.errorCode==='OK'){
            this.toastr.success(res.errorDesc);
            this._router.navigate(['/auth/sign-in'])
            this.response.errorCode ="OK";
          }else{
            this.response.errorCode = "UNAUTHORIZED";
            this.error=res.errorDesc;
            this.toastr.error(res.errorDesc);
          }
          // console.log(response.accessToken);
        },
        (error) => {
          console.error('Login error', error);
        }
      );
      // this._router.navigate(['/home']);
    }
  }

  // forgotPassword(){
  //   this._router.navigate(['/auth/reset-password']);
  // }
}
