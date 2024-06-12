package com.metaphorce.exam.ecommerce.product.mapper;

import org.mapstruct.Mapper;

import com.metaphorce.exam.ecommerce.product.dto.CategoryDTO;
import com.metaphorce.exam.ecommerce.product.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryDTO toDTO(Category entity);
	Category toEntity(CategoryDTO dto);
}
