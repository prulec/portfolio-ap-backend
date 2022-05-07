package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.SocialType;
import java.util.List;

public interface ISocialTypeService {
    public SocialType createSocialType (SocialType socialType);
    public SocialType findSocialTypeById (Long id);
    public List<SocialType> getSocialTypes ();
    public SocialType updateSocialType (SocialType socialType);
    public void deleteSocialType (SocialType socialType);
}
