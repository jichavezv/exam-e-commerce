package com.metaphorce.exam.ecommerce.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.metaphorce.exam.ecommerce.product.dto.CategoryDTO;
import com.metaphorce.exam.ecommerce.product.model.impl.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
	
	CategoryDTO toDTO(Category entity);
	Category toEntity(CategoryDTO dto);
}
