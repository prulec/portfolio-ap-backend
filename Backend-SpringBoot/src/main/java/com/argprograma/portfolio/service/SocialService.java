package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.SocialType;
import com.argprograma.portfolio.repository.SocialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialService implements ISocialService {
    
    @Autowired
    private SocialRepository socialRepo;
    @Autowired
    private PortfolioService portfolioService;

    @Override
    public Social createSocial(Social social) {
        Social savedSocial = socialRepo.save(social);
        savedSocial.getPortfolio().getSocialSet().add(savedSocial);
        portfolioService.updatePortfolio(savedSocial.getPortfolio());
        return savedSocial;
    }

    @Override
    public List<Social> findSocialBySocialType(SocialType socialType) {
        return socialRepo.findAllBySocialType(socialType);
    }

    @Override
    public void deleteSocial(Social social) {
        social.getPortfolio().getSocialSet().remove(social);
        portfolioService.updatePortfolio(social.getPortfolio());
        socialRepo.delete(social);
    }
    
}
