package com.metaphorce.exam.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metaphorce.exam.ecommerce.product.model.impl.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
	
}
