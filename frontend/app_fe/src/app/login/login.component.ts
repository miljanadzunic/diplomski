import { Component, OnInit} from '@angular/core';
import { AccountService } from '../service/account.service';
import { Account } from '../model/account';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  allAccounts: Account[] = [];
  constructor(private router: Router, private accountService: AccountService) { }

  ngOnInit(): void {
   this.errMsg = '';
  }

  username: string = "";
  password: string = "";
  errMsg: string = "";

  getAllAccounts(){
    this.accountService.getAllAccounts().subscribe(res => {
      this.allAccounts = res;
      console.log(res);
    });
  }

  login(){
    console.log("LOGIN");
    this.errMsg = "";
    if (this.username == null || this.username == undefined || this.password == null || this.password == undefined){
      this.errMsg = "Niste uneli sve podatke!";
      return;
    }
  
    this.accountService.login(this.username, this.password).subscribe(res => {
      
      if(res != null && res != undefined){

        localStorage.setItem("user", JSON.stringify(res));
        if(res.accRole) {
          if(res.accRole.rolName == "admin") {
            this.router.navigate(['/admin-home']);
          } else if (res.accRole.rolName == "coach"){
            this.router.navigate(['/coach-home']);
          } else if(res.accRole.rolName == "student"){
            this.router.navigate(['/student']);
          } else if (res.accRole.rolName == "recreationist") {
            this.router.navigate(['/recreationist']);
          } else {
            this.errMsg = "Korisničko ime ili lozinka nisu ispravni!"
          }
        }
        
      } else {
        this.errMsg = "Korisnicko ime ili lozinka nisu ispravni!";
      }
    }, error => {
      let errMsg = (error as any);
      if(errMsg.includes("ERROR_ACCOUNT_NOT_ENABLED")){
        this.errMsg = "Profil nije aktivan. Molimo pokušajte kasnije."
      } else {
        this.errMsg = errMsg;
      }
      console.log(error);
    });
  }

  registration(){
    this.router.navigate(['registration']);
  }
}
