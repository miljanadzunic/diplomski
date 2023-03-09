import { Component, OnInit } from '@angular/core';
import { PriceList } from '../model/price_list';
import { PriceListService } from '../service/price-list.service';

@Component({
  selector: 'app-price-list',
  templateUrl: './price-list.component.html',
  styleUrls: ['./price-list.component.css']
})
export class PriceListComponent implements OnInit{

  priceList: PriceList[] = [];

  constructor(private priceListService: PriceListService){
    
  }

  ngOnInit(): void {
    this.priceListService.getAllPriceList().subscribe(res => {
      this.priceList = res;
    });
  }
}
