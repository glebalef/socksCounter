package com.socks.socksCounter.repository;

import com.socks.socksCounter.entity.Socks;
import com.socks.socksCounter.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock findBySocks(Socks socks);


}
