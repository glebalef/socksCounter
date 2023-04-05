package com.socks.socksCounter.service;

import com.socks.socksCounter.entity.Socks;
import com.socks.socksCounter.entity.Stock;
import com.socks.socksCounter.repository.SocksRepository;
import com.socks.socksCounter.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private SocksRepository socksRepository;
    @Autowired
    private SocksService socksService;

    public Stock addToStock(int cotton, String color, int quantity) {
        if (socksRepository.findByCottonPartAndColor(cotton, color) == null) {
            Socks socks = new Socks();
            socks.setColor(color);
            socks.setCottonPart(cotton);
            socksRepository.save(socks);
            Stock stock = new Stock();
            stock.setSocks(socks);
            stock.setQuantity(quantity);
            stockRepository.save(stock);
            return stock;
        } else {
            Socks socks = socksRepository.findByCottonPartAndColor(cotton, color);
            if (stockRepository.findBySocks(socks) == null) {
                return stockRepository.save(new Stock(socks, quantity));
            } else {
                Stock stock = stockRepository.findBySocks(socks);
                Integer newQuantity = stock.getQuantity() + quantity;
                stock.setQuantity(newQuantity);
                return stockRepository.save(stock);
            }
        }
    }
}

