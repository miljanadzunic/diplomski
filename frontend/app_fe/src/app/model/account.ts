import { Role } from "./role";

export class Account {

    accId!: number;
    accName!: string;
    accSurname!: string;
    accEmail!: string;
    accPassword!: string;
    accRole!: Role;
    accEnabled!: boolean;
    accPhone!: string;
    accUsername!: string;
    accBirthday?: Date;
    accPhoto?: string;
}