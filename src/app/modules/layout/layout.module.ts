import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LayoutComponent } from './layout.component';
import { LayoutRoutingModule } from './layout-routing.module';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';


@NgModule({
  declarations: [
    LayoutComponent,
    NavbarComponent,
    SidebarComponent
  ],
  imports: [
    LayoutRoutingModule,
    CommonModule
  ]
})
export class LayoutModule { }
