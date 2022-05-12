export interface Experience {
    id?: bigint;
    itemOrder: number;
    logoUrl: string;
    enterprise: string;
    experienceTime: string;
    position: string;
    tasks: string;
    portfolioData: {
        id: bigint;
        name: string;
    }
}