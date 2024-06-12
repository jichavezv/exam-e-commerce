package com.metaphorce.exam.ecommerce.product.mapper;

import org.mapstruct.Mapper;

import com.metaphorce.exam.ecommerce.product.dto.ProductDTO;
import com.metaphorce.exam.ecommerce.product.model.Product;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, InventoryMapper.class, DiscountMapper.class})
public interface ProductMapper {
	Product toEntity(ProductDTO dto);
	ProductDTO toDTO(Product entity);
}
