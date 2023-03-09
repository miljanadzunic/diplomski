import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { Reservation } from '../model/reservation';
import { Status } from '../model/status';
import { StudentCoach } from '../model/student_coach';
import { ReservationService } from '../service/reservation.service';
import { StatusService } from '../service/status.service';
import { StudentCoachService } from '../service/student-coach.service';

@Component({
  selector: 'app-coach-home',
  templateUrl: './coach-home.component.html',
  styleUrls: ['./coach-home.component.css']
})
export class CoachHomeComponent implements OnInit{
  errMsg1: string = '';
  successMsg1: string = '';

  errMsg2: string = '';
  successMsg2: string = '';

  user: Account = new Account();

  myReservations: Reservation[] = [];
  myRequests: StudentCoach[] = [];

  filterStatusOptions1: any[] = [];
  filterStatus1: Status | null = null;

  filterStatusOptions2: any[] = [];
  filterStatus2: Status | null = null;

  constructor(private reservationService: ReservationService, 
              private statusService: StatusService, 
              private studentCoachService: StudentCoachService,
              private router: Router){}

  ngOnInit(): void {
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    let newUser = localStorage.getItem('user');
    if(newUser != null){
      this.user = JSON.parse(newUser)
    } else {
      this.user = new Account();
      this.logOut();
    }
    
    if(this.user && this.user?.accRole.rolName != "coach"){
      this.logOut();
      return;
    } 

   
    this.reservationService.getReservationsByCoachId(this.user.accId).subscribe(res => {
      this.studentCoachService.getAllByCoachId(this.user.accId).subscribe(req => {
        this.populateFilters();
        this.myRequests = req;
        this.myReservations = res.filter(r=> r.resGroup == null);
      });
    });
    
  }

  logOut() {
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    this.user = new Account();
    localStorage.clear();
    this.router.navigate([""]);
  }

  acceptStudent(obj: StudentCoach){
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    this.studentCoachService.accept(obj.sctId).subscribe(()=>{
      this.studentCoachService.getAllByCoachId(this.user.accId).subscribe(req => {
        this.myRequests = req;
        this.successMsg1 = "Zahtev uspešno prihvaćen!";
      });
    });
    
  }

  declineStudent(obj: StudentCoach){
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    this.studentCoachService.cancel(obj.sctId).subscribe(()=>{
      this.studentCoachService.getAllByCoachId(this.user.accId).subscribe(req => {
        this.myRequests = req;
        this.successMsg1 = "Zahtev uspešno odbijen!";
      });
    });
  }

  deleteRequest(obj: StudentCoach){
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    this.studentCoachService.delete(obj.sctId).subscribe(()=>{
      this.studentCoachService.getAllByCoachId(this.user.accId).subscribe(req => {
        this.myRequests = req;
        this.successMsg1 = 'Zahtev uspešno obrisan!';
      });
      
    });
  }

  acceptReservation(obj: Reservation) {
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    this.reservationService.acceptReservation(obj.resId).subscribe(() => {
      this.reservationService.getReservationsByCoachId(this.user.accId).subscribe(res => {
        this.myReservations = res.filter(r=> r.resGroup == null);
        this.successMsg2 = "Zahtev uspešno prihvaćen!";
      });
    });
  }

  cancelReservation(obj: Reservation) {
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    this.reservationService.cancel(obj.resId).subscribe(() => {
      this.reservationService.getReservationsByCoachId(this.user.accId).subscribe(res => {
        this.myReservations = res.filter(r=> r.resGroup == null);;
        this.successMsg2 = "Zahtev uspešno odbijen!"
      });
    });
  }

  populateFilters() {
    this.filterStatusOptions1 = [];
    this.filterStatusOptions1.push({
      label: '',
      value: null
    });

    this.filterStatusOptions2 = [];
    this.filterStatusOptions2.push({
      label: '',
      value: null
    });

    this.statusService.getAllStatusesByTable("student_coach").subscribe(res => {
      res.forEach(r => {
        this.filterStatusOptions1.push({
          label: r.staDesc,
          value: r
        });
      });
    });

    this.statusService.getAllStatusesByTable("reservation").subscribe(res => {
      res.forEach(r => {
        this.filterStatusOptions2.push({
          label: r.staDesc,
          value: r
        });
      });
    });
  }

  filterTable1(){
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    if(this.filterStatus1 == null) {
      this.studentCoachService.getAllByCoachId(this.user.accId).subscribe( res => {
        this.myRequests = res;
        return;
      });
       
    }
    this.studentCoachService.getAllByCoachId(this.user.accId).subscribe( res => {
      let filterRes: StudentCoach[] = res;

      if(this.filterStatus1){
        filterRes = filterRes.filter(res => {
          return res.sctStatus.staId == this.filterStatus1?.staId;
        });
      }

      this.myRequests = filterRes;
    });
  }

  cancelFilter1(){
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    this.filterStatus1 = this.filterStatusOptions1.find(res => res.value == null);

    this.studentCoachService.getAllByCoachId(this.user.accId).subscribe( res => {
      this.myRequests = res;
    });
  }


  filterTable2(){
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';

    if(this.filterStatus2 == null) {
      this.studentCoachService.getAllByCoachId(this.user.accId).subscribe( res => {
        this.myRequests = res;
        return;
      });
    }

    this.reservationService.getReservationsByCoachId(this.user.accId).subscribe( res => {
      let filterRes: Reservation[] = res.filter(r=> r.resGroup == null);;

      if(this.filterStatus2){
        filterRes = filterRes.filter(res => {
          return res.resStatus.staId == this.filterStatus2?.staId;
          });
      }

      this.myReservations = filterRes;
    });

    
  }

  cancelFilter2() {
    this.errMsg1 = '';
    this.successMsg1 = '';

    this.errMsg2 = '';
    this.successMsg2 = '';
    
    this.filterStatus2 = this.filterStatusOptions2.find(res => res.value == null);
    this.reservationService.getReservationsByCoachId(this.user.accId).subscribe( res => {
      this.myReservations = res.filter(r=> r.resGroup == null);;
    });
  }

}
