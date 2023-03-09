import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';

@Component({
  selector: 'app-about-account',
  templateUrl: './about-account.component.html',
  styleUrls: ['./about-account.component.css']
})
export class AboutAccountComponent implements OnInit {
  user: Account = new Account();

  constructor(private router: Router){}

  ngOnInit(): void {
    let newUser = localStorage.getItem('user');
    if(newUser != null){
      this.user = JSON.parse(newUser)
    } else {
      this.user = new Account();
      this.logOut();
    }
    
  }

  logOut() {
    this.user = new Account();
    localStorage.clear();
    this.router.navigate([""]);
  }

}
