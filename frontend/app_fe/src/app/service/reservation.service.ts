import { Injectable } from "@angular/core";
import { Observable} from "rxjs";
import { catchError, map} from "rxjs/operators"
import { HttpClient} from "@angular/common/http"
import { ResponseWrapper } from "../model/sys/response-wrapper";
import Utils from "../sys/utils";
import { Reservation } from "../model/reservation";

@Injectable()
export class ReservationService {
    private apiUrl = '/api';
    private reservationUrl = this.apiUrl + '/reservations'
    constructor(private httpClient: HttpClient){

    }

    private static handleError(error: Response | any){
        return Utils.handleError(error);
    }

    private static processData(obj: Reservation){
        if(obj.resDate){
            obj.resDate = new Date(obj.resDate);
        }
        return obj;
    }

    getAllReservations(): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.apiUrl}/getAllReservations`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            })
        );
    }

    create(res: Reservation): Observable<Reservation>{
        return this.httpClient.post<ResponseWrapper>(`${this.reservationUrl}/create`, res).pipe(
            map(rw => {
                return ReservationService.processData(rw.data);
            }), catchError(ReservationService.handleError)
        );
    }

    delete(id: number): Observable<{} | object> {
        return this.httpClient.delete(`${this.reservationUrl}/delete/${id}`).pipe(
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByDateStudentCourt(date: Date, studentId: number, courtId: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getAllByDateAndStudentAndCourt/${date}/${studentId}/${courtId}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByDateStudentCourtCoach(date: Date, studentId: number, courtId: number, coachId: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getAllByDateAndStudentAndCourtAndCoach/${date}/${studentId}/${courtId}/${coachId}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByStudentId(studentId: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByStudentId/${studentId}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByDateStudentIdStatus(date: Date, studentId: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByDateStudentIdStatus/${date}/${studentId}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByCoachId(coachId: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByCoachId/${coachId}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    cancel(id: number): Observable<{} | object> {
        return this.httpClient.post(`${this.reservationUrl}/cancel`, id).pipe(
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByDate(date: Date): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByDate/${date}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByDateAndTimeStart(date: Date, timeStart: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByDateAndTimeStart/${date}/${timeStart}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByDateAndTimeStartForCoaches(date: Date, timeStart: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByDateAndTimeStartForCoaches/${date}/${timeStart}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    acceptReservation(id: number): Observable<{} | object>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/acceptReservation/${id}`).pipe(
            map(rw => {
                return rw.data;
            }), 
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByDateCourtCoachGroupStudents(date: Date, courtId: number, coachId: number, groupId: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByDateCourtCoachGroupStudents/${date}/${courtId}/${coachId}/${groupId}`).pipe(
            map(rw => {
                debugger;
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByDateStudentCourtCoachGroup(date: Date, studentId: number, courtId: number, coachId: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByDateStudentCourtCoachGroup/${date}/${studentId}/${courtId}/${coachId}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }

    getReservationsByCoachIdAndGroupNotNull(coachId: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByCoachAndGroupNotNull/${coachId}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }
    getReservationsByStudentIdAndGroup(studentId: number): Observable<Reservation[]>{
        return this.httpClient.get<ResponseWrapper>(`${this.reservationUrl}/getReservationsByStudentIdAndGroup/${studentId}`).pipe(
            map(rw => {
                return rw.data.map((rwd: Reservation) => ReservationService.processData(rwd));
            }), 
            catchError(ReservationService.handleError)
        );
    }
    
}