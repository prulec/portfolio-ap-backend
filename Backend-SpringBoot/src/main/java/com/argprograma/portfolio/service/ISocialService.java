package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.SocialType;
import java.util.List;

public interface ISocialService {
    public Social createSocial (Social social);
    public void deleteSocial (Social social);
}