package com.socks.socksCounter.controller;

import com.socks.socksCounter.entity.Stock;
import com.socks.socksCounter.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class stockController {

    @Autowired
    private StockService stockService;

    @PostMapping(value = "/socks/income")
   public Stock addStock(@RequestParam Integer cotton,
                         @RequestParam String color,
                         @RequestParam Integer quantity) {
   return stockService.addToStock(cotton,color, quantity);
    }
}
