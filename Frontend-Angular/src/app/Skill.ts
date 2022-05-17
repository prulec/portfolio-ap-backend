export interface Skill {
    id: bigint;
    itemOrder: number;
    name: string;
    skillLevel: string;
    levelTag: string;
    portfolioData: {
        id: bigint;
        name: string;
    }
}