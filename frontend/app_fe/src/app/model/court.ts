import { CourtType } from "./court-type";

export class Court{

    corId!: number;
    corName!: string;
    corType!: CourtType;
    corImg?: string;
    corDesc?: string;
}