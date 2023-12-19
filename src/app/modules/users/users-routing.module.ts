import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { UsersComponent } from './users.component';
const routes: Routes = [
  {
    path: '',
    component: UsersComponent,
    canActivate: [AuthGuard], // Apply the AuthGuard to the DashboardComponent
    // children: [
    //   { path: '', redirectTo: 'fis', pathMatch: 'full' }

    // ],
    
  },
  
  // Các routes khác nếu có
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
