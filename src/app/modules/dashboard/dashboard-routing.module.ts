import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    canActivate: [AuthGuard], // Apply the AuthGuard to the DashboardComponent
    children: [
      { path: '', redirectTo: 'fis', pathMatch: 'full' }

    ],
    
  },
  
  // Các routes khác nếu có
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
