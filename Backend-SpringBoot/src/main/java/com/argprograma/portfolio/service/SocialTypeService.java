package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.SocialType;
import com.argprograma.portfolio.repository.SocialTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialTypeService implements ISocialTypeService {
    
    @Autowired
    private SocialTypeRepository socialTypeRepo;
    @Autowired
    private SocialService socialService;

    @Override
    public SocialType createSocialType(SocialType socialType) {
        return socialTypeRepo.save(socialType);
    }

    @Override
    public SocialType findSocialTypeById(Long id) {
        return socialTypeRepo.findById(id).orElse(null);
    }

    @Override
    public SocialType findSocialTypeByName(String name) {
        return socialTypeRepo.findByName(name);
    }

    @Override
    public List<SocialType> getSocialTypes() {
        return socialTypeRepo.findAll();
    }

    @Override
    public SocialType updateSocialType(SocialType socialType) {
        return socialTypeRepo.save(socialType);
    }

    @Override
    public void deleteSocialType(SocialType socialType) {
        for (Social social : socialType.getSocialSet()) {
            socialService.deleteSocial(social);
        }
        socialTypeRepo.delete(socialType);
    }
}
