import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private router: Router, private authService: AuthService) {
    
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    const isExpired = this.authService.isTokenExpired(token);
    if (!isExpired) {
      req = req.clone({
        setHeaders: {
            'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
    } else {
      // Token không tồn tại, thực hiện xử lý đăng xuất và chuyển hướng đến trang đăng nhập
      if (!req?.url.includes('/auth') ) {
        this.handleLogout();
      }
      
    }

    return next.handle(req).pipe(
      tap(
        (event) => {},
        (error) => {
          // Kiểm tra lỗi để xác định nếu là lỗi hết hạn token
          if (error.status === 401) {
            // Token đã hết hạn, thực hiện xử lý đăng xuất và chuyển hướng đến trang đăng nhập
            this.handleLogout();
          }
        }
      )
    );
  }

  // Hàm xử lý đăng xuất và chuyển hướng
  private handleLogout() {
    console.log('handleLogout')
    // Xoá token từ localStorage (hoặc thực hiện các bước đăng xuất khác)
    localStorage.removeItem('token');

    // Chuyển hướng đến trang đăng nhập sử dụng Angular Router
    this.router.navigate(['/auth/sign-in']);
  }
}
