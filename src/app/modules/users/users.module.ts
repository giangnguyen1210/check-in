import { NgModule, ɵsetUnknownPropertyStrictMode } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReactiveFormsModule } from '@angular/forms';
import { UsersComponent } from './users.component';
import { UsersRoutingModule } from './users-routing.module';
import { ListUserComponent } from './list-user/list-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';

@NgModule({
  declarations: [
    UsersComponent,
    ListUserComponent,
    EditUserComponent
],
  imports: [
    CommonModule,
    UsersRoutingModule,
    ReactiveFormsModule
  ],
})
export class UsersModule {}
