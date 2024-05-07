package com.inditex.germanheinz.mapper;

import com.inditex.germanheinz.entity.Mango;
import com.inditex.germanheinz.model.MangoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MangoMapper {

    List<MangoDto> mangoJpaToMangoDto(List<Mango> mangoList);

}