import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { Court } from '../model/court';
import { Reservation } from '../model/reservation';
import { Status } from '../model/status';
import { AccountService } from '../service/account.service';
import { CourtService } from '../service/court.service';
import { ReservationService } from '../service/reservation.service';
import { StatusService } from '../service/status.service';

@Component({
  selector: 'app-student-and-recreationist-reservation-list',
  templateUrl: './student-and-recreationist-reservation-list.component.html',
  styleUrls: ['./student-and-recreationist-reservation-list.component.css']
})
export class StudentAndRecreationistReservationListComponent implements OnInit{
  
  errMsg: string = '';
  successMsg: string = '';
  emptymessage: boolean = false;

  myReservations: Reservation[] = [];
  user: Account = new Account();

  filterTimeOptions: any[] = [];
  filterCoachesOptions: any[] = [];
  filterCourtsOptions: any[] = [];
  filterStatusOptions: any[] = [];

  filterDate: Date | null = null;
  filterTime: number | null = null;
  filterCoach: Account | null = null;
  filterCourt: Court | null = null;  
  filterStatus: Status | null = null;

  constructor(private router: Router, 
              private reservationService: ReservationService, 
              private accountService: AccountService,
              private courtService: CourtService,
              private statusService: StatusService) {

  }

  ngOnInit(): void {
    this.errMsg = '';
    this.successMsg = '';

    let newUser = localStorage.getItem('user');
    if(newUser != null){
      this.user = JSON.parse(newUser)
    } else {
      this.user = new Account();
      this.logOut();
    }
    
    if(this.user && this.user.accRole.rolName != "recreationist" && this.user.accRole.rolName != "student"){
      this.logOut();
      return;
    } 

    this.reservationService.getReservationsByStudentIdAndGroup(this.user.accId).subscribe( res => {
      this.populateFilters();
      this.myReservations = res;
    });
  }

  logOut() {
    this.errMsg = '';
    this.successMsg = '';
    localStorage.clear();
    this.router.navigate([""]);
  }

  cancelReservation(obj: Reservation){
    this.reservationService.cancel(obj.resId).subscribe(() => {
      this.reservationService.getReservationsByStudentIdAndGroup(this.user.accId).subscribe( res => {
        this.myReservations = res;
      });
    });
  }

  deleteReservation(obj: Reservation){
    this.reservationService.delete(obj.resId).subscribe(() => {
      this.reservationService.getReservationsByStudentIdAndGroup(this.user.accId).subscribe( res => {
        this.myReservations = res;
      });
    });
  }

  filterTable(){
    if(this.filterCoach == null &&
      this.filterCourt == null && 
      this.filterDate == null &&
      this.filterTime == null && 
      this.filterStatus == null) {
        this.reservationService.getReservationsByStudentIdAndGroup(this.user.accId).subscribe( res => {
          this.myReservations = res;
        });
        return;
      }
      
      this.reservationService.getReservationsByStudentIdAndGroup(this.user.accId).subscribe( res => {
        let filterRes: Reservation[] = res;
        if(this.filterDate){
          debugger;
           filterRes = filterRes.filter(res => {
            debugger;
            return (res.resDate.getFullYear() == this.filterDate?.getFullYear() && 
                    res.resDate.getMonth() == this.filterDate.getMonth() && 
                    res.resDate.getDate() == this.filterDate.getDate()) ;
           });
        }

        if(this.filterTime){
          filterRes = filterRes.filter(res => {
            return res.resTimeStart == this.filterTime;
           });
        }

        if(this.filterStatus){
          filterRes = filterRes.filter(res => {
            return res.resStatus.staId == this.filterStatus?.staId;
           });
        }

        if(this.filterCoach){
          filterRes = filterRes.filter(res => {
            return res.resCoach?.accId == this.filterCoach?.accId;
           });
        }

        if(this.filterCourt){
          filterRes = filterRes.filter(res => {
            return res.resCourt.corId == this.filterCourt?.corId;
           });
        }

        this.myReservations = filterRes;
      });

  }

  populateFilters(){
    this.filterCoachesOptions.push({
      label: '',
      value: null
    });


    this.filterCourtsOptions.push({
      label: '',
      value: null
    });

    this.filterTimeOptions.push({
      label: '',
      value: null
    });

    this.filterStatusOptions.push({
      label: '',
      value: null
    });

    for(let i = 7; i < 22; i++){
      this.filterTimeOptions.push({
        label: (i < 10) ? '0' + i + ":00": i + ":00",
        value: i
      });
    }
    this.accountService.getAccountsByRole('coach').subscribe(coaches => {
      if(coaches){
        coaches.map(res => {
          this.filterCoachesOptions.push({
            label: res.accName + ' ' + res.accSurname,
            value: res
          });
        });
      }

      this.courtService.getAllCourts().subscribe(courts => {
        if(courts){
          courts.map(res => {
            this.filterCourtsOptions.push({
              label: res.corName,
              value: res
            });
          });
        }

        this.statusService.getAllStatusesByTable('reservation').subscribe(statuses => {
          if(statuses){
            statuses.map(res => {
              this.filterStatusOptions.push({
                label: res.staDesc,
                value: res
              });
            });
          }
        })
      });
    });
  }

  cancelFilter(){
    this.filterCoach = this.filterCoachesOptions.find(res => res.value == null);
    this.filterCourt = this.filterCourtsOptions.find(res => res.value == null);
    this.filterDate = null;
    this.filterTime = this.filterTimeOptions.find(res => res.value == null);
    this.filterStatus = this.filterStatusOptions.find(res => res.value == null);
    this.reservationService.getReservationsByStudentIdAndGroup(this.user.accId).subscribe( res => {
      this.myReservations = res;
    });
  }
}
