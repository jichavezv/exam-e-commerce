package com.metaphorce.exam.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metaphorce.exam.ecommerce.product.model.impl.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
