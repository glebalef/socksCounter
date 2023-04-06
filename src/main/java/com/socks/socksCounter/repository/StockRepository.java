package com.socks.socksCounter.repository;

import com.socks.socksCounter.entity.Socks;
import com.socks.socksCounter.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findBySocks(Socks socks);

    List<Stock> findBySocksCottonPartGreaterThanAndSocksColor (int socks_cottonPart, String socks_color);
    List<Stock> findBySocksCottonPartLessThanAndSocksColor (int socks_cottonPart, String socks_color);
    List<Stock> findBySocksCottonPartAndSocksColor (int socks_cottonPart, String socks_color);


}
