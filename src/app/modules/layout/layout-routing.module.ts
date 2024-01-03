import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout.component';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
// import { AuthGuard } from 'src/app/core/guards/auth.guard';

const routes: Routes = [
  {
    path: 'dashboard',
    component: LayoutComponent,
    loadChildren: () => import('../dashboard/dashboard.module').then((m) => m.DashboardModule),
    canActivate: [AuthGuard], // Apply the AuthGuard to the entire dashboard module
  },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'users',
    component: LayoutComponent,
    loadChildren: () => import('../users/users.module').then((m) => m.UsersModule),
    canActivate: [AuthGuard], // Apply the AuthGuard to the entire dashboard module
  },
  {
    path: 'form-management',
    component: LayoutComponent,
    loadChildren: () => import('../form-management/form-management.module').then((m) => m.FormManagementModule),
    canActivate: [AuthGuard], // Apply the AuthGuard to the entire dashboard module
  },
  {
    path: 'category',
    component: LayoutComponent,
    loadChildren: () => import('../category/category.module').then((m) => m.CategoryManagementModule),
    canActivate: [AuthGuard], // Apply the AuthGuard to the entire dashboard module
  },
  {
    path: 'statistic',
    component: LayoutComponent,
    loadChildren: () => import('../statistic/statistic.module').then((m) => m.StatisticModule),
    canActivate: [AuthGuard], // Apply the AuthGuard to the entire dashboard module
  },
  {
    path: 'setting',
    component: LayoutComponent,
    loadChildren: () => import('../setting/setting.module').then((m) => m.SettingModule),
    canActivate: [AuthGuard], // Apply the AuthGuard to the entire dashboard module
  }
  ,
  {
    path: 'opinion',
    component: LayoutComponent,
    loadChildren: () => import('../opinion/opinion.module').then((m) => m.OpinionModule),
    canActivate: [AuthGuard], // Apply the AuthGuard to the entire dashboard module
  },
  // { path: '**', redirectTo: 'error/404' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LayoutRoutingModule {}