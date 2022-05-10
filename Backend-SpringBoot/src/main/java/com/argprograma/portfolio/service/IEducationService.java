package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Education;

public interface IEducationService {
    public Education createEducation (Education education);
    public Education findEducationById (Long id);
    public Education updateEducation(Education education);
    public Education changeOrderEducation (Education education, int newOrder);
}
