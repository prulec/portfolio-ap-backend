package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.SocialType;
import java.util.List;

public interface ISocialTypeService {
    public SocialType createSocialType (SocialType socialType);
    public SocialType findSocialTypeById (Long id);
    public SocialType findSocialTypeByName (String name);
    public List<SocialType> getSocialTypes ();
    public SocialType updateSocialType (SocialType socialType);
    public void deleteSocialType (SocialType socialType);
    public SocialType disconnectSocial (Social social);
}
