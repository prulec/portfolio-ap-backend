package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Social;

public interface ISocialService {
    public Social createSocial (Social social);
    public Social findSocialById (Long id);
    public Social updateSocial(Social social);
    public Social changeOrderSocial (Social social, int newOrder);
    public void deleteSocial (Social social);
}
