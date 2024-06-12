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
public class DiscountDTO {
	private String name;
	private String description;
	private Double discountPercent;
	private Boolean active;
}
