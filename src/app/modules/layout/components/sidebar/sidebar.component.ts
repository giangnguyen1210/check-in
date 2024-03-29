import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  constructor(private router: Router) {}
  navigateTo(route: string){
    this.router.navigate([route]);
  }
  isDropdownOpen:boolean = false;
  isDropdownCategoryOpen: boolean = false;
  isDropdownStatisticOpen: boolean = false;
  isDropdownSettingOpen: boolean = false;


  toggleDropdownCategory() {
    this.isDropdownCategoryOpen = !this.isDropdownCategoryOpen;

  }
  toggleDropdownForm() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  toggleDropdownStatistic(){
    this.isDropdownStatisticOpen = !this.isDropdownStatisticOpen;
  }

 
}
