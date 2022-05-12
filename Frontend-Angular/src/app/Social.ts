export interface Social {
    id?: bigint;
    itemOrder: number;
    url: string;
    socialTypeData: {
        id: bigint;
        name: string;
        iconUrl: string;
    };
    portfolioData: {
        id: bigint;
        name: string;
    }
}