import { Account } from "./account";
import { Court } from "./court";
import { Group } from "./group";
import { Status } from "./status";


export class Reservation {
    resId!: number;

    resDate!: Date;

    resTimeStart!: number;

    resTimeEnd!: number;

    resCourt!: Court;

    resCoach?: Account;

    resRacketNum?: number;

    resStatus!: Status;

    resTerminNum!: number;

    resStudent!: Account;

    resGroup!: Group;
}