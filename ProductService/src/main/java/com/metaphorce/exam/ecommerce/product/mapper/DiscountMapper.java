package com.metaphorce.exam.ecommerce.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.metaphorce.exam.ecommerce.product.dto.DiscountDTO;
import com.metaphorce.exam.ecommerce.product.model.impl.Discount;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
	DiscountMapper mapper = Mappers.getMapper(DiscountMapper.class);
	
	Discount toEntity(DiscountDTO dto);
	DiscountDTO toDTO(Discount entity);
}
