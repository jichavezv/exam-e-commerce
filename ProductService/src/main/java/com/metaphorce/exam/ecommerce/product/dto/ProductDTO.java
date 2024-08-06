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
	private Integer id;
	private String name;
	private String description;
	private String SKU;
	private Double price;
	
	private Integer categoryId;
	private Integer inventoryId;
	private Integer discountId;
}
