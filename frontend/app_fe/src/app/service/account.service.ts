import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { catchError, map} from "rxjs/operators"
import { Account } from "../model/account";
import { HttpClient} from "@angular/common/http"
import { ResponseWrapper } from "../model/sys/response-wrapper";
import Utils from "../sys/utils";

@Injectable()
export class AccountService {
    private apiUrl = '/api';
    private loginUrl = '/api/login';
    private accountUrl = this.apiUrl + '/accounts'
    constructor(private httpClient: HttpClient){

    }

    private static handleError(error: Response | any){
        return Utils.handleError(error);
    }

    getAllAccounts(): Observable<Account[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.apiUrl}/getAllAccounts`).pipe(
            map(rw => {
                return rw.data;
            })
        );
    }

    login(username: string, password: string): Observable<Account>{
        let data = {
            username: username,
            password: password
        }
        return this.httpClient.post<ResponseWrapper>(`${this.loginUrl}`, data).pipe(
            map ( rw => {
                return rw.data;
            }), catchError(AccountService.handleError)
        );
    }

    create(res: Account): Observable<Account>{
        return this.httpClient.post<ResponseWrapper>(`${this.accountUrl}/create`, res).pipe(
            map(rw => {
                return rw.data;
            }), catchError(AccountService.handleError)
        );
    }

    getAccountsByRole(rolName: string): Observable<Account[]> {
        return this.httpClient.post<ResponseWrapper>(`${this.accountUrl}/findByRole`, rolName).pipe(
            map(rw => {
                return rw.data;
            }), catchError(AccountService.handleError)
        );
    }

    enableAccount(obj: Account): Observable<Account> {
        return this.httpClient.post<ResponseWrapper>(`${this.accountUrl}/enableAccount`, obj).pipe(
            map(rw => {
                return rw.data;
            }), catchError(AccountService.handleError)
        );
    }
}