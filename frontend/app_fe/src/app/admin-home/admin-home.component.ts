import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { AccountService } from '../service/account.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {
  errMsg: string = '';
  public allAccounts: Account[] = [];

  user: Account | null = new Account();
  role: any = null;

  constructor(private accountService: AccountService, private router: Router) {
    
  }
  ngOnInit(): void {
    this.errMsg = '';

    let newUser = localStorage.getItem('user');
    if(newUser != null){
      this.user = JSON.parse(newUser)
    } else {
      this.user = null;
      this.logOut();
    }
    
    if(this.user && this.user?.accRole.rolName != "admin"){
      this.logOut();
      return;
    }  
    this.accountService.getAllAccounts().subscribe(res => {
      this.allAccounts = res;
    });
  }

  logOut() {
    this.errMsg = '';
    this.user = null;
    localStorage.clear();
    this.router.navigate([""]);
  }

  approveAccount(obj: Account) {
    this.accountService.enableAccount(obj).subscribe(acc => {
      let pom = acc;
      debugger;
      this.accountService.getAllAccounts().subscribe(res => {
        this.allAccounts = res;
      });
    });
  }

  // deleteAccount(obj: Account) {c
  // }
}
