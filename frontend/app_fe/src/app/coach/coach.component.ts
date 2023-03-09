import { Component, OnInit } from '@angular/core';
import { Account } from '../model/account';
import { StudentCoach } from '../model/student_coach';
import { AccountService } from '../service/account.service';
import { StudentCoachService } from '../service/student-coach.service';

@Component({
  selector: 'app-coach',
  templateUrl: './coach.component.html',
  styleUrls: ['./coach.component.css']
})
export class CoachComponent implements OnInit{

  errMsg: string = '';
  successMsg: string = '';

  public allCoaches: Account[] = [];

  user: Account = new Account();
  role: any = null;

  hasCoach: boolean = false;

  constructor(private accountService: AccountService,
              private studentCoachService: StudentCoachService) {
    
  }

  ngOnInit(): void {
    this.errMsg = '';
    this.successMsg = '';
    this.hasCoach = false;

    let newUser = localStorage.getItem('user');
    if(newUser != null){
      this.user = JSON.parse(newUser)
      this.role = this.user.accRole.rolName;
    } else {
      this.user = new Account();
      this.role = null;
    }
    
    if (this.role == 'student'){
      this.accountService.getAccountsByRole('coach').subscribe(res => {
        this.studentCoachService.getAllByStudentIdAccepted(this.user.accId).subscribe(sct=>{
          if(sct && sct.length > 0){
            this.hasCoach = true;
          }
        });
        this.allCoaches = res;
      });
    } else {
      this.accountService.getAccountsByRole('coach').subscribe(res => {
        this.allCoaches = res;
      });
    }
    
  }
  
  reserve(obj: Account){
    this.errMsg = '';
    this.successMsg = '';

    let request: StudentCoach = new StudentCoach();
    request.sctStudent = this.user;
    request.sctCoach = obj;
  
    this.studentCoachService.create(request).subscribe(res => {
      let newRwq = res;
      console.log("ZAHTEV OD STUDENTA KA TRENERU:")
      console.log(newRwq);
      this.successMsg = "Zahtev uspešno poslat!";
    }, error => {
      let err = (error as  any);
      if(err.includes("ERROR_REQUEST_ALREADY_SENT")) {
        this.errMsg = "Zahtev je već poslat!";
      } else {
        this.errMsg = err;
      }
      
    });
  }

}
