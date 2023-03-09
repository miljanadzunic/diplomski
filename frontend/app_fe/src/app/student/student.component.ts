import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { StudentCoach } from '../model/student_coach';
import { StudentCoachService } from '../service/student-coach.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  errMsg: string = '';
  successMsg: string = '';

  user: Account = new Account();
  role: any = null;

  newRequests: StudentCoach[] = [];
  acceptedRequest: StudentCoach = new StudentCoach();
  teamMembers: Account[] = [];

  constructor(private studenCoachService: StudentCoachService, private router: Router) {}

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
    
    if(this.user && this.user?.accRole.rolName != "student"){
      this.logOut();
      return;
    } 

    this.getRequests();
  }

  getRequests(){
    this.newRequests = [];
    
    this.studenCoachService.getAllByStudent(this.user.accId).subscribe(res => {
      let allReq = res;
      let accReq = allReq.filter(req => req.sctStatus.staCode == 'STA_PRIHVACEN');

      if(accReq && accReq[0]) {
        this.acceptedRequest = accReq[0];
        if(accReq[0].sctGroup){
          this.studenCoachService.getAllByGroupId(accReq[0].sctGroup.grpId).subscribe(members =>{
            members.forEach(mem => {
              this.teamMembers.push(mem.sctStudent);
            });
          });
        }
      }

      allReq = res;
      this.newRequests = allReq.filter(req => req.sctStatus.staCode == 'STA_NOVI')
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


  deleteReservation(obj: StudentCoach) {
    this.errMsg = '';
    this.successMsg = '';

    this.studenCoachService.delete(obj.sctId).subscribe(res => {
      this.getRequests();
      this.successMsg = "Zahtev je uspešno obrisan!"
    }, error => {
      this.errMsg = (error as any);
    });
  }

  cancelCoach(){
    this.successMsg = '';
    this.errMsg = '';
    this.studenCoachService.cancel(this.acceptedRequest.sctId).subscribe( ()=>{
      this.getRequests();
      this.successMsg = "Trener uspešno uklonjen!";
    }, error => {
      let err = (error as any);
      if(err.includes("ERROR_STUDENT_HAS_GROUP")){
        this.errMsg = "Nije moguće otkazati trener jer je korisnik u grupi! Kontaktirajte trenera!";
      } else{
        this.errMsg = err;
      }
    });
  }
}
