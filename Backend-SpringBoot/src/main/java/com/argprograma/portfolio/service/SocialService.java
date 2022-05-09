package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialService implements ISocialService {
    
    @Autowired
    private SocialRepository socialRepo;
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private SocialTypeService socialTypeService;

    @Override
    public Social createSocial(Social social) {
        Social savedSocial = socialRepo.save(social);
        savedSocial.getPortfolio().getSocialSet().add(savedSocial);
        portfolioService.updatePortfolio(savedSocial.getPortfolio());
        return savedSocial;
    }

    @Override
    public void deleteSocial(Social social) {
        social.getPortfolio().getSocialSet().remove(social);
        social.getSocialType().getSocialSet().remove(social);
        portfolioService.updatePortfolio(social.getPortfolio());
        socialTypeService.updateSocialType(social.getSocialType());
    }
    
}
