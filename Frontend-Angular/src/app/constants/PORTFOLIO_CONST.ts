import { Portfolio } from "../model/Portfolio";

export const PORTFOLIO:Portfolio = {
    "id": BigInt(0),
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
        "id": BigInt(0),
        "itemOrder": 0,
        "url": "",
        "socialTypeData": {
            "id": BigInt(0),
            "name": "",
            "iconUrl": "",
        },
        "portfolioData": {
            "id": BigInt(0),
            "name": "",
        }
    }],
    "experienceList": [{
        "id": BigInt(0),
        "itemOrder": 0,
        "logoUrl": "",
        "enterprise": "",
        "experienceTime": "",
        "position": "",
        "tasks": "",
        "portfolioData": {
            "id": BigInt(0),
            "name": "",
        }
    }],
    "educationList": [{
        "id": BigInt(0),
        "itemOrder": 0,
        "logoUrl": "",
        "institution": "",
        "educationTime": "",
        "title": "",
        "portfolioData": {
            "id": BigInt(0),
            "name": "",
        }
    }],
    "skillList": [{
        "id": BigInt(0),
        "itemOrder": 0,
        "name": "",
        "skillLevel": "",
        "levelTag": "",
        "portfolioData": {
            "id": BigInt(0),
            "name": "",
        }
    }],
    "projectList": [{
        "id": BigInt(0),
        "itemOrder": 0,
        "name": "",
        "projectTime": "",
        "link": "",
        "description": "",
        "projectImageList": [{
            "id": BigInt(0),
            "itemOrder": 0,
            "title": "",
            "imageUrl": "",
            "projectData": {
                "id": BigInt(0),
                "name": "",
                "projectTime": "",
                "link": "",
                "description": "",
            }
        }],
        "portfolioData": {
            "id": BigInt(0),
            "name": "",
        }
    }]
    
}