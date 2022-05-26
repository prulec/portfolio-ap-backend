import { ProjectImage } from "./ProjectImage";

export interface Project {
    id: bigint;
    itemOrder: number;
    name: string;
    projectTime: string;
    link: string;
    description: string;
    projectImageList: ProjectImage[];
    portfolioData: {
        id: bigint;
        name: string;
    }
}