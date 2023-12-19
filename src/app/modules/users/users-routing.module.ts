import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { UsersComponent } from './users.component';
import { ListUserComponent } from './list-user/list-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
const routes: Routes = [
  {
    path: '',
    component: UsersComponent,
    canActivate: [AuthGuard], // Apply the AuthGuard to the DashboardComponent
    children: [
        {
            path: 'list-user',
            component: ListUserComponent,
        },
        {
            path: 'edit-user',
            component: EditUserComponent,
        }
    ],
    
  },
  
  // Các routes khác nếu có
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UsersRoutingModule {}
