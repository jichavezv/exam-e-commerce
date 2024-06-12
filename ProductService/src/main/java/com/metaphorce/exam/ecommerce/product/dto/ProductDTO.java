package com.metaphorce.exam.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
	private String name;
	private String description;
	private String SKU;
	private Double price;
	
	private CategoryDTO category;
	private InventoryDTO inventory;
	private DiscountDTO discount;
}
