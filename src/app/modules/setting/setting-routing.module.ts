import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { SettingComponent } from './setting.component';
const routes: Routes = [
  {
    path: '',
    component: SettingComponent,
    canActivate: [AuthGuard], // Apply the AuthGuard to the DashboardComponent
    
  },
  
  // Các routes khác nếu có
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SettingRoutingModule {}
