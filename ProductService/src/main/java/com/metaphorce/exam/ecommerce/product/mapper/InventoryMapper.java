package com.metaphorce.exam.ecommerce.product.mapper;

import org.mapstruct.Mapper;

import com.metaphorce.exam.ecommerce.product.dto.InventoryDTO;
import com.metaphorce.exam.ecommerce.product.model.Inventory;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
	Inventory toEntity(InventoryDTO dto);
	InventoryDTO toDTO(Inventory entity);
}
