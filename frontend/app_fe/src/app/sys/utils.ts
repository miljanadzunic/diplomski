import { HttpErrorResponse } from "@angular/common/http";
import { throwError } from "rxjs";

export default class Utils {
    static handleError(error: HttpErrorResponse | any) {
        console.log('***************** handleErrors');
       
        console.log(error);

        console.log(error.messages);
        console.log(error.message);


        console.log(error.error.messages);
        console.log(error.error.message);
        return throwError(error.error.message);
    }
}