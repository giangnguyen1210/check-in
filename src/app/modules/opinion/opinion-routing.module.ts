import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { OpinionComponent } from './opinion.component';
const routes: Routes = [
  {
    path: 'list-opinion',
    component: OpinionComponent,
    canActivate: [AuthGuard], // Apply the AuthGuard to the DashboardComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OpinionRoutingModule {}
