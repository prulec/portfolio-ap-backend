import { Portfolio } from "./Portfolio";

export const PORTFOLIO:Portfolio = {
    
    "name": "",
    "visible": false,
    "bannerUrl": "",
    "photoUrl": "",
    "jobTitle": "",
    "pstatement": "",
    "user": {
        "id": BigInt(0),
        "username": "",
        "password": "",
        "firstName": "",
        "lastName": "",
        "email": "",
    },
    "socialList": [{
        itemOrder: 0,
        url: "",
        socialTypeData: {
            id: BigInt(0),
            name: "",
            iconUrl: "",
        },
        portfolioData: {
            id: BigInt(0),
            name: "",
        }
    }],
    "experienceList": [{
        itemOrder: 0,
        logoUrl: "",
        enterprise: "",
        experienceTime: "",
        position: "",
        tasks: "",
        portfolioData: {
            id: BigInt(0),
            name: "",
        }
    }],
    "educationList": [{
        itemOrder: 0,
        logoUrl: "",
        institution: "",
        educationTime: "",
        title: "",
        portfolioData: {
            id: BigInt(0),
            name: "",
        }
    }],
    "skillList": [{
        itemOrder: 0,
        name: "",
        skillLevel: "",
        levelTag: "",
        portfolioData: {
            id: BigInt(0),
            name: "",
        }
    }],
    "projectList": [{
        itemOrder: 0,
        name: "",
        projectTime: "",
        link: "",
        description: "",
        projectImageList: [{
            itemOrder: 0,
            title: "",
            imageUrl: "",
            projectData: {
                id: BigInt(0),
                name: "",
                projectTime: "",
                link: "",
                description: "",
            }
        }],
        portfolioData: {
            id: BigInt(0),
            name: "",
        }
    }]
    
}