// auth.guard.ts

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, UrlTree } from '@angular/router';
import { AuthService } from '../services/auth.service';
// import { AuthService } from './auth.service';

@Injectable({
    providedIn: 'root',
})
export class AuthGuard implements CanActivate {
    constructor(private authService: AuthService, private router: Router) { }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): boolean {
        // console.log(this.authService.isLoggedIn());
        // console.log(next.url);
        // console.log(state.url);
        if (this.authService.isLoggedIn()) {
            // 1.check router contais /auth
            // redirect /home
            if (state.url.includes('/auth/sign-in')) {
                this.router.navigateByUrl('/');
                return false;
            }
            // 2. new guard for auth component routing
            return true;
        } else {
            console.log('Redirecting to login');
            this.router.navigateByUrl('/auth/sign-in');
            return false;
        }
    }


}