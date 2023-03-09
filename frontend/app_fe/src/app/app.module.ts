import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormBuilder, FormsModule } from '@angular/forms';
import { AccountService } from './service/account.service';
import { HttpClientModule } from '@angular/common/http';
import { RegistrationComponent } from './registration/registration.component';
import { MenuComponent } from './menu/menu.component';
import { AboutComponent } from './about/about.component';
import { CoachComponent } from './coach/coach.component';
import { PriceListComponent } from './price-list/price-list.component';
import { StudentComponent } from './student/student.component';
import { RecreationistHomeComponent } from './recreationist-home/recreationist-home.component';
import { DropdownModule } from 'primeng/dropdown';
import { RoleService } from './service/role.service';
import {AccordionModule} from 'primeng/accordion'; 
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import  {ButtonModule } from'primeng/button'
import { MessagesModule } from 'primeng/messages';
import { GoogleMapsModule } from '@angular/google-maps';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CourtComponent } from './court/court.component';
import { TableModule } from 'primeng/table';
import { CourtService } from './service/court.service';
import { CourtTypeService } from './service/court-type.service';
import { PriceListService } from './service/price-list.service';
import { CalendarModule } from 'primeng/calendar';
import { ChipModule } from 'primeng/chip';
import { ReservationService } from './service/reservation.service';
import { ReservationStepperComponent } from './reservation-stepper/reservation-stepper.component';
import { MatStepperModule } from '@angular/material/stepper';
import { StudentAndRecreationistReservationListComponent } from './student-and-recreationist-reservation-list/student-and-recreationist-reservation-list.component';
import { SearchCoachesComponent } from './search-coaches/search-coaches.component';
import { SearchCourtsComponent } from './search-courts/search-courts.component';
import { AboutAccountComponent } from './about-account/about-account.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CoachHomeComponent } from './coach-home/coach-home.component';
import { StatusService } from './service/status.service';
import { StudentCoachService } from './service/student-coach.service';
import { GroupFormComponent } from './group-form/group-form.component';
import { GroupService } from './service/group.service';
import {MultiSelectModule} from 'primeng/multiselect';
import { CoachReservationStepperComponent } from './coach-reservation-stepper/coach-reservation-stepper.component';
import {RadioButtonModule} from 'primeng/radiobutton';
import { MyGroupsComponent } from './my-groups/my-groups.component';
import { CoachGroupsComponent } from './coach-groups/coach-groups.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    MenuComponent,
    AboutComponent,
    CoachComponent,
    PriceListComponent,
    StudentComponent,
    RecreationistHomeComponent,
    CourtComponent,
    ReservationStepperComponent,
    StudentAndRecreationistReservationListComponent,
    SearchCoachesComponent,
    SearchCourtsComponent,
    AboutAccountComponent,
    AdminHomeComponent,
    CoachHomeComponent,
    GroupFormComponent,
    CoachReservationStepperComponent,
    MyGroupsComponent,
    CoachGroupsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    DropdownModule,
    AccordionModule,
    BrowserAnimationsModule,
    ButtonModule,
    MessagesModule,
    GoogleMapsModule,
    FontAwesomeModule,
    TableModule, 
    CalendarModule,
    ChipModule,
    MatStepperModule,
    MultiSelectModule,
    RadioButtonModule
  ],
  providers: [
    AccountService,
    RoleService, 
    CourtService,
    CourtTypeService,
    PriceListService,
    ReservationService,
    StatusService,
    StudentCoachService,
    GroupService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
