package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService implements IPortfolioService {
    
    @Autowired
    private PortfolioRepository portfolioRepo;
    @Autowired
    private UserService userService;

    @Override
    public Portfolio createPortfolio(Portfolio portfolio) {
        User user = portfolio.getUser();
        user.getPortfolioSet().add(portfolio);
        userService.updateUser(user);
        return portfolioRepo.save(portfolio);
    }

    @Override
    public Portfolio findPortfolioById(Long id) {
        return portfolioRepo.findById(id).orElse(null);
    }

    @Override
    public Portfolio updatePortfolio(Portfolio portfolio) {
        return portfolioRepo.save(portfolio);
    }

    @Override
    public void deletePortfolio(Portfolio portfolio) {
        portfolioRepo.delete(portfolio);
    }
    
}
