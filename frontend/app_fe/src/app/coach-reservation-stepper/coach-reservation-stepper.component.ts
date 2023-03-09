import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { Reservation } from '../model/reservation';
import { CourtService } from '../service/court.service';
import { ReservationService } from '../service/reservation.service';
import { StudentCoachService } from '../service/student-coach.service';

@Component({
  selector: 'app-coach-reservation-stepper',
  templateUrl: './coach-reservation-stepper.component.html',
  styleUrls: ['./coach-reservation-stepper.component.css']
})
export class CoachReservationStepperComponent {
  isLinear = false;
  firstFormGroup?: FormGroup;
  secondFormGroup?: FormGroup;

  errMsg: string = '';
  successMsg: string = '';

  user: Account = new Account();
  myStudents: any[] = [];
  allCourts: any[] = [];
  myGroups: any[] = [];
  terminsNum: any[] = [];
  racketsNum: any[] = [];
  newReservation: Reservation = new Reservation();

  minDate: Date = new Date();

  timeChips: any[] = [];

  trainingType: string | null = null;

  constructor(private router: Router,
              private courtService: CourtService,
              private reservationService: ReservationService,
              private studentCoachService: StudentCoachService) {}

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
    
    this.newReservation.resCoach = this.user;

    this.myStudents = [];
    this.myGroups = [];
    this.allCourts = [];

    this.allCourts.push({
      label: '---',
      value: null
    });

    this.myStudents.push({
      label: '---',
      value: null
    });

    this.myGroups.push({
      label: '---',
      value: null
    });

    this.populateTerminNum();
    this.newReservation.resTerminNum = 1;

    this.populateRacketNum();
    this.newReservation.resRacketNum = 0;
    this.newReservation.resTimeStart = 0;

