import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { Reservation } from '../model/reservation';
import { ReservationService } from '../service/reservation.service';
import { StudentCoachService } from '../service/student-coach.service';

@Component({
  selector: 'app-my-groups',
  templateUrl: './my-groups.component.html',
  styleUrls: ['./my-groups.component.css']
})
export class MyGroupsComponent implements OnInit{
  errMsg: string = '';
  successMsg: string = '';

  user: Account = new Account();
  role: any = null;

  groupReservations: any[] = [];

  constructor(private reservationService: ReservationService,
              private studenCoachService: StudentCoachService,
              private router: Router) {}

  ngOnInit(): void {
    this.errMsg = '';
    this.successMsg = '';

    let newUser = localStorage.getItem('user');
    if(newUser != null){
      this.user = JSON.parse(newUser)
    } else {
      this.logOut();
      return;
    }
    
    if(this.user && this.user.accRole.rolName != "coach") {
      this.logOut();
      return;
    } 

    this.getRequests();
  }

  getRequests(){
    this.groupReservations = [];
    
    this.reservationService.getReservationsByCoachIdAndGroupNotNull(this.user.accId).subscribe(res => {
      res.forEach(r => {
        let students = '';
        this.studenCoachService.getAllByGroupId(r.resGroup.grpId).subscribe(sct => {
          for(let i = 0; i < sct.length; i++){
            students = students + sct[i].sctStudent.accName + " " +  sct[i].sctStudent.accSurname;
            if( i != sct.length - 1){
              students += ", "
            }
          }

          this.groupReservations.push({
            value: r,
            students: students
          });
        });
        
      });
      
    }, error => {
      this.errMsg = (error as any);
    });
  }

  logOut() {
    this.errMsg = '';
    this.successMsg = '';
    this.user = new Account();
    localStorage.clear();
    this.router.navigate([""]);
  }


  deleteReservation(obj: Reservation) {
    this.errMsg = '';
    this.successMsg = '';

    this.reservationService.delete(obj.resId).subscribe(res => {
      this.getRequests();
      this.successMsg = "Termin je uspešno obrisan!"
    }, error => {
      this.errMsg = (error as any);
    });
  }

  cancelReservation(obj: Reservation){
    this.errMsg = '';
    this.successMsg = '';

    this.reservationService.cancel(obj.resId).subscribe(res=>{
      this.getRequests();
      this.successMsg = "Termin je uspešno otkazan!"
    });
  }
}
