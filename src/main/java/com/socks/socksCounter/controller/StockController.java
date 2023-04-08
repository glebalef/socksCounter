package com.socks.socksCounter.controller;

import com.socks.socksCounter.constant.Operation;
import com.socks.socksCounter.exceptions.NegativeStockException;
import com.socks.socksCounter.exceptions.NoSuchItemException;
import com.socks.socksCounter.exceptions.WrongDataProvidedException;
import com.socks.socksCounter.entity.Stock;
import com.socks.socksCounter.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StockController {

    Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @PostMapping(value = "/socks/income")
    public Stock addStock(@RequestParam Integer cotton,
                          @RequestParam String color,
                          @RequestParam Integer quantity) throws WrongDataProvidedException {
        if (cotton < 0 || quantity <= 0) {
            logger.warn("incorrect data requested by user - method addStock");
            throw new WrongDataProvidedException();
        }
        return stockService.addToStock(cotton, color, quantity);
    }

    @PostMapping(value = "/socks/outcome")
    public Stock reduceStock(@RequestParam Integer cotton,
                             @RequestParam String color,
                             @RequestParam Integer quantity) throws NegativeStockException, NoSuchItemException, WrongDataProvidedException {
        if (cotton < 0 || cotton > 100 || quantity <= 0) {
            logger.warn("incorrect data requested by user - method reduceStock");
            throw new WrongDataProvidedException();
        }
        try {
            return stockService.reduceStock(cotton, color, quantity);
        } catch (NegativeStockException | NoSuchItemException e) {
            throw new WrongDataProvidedException();
        }
    }

    @GetMapping (value = "/socks")
    public int getStock (@RequestParam String color,
                         @RequestParam Operation operation,
                         @RequestParam int cottonPart) throws WrongDataProvidedException {
        if (cottonPart<0) {
            logger.warn("incorrect data requested by user - getStock");
            throw new WrongDataProvidedException();
        }
      return stockService.getStock(color, operation, cottonPart);
    }
}