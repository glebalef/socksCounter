package com.socks.socksCounter.service;

import com.socks.socksCounter.entity.Socks;
//import com.socks.socksCounter.repository.SocksRepository;
import com.socks.socksCounter.repository.SocksRepository;
import org.springframework.stereotype.Service;

@Service
public class SocksService {

    private final SocksRepository socksRepository;

    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public Socks addSocks(Socks socks) {

        if (!socksRepository.findByCottonPartAndColor(socks.getCottonPart(), socks.getColor()).equals(socks)) {
            return socksRepository.save(socks);
        } return socks;
    }
}
