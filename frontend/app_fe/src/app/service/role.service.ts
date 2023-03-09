import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { map} from "rxjs/operators"
import { Account } from "../model/account";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http"
import { ResponseWrapper } from "../model/sys/response-wrapper";
import { Role } from "../model/role";

@Injectable()
export class RoleService {
    private apiUrl = '/api';
    private roleUrl = this.apiUrl + '/roles';

    constructor(private httpClient: HttpClient){

    }

    getAllRoles(): Observable<Role[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.roleUrl}/getAllRoles`).pipe(
            map(rw => {
                return rw.data;
            })
        );
    }

}