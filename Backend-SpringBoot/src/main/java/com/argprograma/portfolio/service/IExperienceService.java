package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Experience;

public interface IExperienceService {
    public Experience createExperience (Experience experience);
    public Experience findExperienceById (Long id);
    public Experience updateExperience(Experience experience);
    public Experience changeOrderExperience (Experience experience, int newOrder);
    public void deleteExperience (Experience experience);
}
