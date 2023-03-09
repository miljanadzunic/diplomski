import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';

@Component({
  selector: 'app-recreationist-home',
  templateUrl: './recreationist-home.component.html',
  styleUrls: ['./recreationist-home.component.css']
})

export class RecreationistHomeComponent implements OnInit{

  errMsg: string = '';
  successMsg: string = '';

  user: Account | null = new Account();

  constructor(private router: Router) {}

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
    
    if(this.user && this.user?.accRole.rolName != "recreationist"){
      this.logOut();
      return;
    }  
  }

  logOut() {
    this.errMsg = '';
    this.successMsg = '';
    this.user = null;
    localStorage.clear();
    this.router.navigate([""]);
  }

  goToReservations(){
    this.router.navigate(["reservation"]);
  }

  goToCoaches(){
    this.router.navigate(["search-coaches"]);
  }

  goToCourts(){
    this.router.navigate(["search-courts"]);
  }

}
