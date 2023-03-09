import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { catchError, map} from "rxjs/operators"
import { HttpClient} from "@angular/common/http"
import { ResponseWrapper } from "../model/sys/response-wrapper";
import Utils from "../sys/utils";
import { PriceList } from "../model/price_list";

@Injectable()
export class PriceListService {
    private apiUrl = '/api';
    private priceListUrl = this.apiUrl + '/priceList'
    constructor(private httpClient: HttpClient){

    }

    private static handleError(error: Response | any){
        return Utils.handleError(error);
    }

    getAllPriceList(): Observable<PriceList[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.apiUrl}/getAllPriceList`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(PriceListService.handleError)
        );
    }    
}