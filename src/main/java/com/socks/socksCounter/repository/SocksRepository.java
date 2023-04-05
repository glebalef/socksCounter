package com.socks.socksCounter.repository;

import com.socks.socksCounter.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
    Socks findByCottonPartAndColor (Integer cotton, String color);
}
