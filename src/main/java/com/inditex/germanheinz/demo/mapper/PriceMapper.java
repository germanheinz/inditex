package com.inditex.germanheinz.demo.mapper;

import com.inditex.germanheinz.demo.entity.Price;

import java.util.List;

import com.inditex.germanheinz.demo.model.PriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    List<PriceDto> pricesToPricesDto(List<Price> allocationInputDtoList);

}