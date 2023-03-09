import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/account';
import { Court } from '../model/court';
import { CourtType } from '../model/court-type';
import { CourtTypeService } from '../service/court-type.service';
import { CourtService } from '../service/court.service';

@Component({
  selector: 'app-court',
  templateUrl: './court.component.html',
  styleUrls: ['./court.component.css']
})
export class CourtComponent implements OnInit{
  errMsg: string = '';
  emptymessage: boolean = false;

  user: Account = new Account();
  role: any = null;
  
  allCourts: Court[] = [];
  allCourtTypes: any[] = [];
  courtTypeFilter: CourtType | null = null;

  constructor (private courtService: CourtService, private courtTypeService: CourtTypeService, private router: Router) {
  }

  ngOnInit(): void {
    this.errMsg = '';
   
    let newUser = localStorage.getItem('user');
    if(newUser != null){
      this.user = JSON.parse(newUser)
      this.role = this.user.accRole.rolName;
    } else {
      this.role = null;
    }
    

    this.allCourtTypes.push({
      label: '---',
      value: null
    });

    this.courtTypeService.getAllCourtTypes().subscribe(courtTypes => {
      courtTypes.map(courtType => {
        this.allCourtTypes.push({
          label: courtType.crtDesc,
          value: courtType
        });
      });

      this.courtService.getAllCourts().subscribe(courts => {
        if(courts.length <= 0) {
          this.emptymessage = true;
        } else {
          this.emptymessage = false;
          this.allCourts = courts;
        }
      
      }, error => {
        this.emptymessage = true;
        this.errMsg = error as any;
      });
    })
  }

  filterByType(){
    this.courtService.getAllCourts().subscribe(courts => {
      if(this.courtTypeFilter != null){
        this.allCourts = courts.filter(res => {
          return res.corType.crtName === this.courtTypeFilter?.crtName;
        });
      } else {
        this.allCourts = courts;
      }
    });
  }

  logOut() {
    this.errMsg = '';
    localStorage.clear();
    this.router.navigate([""]);
  }

  reserve(obj: Court){

  }

}
