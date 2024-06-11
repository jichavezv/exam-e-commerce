package com.metaphorce.exam.ecommerce.product.model;

import com.metaphorce.exam.ecommerce.model.BaseEntity;

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
	private String desc;
	private Double discountPercent;
	private Boolean active;
}
