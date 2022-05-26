export interface Education {
    id: bigint;
    itemOrder: number;
    logoUrl: string;
    institution: string;
    educationTime: string;
    title: string;
    portfolioData: {
        id: bigint;
        name: string;
    }
}