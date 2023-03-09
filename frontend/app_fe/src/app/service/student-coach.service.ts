import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { catchError, map} from "rxjs/operators"
import { HttpClient} from "@angular/common/http"
import { ResponseWrapper } from "../model/sys/response-wrapper";
import Utils from "../sys/utils";
import { StudentCoach } from "../model/student_coach";

@Injectable()
export class StudentCoachService {
    private apiUrl = '/api';
    private studentCoachUrl = this.apiUrl + '/studenCoachRequests'
    constructor(private httpClient: HttpClient){

    }

    private static handleError(error: Response | any){
        return Utils.handleError(error);
    }

    create(obj: StudentCoach): Observable<StudentCoach>{
        return this.httpClient.post<ResponseWrapper>(`${this.studentCoachUrl}/create`, obj).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StudentCoachService.handleError)
        );
    }    

    delete(id: number): Observable<{} | object> {
        return this.httpClient.delete(`${this.studentCoachUrl}/delete/${id}`).pipe(
            catchError(StudentCoachService.handleError)
        );
    }

    getAllByCoachId(coachId: number): Observable<StudentCoach[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.studentCoachUrl}/getAllByCoachId/${coachId}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StudentCoachService.handleError)
        );
    }  
    
    cancel(id: number): Observable<{} | object | null>{
        return this.httpClient.post<ResponseWrapper>(`${this.studentCoachUrl}/cancel`, id).pipe(
            map(rw => {
                return null;
            }), catchError(StudentCoachService.handleError)
        );
    }    

    accept(id: number): Observable<{} | object>{
        return this.httpClient.get<ResponseWrapper>(`${this.studentCoachUrl}/accept/${id}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StudentCoachService.handleError)
        );
    } 

    findByCoachAndStudent(coachId: number, studentId: number): Observable<boolean>{
        return this.httpClient.get<ResponseWrapper>(`${this.studentCoachUrl}/findByCoachAndStudent/${coachId}/${studentId}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StudentCoachService.handleError)
        );
    } 

    getAllByStudentIdAccepted(studentId: number): Observable<StudentCoach[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.studentCoachUrl}/getAllByStudentIdAccepted/${studentId}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StudentCoachService.handleError)
        );
    } 

    getAllByStudenIdNew(studentId: number): Observable<StudentCoach[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.studentCoachUrl}/getAllByStudentIdNew/${studentId}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StudentCoachService.handleError)
        );
    } 

    getAllByStudent(studentId: number): Observable<StudentCoach[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.studentCoachUrl}/getAllByStudent/${studentId}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StudentCoachService.handleError)
        );
    } 

    getByCoachAccepted(coachId: number): Observable<StudentCoach[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.studentCoachUrl}/getByCoachAccepted/${coachId}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StudentCoachService.handleError)
        );
    }  

    getAllByGroupId(coachId: number): Observable<StudentCoach[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.studentCoachUrl}/getAllByGroupId/${coachId}`).pipe(
            map(rw => {
                return rw.data;
            }), catchError(StudentCoachService.handleError)
        );
    } 
}