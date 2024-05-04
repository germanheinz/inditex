package com.inditex.germanheinz.demo.mapper;

import com.inditex.germanheinz.demo.entity.Price;

import java.util.List;

import com.inditex.germanheinz.demo.model.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target="brand", source="brand")
    @Mapping(target="productId", source="productId")
    List<PriceDto> pricesToPricesDto(List<Price> priceJpaList);

}