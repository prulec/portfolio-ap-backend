package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Portfolio;

public interface IPortfolioService {
    public Portfolio createPortfolio (Portfolio portfolio);
    public Portfolio findPortfolioById (Long id);
    public Portfolio updatePortfolio (Portfolio portfolio);
    public void deletePortfolio (Portfolio portfolio);
}
