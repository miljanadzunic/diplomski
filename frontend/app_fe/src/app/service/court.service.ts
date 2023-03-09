import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { catchError, map} from "rxjs/operators"
import { HttpClient} from "@angular/common/http"
import { ResponseWrapper } from "../model/sys/response-wrapper";
import Utils from "../sys/utils";
import { Court } from "../model/court";

@Injectable()
export class CourtService {
    private apiUrl = '/api';
    private courtsUrl = this.apiUrl + '/courts'
    constructor(private httpClient: HttpClient){

    }

    private static handleError(error: Response | any){
        return Utils.handleError(error);
    }

    getAllCourts(): Observable<Court[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.apiUrl}/getAllCourts`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(CourtService.handleError)
        );
    }    
}