    this.studentCoachService.getByCoachAccepted(this.user.accId).subscribe(req => {
      if(req){
        req.map(res => {
          this.myStudents.push({
            label: res.sctStudent.accName + ' ' + res.sctStudent.accSurname,
            value: res.sctStudent
          });

          if(res.sctGroup){
            let found: boolean = false;
            this.myGroups.forEach(mg => {
              if( mg.value && mg.value.grpId == res.sctGroup.grpId){
                found = true;
              }
            });
    
            if(found == false) {
              this.myGroups.push({
                label: res.sctGroup.grpName,
                value: res.sctGroup
              });
            }
          }
        });
      }

      this.courtService.getAllCourts().subscribe(courts => {
        if(courts){
          courts.map(res => {
            this.allCourts.push({
              label: res.corName,
              value: res
            });
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

  findNotTakenHours(res: Reservation[]){
    if(res == null || res == undefined){
      this.errMsg = "Greska!";
      return;
    }

    let takenHours: number[] = [];
    let today = new Date();
    let currHour = today.getHours();
    let currMin = today.getMinutes();
    let disable = today.getDate() == this.newReservation.resDate.getDate() && 
                  today.getMonth() == this.newReservation.resDate.getMonth() && 
                  today.getFullYear() == this.newReservation.resDate.getFullYear();
    res.forEach(r=>{
      for(let i = r.resTimeStart; i < r.resTimeEnd; i++){
        takenHours.push(i);
      }
    });
 
    for(let i = 7; i < 22; i++){
      let found = takenHours.find(th => th == i);
    
      this.timeChips.push({
        label: (i < 10) ? '0' + i + ':00' : i + ':00',
        value: i,
        checked: false,
        disabled: found  || (disable && (i <= currHour || (i == currHour + 1 && currMin > 15)))
      });
    }
  }

  populateTimeChips(){
    this.errMsg = '';
    this.successMsg = '';

    if(this.newReservation && this.newReservation.resCourt == null || this.newReservation.resDate == null || this.trainingType == null){
      return;
    }

    if(this.trainingType == 'group' && this.newReservation && this.newReservation.resGroup == null){
      return;
    }

    if(this.trainingType == 'oneOnOne' && this.newReservation && this.newReservation.resStudent == null){
      return;
    }

    this.timeChips = [];
    if (this.newReservation && this.newReservation.resCoach && this.trainingType == 'group' && this.newReservation.resGroup) {
      this.reservationService.getReservationsByDateCourtCoachGroupStudents(this.newReservation.resDate, this.newReservation.resCourt.corId, this.newReservation.resCoach.accId, this.newReservation.resGroup.grpId)
      .subscribe(res => {
        
        this.findNotTakenHours(res);
      });
    }
    if(this.newReservation && this.newReservation.resCoach && this.trainingType == 'oneOnOne' && this.newReservation.resStudent){
      this.reservationService.getReservationsByDateStudentCourtCoachGroup(this.newReservation.resDate, this.newReservation.resStudent.accId, this.newReservation.resCourt.corId, this.newReservation.resCoach.accId).subscribe(res => {
        this.findNotTakenHours(res);
      });
    }

    debugger;

  }

  populateTerminNum() {
    this.terminsNum.push({
      label: '1h',
      value: 1
    });

    // this.terminsNum.push({
    //   label: '2h',
    //   value: 2
    // });
  }

  populateRacketNum(){
    for(let i = 0; i <=4; i++){
      this.racketsNum.push({
        label: '' + i,
        value: i
      });
    }
    
  }
  chipToggle(obj: any){
    if(obj.disabled){
      obj.checked = false;
      return;
    }

    obj.checked = !obj.checked;
    if (obj.checked){
      this.newReservation.resTimeStart = obj.value;
    } else {
      this.newReservation.resTimeStart = 0;
    }
    
    this.timeChips.forEach(res => {
      if (res.value != obj.value) {
        res.checked = false;
      }
    })
  }

  onDateSelect(){
    if(this.newReservation && this.newReservation.resCourt == null){
      this.errMsg = "Niste izabrali teren!";
      return;
    }
    
    if(this.trainingType == null) {
      this.errMsg = "Niste izabrali tip treninga!"
      return;
    }
  
    if(this.trainingType == 'oneOnOne' && (this.newReservation.resStudent == null || this.newReservation.resStudent == undefined)){
      this.errMsg = "Niste izabrali učenika!"
      return;
    }

    if(this.trainingType == 'group' && (this.newReservation.resGroup == null || this.newReservation.resGroup == undefined)){
      this.errMsg = "Niste izabrali grupu!"
      return;
    }

    if(this.newReservation && this.newReservation.resDate == null){
      this.errMsg = "Niste izabrali datum!";
      return;
    }

  
    this.populateTimeChips();
  }

  reserve(){
    this.errMsg = '';
    this.successMsg = '';

    if(this.newReservation.resTimeStart == 0){
      this.errMsg = "Niste uneli vreme!"
      return;
    }

    this.newReservation.resTimeEnd = this.newReservation.resTimeStart + this.newReservation.resTerminNum;

    this.reservationService.create(this.newReservation).subscribe(res =>{
      if(res){
        this.ngOnInit();
        this.successMsg = "Uspešno ste rezervisali termin!";
      } else {
        this.errMsg = "Greška pri rezervaciji termina!";
      }
    }, error => {
      this.errMsg = error as any;
    });

  }

  styleChip(obj: any){
    if(obj.disabled) {
      return 'p-chip-disabled';
    } else {
      return obj.checked ? 'filter-chip-checked custom-p-chip' : 'custom-p-chip'
    }
    
  }
  showTime(): boolean {
    if (this.newReservation && this.newReservation.resCourt && this.trainingType){
      if(this.trainingType == 'group' && this.newReservation.resGroup && this.newReservation.resDate){
        return true;
      }
      if(this.trainingType == 'oneOnOne' && this.newReservation.resStudent && this.newReservation.resDate){
        return true;
      }
      return false;
    } else {
      return false;
    }
  }
}
