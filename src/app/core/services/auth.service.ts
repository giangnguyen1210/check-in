import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, map, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.TOKEN_KEY);
  }

  // private apiUrl = 'http://localhost:4400/api/v1';
  private apiUrl = environment.apiUrl;
  constructor(private http: HttpClient,private _router: Router, private headerService: HeaderService) { }

  private readonly GET_TOKEN = environment.token;
 
  private readonly TOKEN_KEY = 'token';

  getJwtToken(): string | null {
    return this.GET_TOKEN;
  }

  setJwtToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }
  isTokenExpired(token: any): boolean {
    if (!token) {
      return true; // Nếu không có token, coi như đã hết hạn
    }

    const decodedToken: any = jwtDecode(token);

    if (!decodedToken.exp) {
      return true; // Nếu không có thời gian hết hạn, coi như đã hết hạn
    }

    // Lấy thời gian hiện tại
    const currentTime = Date.now() / 1000;

    // So sánh thời gian hết hạn với thời gian hiện tại
    return decodedToken.exp < currentTime;
  }

  login(user: any): Observable<any> {
    const loginUrl = `${this.apiUrl}/auth/login`; 
    return this.http.post(loginUrl, user).pipe(
      map((response) => {
        // console.log(response);
        return response;
      }),
      catchError((error) => {
        throw error;
      })
    );

  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    this._router.navigate(['/auth/sign-in']);
  }

  // userDetail(): Observable<any>{
  //   const url = `${this.apiUrl}/auth/user-detail`;
  //   return this.http.post(url, {}).pipe(
  //     map((response)=>{
  //       return response;
  //     })
  //   )
  // }
  
  sendEmailOTP(user: any): Observable<any> {
    const url = `${this.apiUrl}/auth/send-email-repass`; 
    return this.http.post(url, user).pipe(
      map((response) => {
        // console.log(response);
        return response;
      }),
      catchError((error) => {
        throw error;
      })
    );
  }

  resetPassword(user: any): Observable<any> {
    const url = `${this.apiUrl}/auth/reset-password`; 
    return this.http.post(url, user).pipe(
      map((response) => {
        // console.log(response);
        return response;
      }),
      catchError((error) => {
        throw error;
      })
    );
  }


}
