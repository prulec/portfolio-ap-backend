package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Education;
import com.argprograma.portfolio.model.Experience;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.Project;
import com.argprograma.portfolio.model.Skill;
import com.argprograma.portfolio.model.Social;

public interface IPortfolioService {
    public Portfolio createPortfolio (Portfolio portfolio);
    public Portfolio findPortfolioById (Long id);
    public Portfolio findPortfolioByName (String name);
    public Portfolio updatePortfolio (Portfolio portfolio);
    public void deletePortfolio (Portfolio portfolio);
    public Portfolio disconnectSocial (Social social);
    public Portfolio disconnectExperience (Experience experience);
    public Portfolio disconnectEducation (Education education);
    public Portfolio disconnectSkill (Skill skill);
    public Portfolio disconnectProject (Project project);
}
