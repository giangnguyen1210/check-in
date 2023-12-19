import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout.component';
// import { AuthGuard } from 'src/app/core/guards/auth.guard';

const routes: Routes = [
  {
    path: 'dashboard',
    component: LayoutComponent,
    loadChildren: () => import('../dashboard/dashboard.module').then((m) => m.DashboardModule),
    // canActivate: [AuthGuard], // Apply the AuthGuard to the entire dashboard module
  },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'product',
    component: LayoutComponent,
    // loadChildren: () => import('../product')

  }
  // { path: '**', redirectTo: 'error/404' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LayoutRoutingModule {}