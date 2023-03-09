import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { Group } from '../model/group';
import { GroupService } from '../service/group.service';
import { StudentCoachService } from '../service/student-coach.service';

@Component({
  selector: 'app-coach-groups',
  templateUrl: './coach-groups.component.html',
  styleUrls: ['./coach-groups.component.css']
})
export class CoachGroupsComponent implements OnInit{
  errMsg: string = '';
  successMsg: string = '';

  user: Account = new Account();

  myGroups: any[] = [];

  constructor(private router: Router, 
              private groupService: GroupService,
              private studentCoachService: StudentCoachService) {}

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
    this.getGroups();
  }

  getGroups(){
    this.errMsg = '';
    this.successMsg = '';

    this.myGroups = [];
    this.groupService.getGroupsByCoachId(this.user.accId).subscribe(grps => {
      grps.forEach(grp => {
        let students: Account[] = [];
        this.studentCoachService.getAllByGroupId(grp.grpId).subscribe(scts => {
          scts.forEach(sct => {
            students.push(sct.sctStudent);
          });
          this.myGroups.push({
            value: grp,
            students: students
          });
        });
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

  delete(obj: Group){
    this.groupService.delete(obj.grpId).subscribe(()=>{
      this.getGroups();
      this.successMsg = "Grupa uspešno izbrisana!";
    })
  }
}
