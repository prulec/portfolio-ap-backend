export interface ProjectImage {
    id?: bigint;
    itemOrder: number;
    title: string;
    imageUrl: string;
    projectData: {
        id: bigint;
        name: string;
        projectTime: string;
        link: string;
        description: string;
    }
}