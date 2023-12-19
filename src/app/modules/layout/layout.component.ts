import { Component, OnInit } from '@angular/core';
import { ThemeService } from 'src/app/core/services/theme.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  constructor(public themeService: ThemeService) {}
  ngOnInit(): void {
    // You can do additional setup logic here
  }

  toggleTheme(): void {
    this.themeService.toggleTheme();
  }
}
