import { Portfolio } from "./Portfolio";

export interface User {
    id: bigint;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    email: string;
    portfolioList: Portfolio[]
}