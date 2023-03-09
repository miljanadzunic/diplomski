import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { Reservation } from '../model/reservation';
import { AccountService } from '../service/account.service';
import { CourtService } from '../service/court.service';
import { ReservationService } from '../service/reservation.service';

@Component({
  selector: 'app-reservation-stepper',
  templateUrl: './reservation-stepper.component.html',
  styleUrls: ['./reservation-stepper.component.css']
})

export class ReservationStepperComponent implements OnInit{
  isLinear = false;
  firstFormGroup?: FormGroup;
  secondFormGroup?: FormGroup;

  errMsg: string = '';
  successMsg: string = '';

  user: Account = new Account();
  allCoaches: any[] = [];
  allCourts: any[] = [];
  terminsNum: any[] = [];
  racketsNum: any[] = [];
  newReservation: Reservation = new Reservation();

  minDate: Date = new Date();

  timeChips: any[] = [];

  constructor(private router: Router, 
              private accountService: AccountService, 
              private courtService: CourtService,
              private reservationService: ReservationService) {}

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
    
    if(this.user && this.user?.accRole.rolName != "recreationist"){
      this.logOut();
      return;
    }  
    
    this.newReservation.resStudent = this.user;

    this.allCoaches.push({
      label: '---',
      value: null
    });

    this.allCourts.push({
      label: '---',
      value: null
    });

    this.populateTerminNum();
    this.newReservation.resTerminNum = 1;

    this.populateRacketNum();
    this.newReservation.resRacketNum = 0;
    this.newReservation.resTimeStart = 0;

    this.accountService.getAccountsByRole('coach').subscribe(coaches => {
      if(coaches){
        coaches.map(res => {
          this.allCoaches.push({
            label: res.accName + ' ' + res.accSurname,
            value: res
          });
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

    if(this.newReservation && this.newReservation.resCourt == null || this.newReservation.resDate == null){
      return;
    }

    this.timeChips = [];
    if (this.newReservation && this.newReservation.resCoach) {
      this.reservationService.getReservationsByDateStudentCourtCoach(this.newReservation.resDate, this.newReservation.resStudent.accId, this.newReservation.resCourt.corId, this.newReservation.resCoach.accId)
      .subscribe(res => {
        this.findNotTakenHours(res);
      });
    } else {
      this.reservationService.getReservationsByDateStudentCourt(this.newReservation.resDate, this.newReservation.resStudent.accId, this.newReservation.resCourt.corId).subscribe(res => {
        this.findNotTakenHours(res);
      });
    }
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

    this.reservationService.create(this.newReservation).subscribe(res=>{
      debugger;
      if(res){
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

}
