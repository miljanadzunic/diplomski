import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { Role } from '../model/role';
import { AccountService } from '../service/account.service';
import { RoleService } from '../service/role.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit{
  
  user: Account = new Account();
  repeatPassword: string = '';
  userType: string = '';
  errMsg: string = '';
  regex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+{};:,<.>])[A-Za-z\d!@#$%^&*()\-_=+{};:,<.>]{8,}$/;
  badImageData: boolean;
  roles: any;
  userPhoto?: File;

  constructor(private router: Router, private accountService: AccountService, private roleService: RoleService){
    this.populateRoles();
    this.badImageData = false;
    this.errMsg = "";
    this.user = new Account();
  }

  ngOnInit(): void {
    
  }

  checkChoosenPhoto(event: any){
    console.log(event);
    if(event.srcElement.files[0].type != "image/png" && event.srcElement.files[0].type != "image/jpeg"){
      this.badImageData = true;
      return;
    }
    var img = new Image();
    img.src = window.URL.createObjectURL(event.srcElement.files[0]);
    img.onload = () =>{
      console.log(img.width); console.log(img.height);
      if(img.width != 300 || img.height != 300){
        this.user.accPhoto = "";
        return;
      }
    }
    this.user.accPhoto = event.srcElement.files[0].name;
    console.log(this.user.accPhoto);
  }

  populateRoles(){
    this.roles = [];
    this.roles.push({
      label: 'Izabrati tip',
      value: null
    });

    this.roleService.getAllRoles().subscribe(res => {
      res.map(role => {
        let rolDesc = '';
        switch (role.rolName){
          case 'admin': rolDesc = 'admin'; break;
          case 'coach': rolDesc = 'trener'; break;
          case 'student': rolDesc = 'ucenik'; break;
          case 'recreationist': rolDesc = 'rekreativac'; break;
        }
        this.roles.push({
          label: rolDesc,
          value: role
        });
      });
    });
  }
 
  registration(){
    this.errMsg = "";
    if(this.user.accName == undefined || this.user.accName == null
      || this.user.accSurname == undefined || this.user.accSurname == null
      || this.user.accUsername == undefined || this.user.accUsername == null 
      || this.user.accPassword == undefined || this.user.accPassword == null
      || this.repeatPassword == undefined || this.repeatPassword == null 
      || this.user.accBirthday == undefined || this.user.accBirthday == null 
      || this.user.accPhone == undefined || this.user.accPhone == null
      || this.user.accEmail == undefined || this.user.accEmail == null 
      || this.user.accPhoto == undefined || this.user.accPhoto == null
      || this.user.accRole == undefined || this.user.accRole == null){
        this.errMsg = "Niste uneli sve podatke!";
        return;
      }
    
    // if(!this.regex.test(this.user.accPassword)){
    //   this.errMsg = "Lozinka nije u odgovarajućem formatu";
    //   return;
    // }

    // if(!this.regex.test(this.repeatPassword)){
    //   this.errMsg = "Ponovljena lozinka nije u odgovarajućem formatu";
    //   return;
    // }

    if(this.repeatPassword != this.user.accPassword){
      this.errMsg = "Lozinke se ne poklapaju!";
      return;
    }

    if(this.badImageData){
      this.errMsg = "Slika mora biti u .jpeg ili .png formatu!";
      return;
    }

    if(this.user.accPhoto == ""){
      this.errMsg = "Minimalna i maksimalna širina i visina slike mora biti 300px!";
        return;
    }

    this.accountService.create(this.user).subscribe(res => {
      if (res != null && res != undefined) {
        console.log(res);
        this.router.navigate(['']);
      } else {
        this.errMsg = "Greska prilikom registracije!";
      }
    }, error => {
      let errMsg = (error as any);
      if (errMsg.includes("ACCOUNT_USERNAME_EXISTS")) {
        this.errMsg = "Username vec postoji!";
      } else if (errMsg.includes("ACCOUNT_EMAIL_EXISTS")) {
        this.errMsg = "Email vec postoji!"
      } else {
        this.errMsg = errMsg;
      }
    })
    
  }
}
