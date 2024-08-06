package com.metaphorce.exam.ecommerce.product.model.impl;

import com.metaphorce.exam.ecommerce.product.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discount")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Discount extends BaseEntity {
	private String name;
	private String description;
	
	@Column(name = "discount_percent")
	private Double discountPercent;
	
	private Boolean active;
}
