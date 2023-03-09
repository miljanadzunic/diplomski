import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { AccountService } from '../service/account.service';
import { ReservationService } from '../service/reservation.service';

@Component({
  selector: 'app-search-coaches',
  templateUrl: './search-coaches.component.html',
  styleUrls: ['./search-coaches.component.css']
})
export class SearchCoachesComponent implements OnInit{
  errMsg: string = '';
  user: Account = new Account();
  allCoaches: Account[] = [];

  filterDate: Date | null = null;
  minDate: Date = new Date();

  filterTimeOptions: any[] = [];
  filterTime: number | null = null;

  freeCoaches: Account[] = [];
  showTable: Boolean = false;

  constructor(private router: Router, private reservationService: ReservationService, private coachService: AccountService) {}

  ngOnInit(): void {
    this.errMsg = '';
    this.showTable = false;

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

    this.populateTimeOptions();
    this.coachService.getAccountsByRole("coach").subscribe(res => {
      this.allCoaches = res;
    });
  }

  logOut() {
    this.showTable = false;
    this.errMsg = '';
    localStorage.clear();
    this.router.navigate([""]);
  }

  populateTimeOptions(){
    this.filterTimeOptions = [];
    
    this.filterTimeOptions.push({
      label: '',
      value: null
    });

    for(let i = 7; i < 22; i++){
      this.filterTimeOptions.push({
        label: (i < 10) ? '0' + i + ":00": i + ":00",
        value: i
      });
    }
  }

  searchFreeCoaches(){
    this.errMsg = '';
    this.showTable = false;

    if(this.filterDate == null && this.filterTime == null){
      this.errMsg = "Niste uneli tražene parametre!"
      return;
    }

    if(this.filterDate && this.filterTime) {
      this.showTable = true;
      this.freeCoaches = [];
      this.reservationService.getReservationsByDateAndTimeStartForCoaches(this.filterDate, this.filterTime).subscribe(res=>{
       
        if (res) {
          this.allCoaches.forEach(coach => {
            let found = res.find(c => c.resCoach?.accId == coach.accId);
            if(!found){
              this.freeCoaches.push(coach);
            }
          });
      
        }
      });
    }

  }

  checkTime(){
    let today = new Date();
    if (this.filterDate && 
        this.filterDate.getDate() == today.getDate() &&
        this.filterDate.getMonth() == today.getMonth() && 
        this.filterDate.getFullYear() == today.getFullYear())
      {

        let currMin = today.getMinutes();
        let currHour = today.getHours();
        this.filterTimeOptions = [];
        this.filterTimeOptions.push({
          label: '',
          value: null
        });
    
        for(let i = 7; i < 22; i++){
          if(i <= currHour || (i == currHour + 1 && currMin > 15)){
            
          } else {
            this.filterTimeOptions.push({
              label: (i < 10) ? '0' + i + ":00": i + ":00",
              value: i
            });
          }    
      }
    } else {
      this.populateTimeOptions();
    }
  }
}
