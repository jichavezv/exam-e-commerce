package com.metaphorce.exam.ecommerce.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.metaphorce.exam.ecommerce.product.dto.ProductDTO;
import com.metaphorce.exam.ecommerce.product.model.impl.Product;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, InventoryMapper.class, DiscountMapper.class})
public interface ProductMapper {
	ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
	
	Product toEntity(ProductDTO dto);
	ProductDTO toDTO(Product entity);
}
