package com.metaphorce.exam.ecommerce.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.metaphorce.exam.ecommerce.product.dto.InventoryDTO;
import com.metaphorce.exam.ecommerce.product.model.impl.Inventory;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
	InventoryMapper mapper = Mappers.getMapper(InventoryMapper.class);
	
	Inventory toEntity(InventoryDTO dto);
	InventoryDTO toDTO(Inventory entity);
}
