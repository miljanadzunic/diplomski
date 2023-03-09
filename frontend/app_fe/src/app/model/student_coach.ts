import { Account } from "./account";
import { Group } from "./group";
import { Status } from "./status";

export class StudentCoach {

    sctId!: number;
    sctStudent!: Account;
    sctCoach!: Account;
    sctGroup!: Group;
    sctStatus!: Status;
}