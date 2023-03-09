import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { AccountService } from '../service/account.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  user: Account | null;

  constructor(private router: Router, private accountService: AccountService) { 
    this.user = null;
  }

  ngOnInit(): void {
    this.router.events.subscribe((ev)=>{
      if(localStorage && localStorage.getItem('user')){
        let notParsed = localStorage.getItem('user');
        if(notParsed){
          this.user = JSON.parse(notParsed);
        }
      }
    });
  }



  logOut(){
    this.user = null;
    localStorage.clear();
    this.router.navigate([""]);
  }
}
