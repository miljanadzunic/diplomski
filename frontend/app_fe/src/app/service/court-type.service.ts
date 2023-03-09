import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { catchError, map} from "rxjs/operators"
import { HttpClient} from "@angular/common/http"
import { ResponseWrapper } from "../model/sys/response-wrapper";
import Utils from "../sys/utils";
import { CourtType } from "../model/court-type";

@Injectable()
export class CourtTypeService {
    private apiUrl = '/api';
    private courtTypesUrl = this.apiUrl + '/courtTypes'
    constructor(private httpClient: HttpClient){

    }

    private static handleError(error: Response | any){
        return Utils.handleError(error);
    }

    getAllCourtTypes(): Observable<CourtType[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.apiUrl}/getAllCourtTypes`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(CourtTypeService.handleError)
        );
    }    
}