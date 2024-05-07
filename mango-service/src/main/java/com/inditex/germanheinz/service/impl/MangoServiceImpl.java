package com.inditex.germanheinz.service.impl;

import com.inditex.germanheinz.entity.Mango;
import com.inditex.germanheinz.mapper.MangoMapper;
import com.inditex.germanheinz.model.MangoDto;
import com.inditex.germanheinz.repository.MangoRepository;
import com.inditex.germanheinz.service.MangoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangoServiceImpl implements MangoService {

    static Logger logger = LoggerFactory.getLogger(MangoServiceImpl.class);

    private final MangoMapper mangoMapper;

    private final MangoRepository mangoRepository;

    public MangoServiceImpl(MangoMapper mangoMapper, MangoRepository mangoRepository) {
        this.mangoMapper = mangoMapper;
        this.mangoRepository = mangoRepository;
    }

    @Override
    public List<MangoDto> getMangoData() {
        logger.info("Get Mango init with Price Request");
        List<Mango> mangoJpa = mangoRepository.findAll();
        logger.info("Get Prices from DB {}", mangoJpa.get(0).toString());

        return mangoMapper.mangoJpaToMangoDto(mangoJpa);
    }
}
