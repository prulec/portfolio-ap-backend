import { Education } from "./Education";
import { Experience } from "./Experience";
import { Project } from "./Project";
import { Skill } from "./Skill";
import { Social } from "./Social";

export interface Portfolio {
    id?: bigint;
    name: string;
    visible: boolean;
    bannerUrl: string;
    photoUrl: string;
    jobTitle: string;
    pstatement: string;
    user: {
        id: bigint;
        username: string;
        password: string;
        firstName: string;
        lastName: string;
        email: string;
    };
    socialList: Social[];
    experienceList: Experience[];
    educationList: Education[];
    skillList: Skill[];
    projectList: Project[]
}