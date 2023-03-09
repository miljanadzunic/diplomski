import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutAccountComponent } from './about-account/about-account.component';
import { AboutComponent } from './about/about.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CoachGroupsComponent } from './coach-groups/coach-groups.component';
import { CoachHomeComponent } from './coach-home/coach-home.component';
import { CoachReservationStepperComponent } from './coach-reservation-stepper/coach-reservation-stepper.component';
import { CoachComponent } from './coach/coach.component';
import { CourtComponent } from './court/court.component';
import { GroupFormComponent } from './group-form/group-form.component';
import { LoginComponent } from './login/login.component';
import { PriceListComponent } from './price-list/price-list.component';
import { RecreationistHomeComponent } from './recreationist-home/recreationist-home.component';
import { RegistrationComponent } from './registration/registration.component';
import { ReservationStepperComponent } from './reservation-stepper/reservation-stepper.component';
import { SearchCoachesComponent } from './search-coaches/search-coaches.component';
import { SearchCourtsComponent } from './search-courts/search-courts.component';
import { StudentComponent } from './student/student.component';

const routes: Routes = [
  {path: "coach-groups", component: CoachGroupsComponent},
  {path: "coach-reservation-stepper", component: CoachReservationStepperComponent},
  {path: "create-group", component: GroupFormComponent},
  {path: "about-me", component: AboutAccountComponent},
  {path: "search-coaches", component: SearchCoachesComponent},
  {path: "search-courts", component: SearchCourtsComponent},
  {path: "reservation", component: ReservationStepperComponent},
  {path: "all-courts", component: CourtComponent},
  {path: "about", component: AboutComponent},
  {path: "price-list", component: PriceListComponent},
  {path: "admin-home", component: AdminHomeComponent},
  {path: "all-coaches", component: CoachComponent},
  {path: "coach-home", component: CoachHomeComponent},
  {path: "student", component: StudentComponent},
  {path: "recreationist", component: RecreationistHomeComponent},
  {path: "registration", component: RegistrationComponent},
  {path: "login", component: LoginComponent},
  {path: "", component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
