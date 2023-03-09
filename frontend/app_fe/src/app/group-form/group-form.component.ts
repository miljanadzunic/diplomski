import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { Group } from '../model/group';
import { StudentCoach } from '../model/student_coach';
import { GroupService } from '../service/group.service';
import { ReservationService } from '../service/reservation.service';
import { StudentCoachService } from '../service/student-coach.service';

@Component({
  selector: 'app-group-form',
  templateUrl: './group-form.component.html',
  styleUrls: ['./group-form.component.css']
})

export class GroupFormComponent implements OnInit{
  
  errMsg: string = '';
  successMsg: string = '';

  user: Account = new Account();
  newGroup: Group = new Group();
  myRequests: StudentCoach[] = [];

  myStudentsOptions: any[] = [];
  selectedStudents: any[] = [];

  groupNumOptions: any[] = [];

  constructor(private reservationService: ReservationService,
              private studentCoachService: StudentCoachService,
              private groupService: GroupService,
              private router: Router){}

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

    if(this.user && this.user?.accRole.rolName != "coach"){
      this.logOut();
      return;
    } 
    this.populateFields();
    this.myStudentsOptions = [];
    this.studentCoachService.getAllByCoachId(this.user.accId).subscribe(res => {
      res.forEach(r=> {
        if(r.sctStatus.staCode == 'STA_PRIHVACEN' && r.sctGroup == null){
          this.myStudentsOptions.push({
            label: r.sctStudent.accName + ' ' +  r.sctStudent.accSurname,
            value: r.sctStudent
          });
        }
      });
    });
  }

  logOut() {
    this.errMsg = '';
    this.successMsg = '';

    this.user = new Account();
    localStorage.clear();
    this.router.navigate([""]);
  }

  populateFields() {
    this.groupNumOptions = [];
   
    for(let i = 2; i < 4; i++){
      this.groupNumOptions.push({
        label: i,
        value: i
      });
    }

  }

  makeGroup(){
    this.successMsg = '';
    this.errMsg = '';

    if(this.newGroup.grpStudentsNum != this.selectedStudents.length){
      this.errMsg = "Broj članova grupe se ne poklapa!";
      return;
    }

    if(this.selectedStudents.length > 3 || this.selectedStudents.length <= 1){
      this.errMsg = "Grupa mora sadržati minimalno 2, a maksimalno 3 člana!";
      return;
    }

    this.newGroup.grpCoach = this.user;

    let studentsHelp: number[] = [];
    this.selectedStudents.forEach(ss => {
      studentsHelp.push(ss.value.accId);
    });
    this.groupService.create(this.newGroup, studentsHelp).subscribe(res => {
      this.myStudentsOptions = [];
      this.studentCoachService.getAllByCoachId(this.user.accId).subscribe(res => {
        res.forEach(r=> {
          if(r.sctStatus.staCode == 'STA_PRIHVACEN' && r.sctGroup == null){
            this.myStudentsOptions.push({
              label: r.sctStudent.accName + ' ' +  r.sctStudent.accSurname,
              value: r.sctStudent
            });
          }
        });
        this.successMsg = "Grupa je uspešno napravljena!";
      });
    
    }, error => {
      if(this.errMsg != ''){
        this.errMsg = this.errMsg + " " + (error as any);
      }
    });

  }



}
