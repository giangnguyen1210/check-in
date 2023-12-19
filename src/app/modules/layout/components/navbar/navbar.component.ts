import { Component } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { ThemeService } from 'src/app/core/services/theme.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(public themeService: ThemeService, public authService: AuthService) {}

  ngOnInit(): void {
    // You can do additional setup logic here
  }

  toggleTheme(): void {
    this.themeService.toggleTheme();
  }

  logout(){
    this.authService.logout();
  }
}
