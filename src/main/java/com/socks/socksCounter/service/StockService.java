package com.socks.socksCounter.service;

import com.socks.socksCounter.constant.Operation;
import com.socks.socksCounter.entity.Socks;
import com.socks.socksCounter.entity.Stock;
import com.socks.socksCounter.exceptions.NegativeStockException;
import com.socks.socksCounter.exceptions.NoSuchItemException;
import com.socks.socksCounter.repository.SocksRepository;
import com.socks.socksCounter.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private SocksRepository socksRepository;

    public Stock addToStock(int cotton, String color, int quantity) {

        if (socksRepository.findByCottonPartAndColor(cotton, color) == null) {
            Socks socks = new Socks();
            socks.setColor(color);
            socks.setCottonPart(cotton);
            socksRepository.save(socks);
            Stock stock = new Stock();
            stock.setSocks(socks);
            stock.setQuantity(quantity);
            return stockRepository.save(stock);
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

    public Stock reduceStock(int cotton,
                             String color,
                             int quantity) throws NoSuchItemException, NegativeStockException {

        if (socksRepository.findByCottonPartAndColor(cotton, color) == null) {
            throw new NoSuchItemException();
        } else {
            Stock stock = stockRepository
                    .findBySocks(socksRepository
                            .findByCottonPartAndColor(cotton, color));
            int newQuantity = stock.getQuantity() - quantity;
            if (newQuantity < 0) {
                throw new NegativeStockException();
            } else {
                stock.setQuantity(newQuantity);
                return stockRepository.save(stock);
            }
        }
    }

    public int getStock(String color, Operation operation, int cotton) {

        int quantity = 0;
        List<Stock> stocks = null;

        switch (operation) {
            case equal -> {
                stocks = stockRepository.findBySocksCottonPartAndSocksColor(cotton, color);}
            case moreThan -> {
                stocks = stockRepository.findBySocksCottonPartGreaterThanAndSocksColor(cotton, color);}
            case lessThan -> {
                stocks = stockRepository.findBySocksCottonPartLessThanAndSocksColor(cotton, color);}
        }

        for (Stock s : stocks) {
            quantity = quantity + s.getQuantity();
        }
        return quantity;
    }
}



