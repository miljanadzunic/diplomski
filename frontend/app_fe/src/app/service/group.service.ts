import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { catchError, map} from "rxjs/operators"
import { HttpClient} from "@angular/common/http"
import { ResponseWrapper } from "../model/sys/response-wrapper";
import Utils from "../sys/utils";
import { Group } from "../model/group";

@Injectable()
export class GroupService {
    private apiUrl = '/api';
    private groupUrl = this.apiUrl + '/groups'
    constructor(private httpClient: HttpClient){

    }

    private static handleError(error: Response | any){
        return Utils.handleError(error);
    }


    create(res: Group, students: number[]): Observable<Group>{
        let data = {
            'grpDesc': res.grpDesc,
            'grpName': res.grpName,
            'grpStudentsNum': res.grpStudentsNum,
            'coachId': res.grpCoach.accId,
            'student1': students[0],
            'student2': students[1],
            'student3': (students[2] != null)? students[2] : 0
        }
        
        return this.httpClient.post<ResponseWrapper>(`${this.groupUrl}/create`, data).pipe(
            map(rw => {
                return rw.data;
            }), catchError(GroupService.handleError)
        );
    }

    delete(id: number): Observable<{} | object> {
        return this.httpClient.delete(`${this.groupUrl}/delete/${id}`).pipe(
            catchError(GroupService.handleError)
        );
    }

    getGroupsByCoachId(coachId: number): Observable<Group[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.groupUrl}/getGroupsByCoachId/${coachId}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(GroupService.handleError)
        );
    } 
}
