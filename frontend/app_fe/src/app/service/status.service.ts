import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { catchError, map} from "rxjs/operators"
import { HttpClient} from "@angular/common/http"
import { ResponseWrapper } from "../model/sys/response-wrapper";
import Utils from "../sys/utils";
import { Status } from "../model/status";

@Injectable()
export class StatusService {
    private apiUrl = '/api';
    private statusUrl = this.apiUrl + '/status'
    constructor(private httpClient: HttpClient){

    }

    private static handleError(error: Response | any){
        return Utils.handleError(error);
    }

    getAllStatuses(): Observable<Status[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.apiUrl}/getAllStatuses`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StatusService.handleError)
        );
    }    

    getAllStatusesByTable(table: string): Observable<Status[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.apiUrl}/getStatusesByTable/${table}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StatusService.handleError)
        );
    }   
}