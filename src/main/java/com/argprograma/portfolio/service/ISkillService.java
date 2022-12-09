package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Skill;

public interface ISkillService {
    public Skill createSkill (Skill skill);
    public Skill findSkillById (Long id);
    public Skill updateSkill(Skill skill);
    public Skill changeOrderSkill (Skill skill, int newOrder);
}
