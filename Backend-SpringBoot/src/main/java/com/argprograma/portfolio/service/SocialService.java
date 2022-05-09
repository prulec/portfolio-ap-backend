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
    public Social findSocialById (Long id) {
        return socialRepo.findById(id).orElse(null);
    }

    @Override
    public Social updateSocial(Social social) {
        return socialRepo.save(social);
    }

    @Override
    public Social changeOrderSocial(Social social, int newOrder) {
        int currentOrder = social.getItemOrder();
        if (newOrder < currentOrder) {
            for (Social item : social.getPortfolio().getSocialSet()) {
                if (item.getItemOrder()>=newOrder && item.getItemOrder()<currentOrder) {
                    item.setItemOrder(item.getItemOrder()+1);
                }
            }
        } else if (newOrder > currentOrder) {
            for (Social item : social.getPortfolio().getSocialSet()) {
                if (item.getItemOrder()<=newOrder && item.getItemOrder()>currentOrder) {
                    item.setItemOrder(item.getItemOrder()-1);
                }
            }
        }
        social.setItemOrder(newOrder);
        portfolioService.updatePortfolio(social.getPortfolio());
        return social;
    }

    @Override
    public void deleteSocial(Social social) {
        social.getPortfolio().getSocialSet().remove(social);
        social.getSocialType().getSocialSet().remove(social);
        portfolioService.updatePortfolio(social.getPortfolio());
        socialTypeService.updateSocialType(social.getSocialType());
    }
    
}
