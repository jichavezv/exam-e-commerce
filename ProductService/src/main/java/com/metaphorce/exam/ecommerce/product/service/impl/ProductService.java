package com.metaphorce.exam.ecommerce.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaphorce.exam.ecommerce.product.model.impl.Product;
import com.metaphorce.exam.ecommerce.product.repository.ProductRepository;
import com.metaphorce.exam.ecommerce.product.service.IService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService implements IService<Product> {
	@Autowired
	private ProductRepository repository;

	@Transactional
	@Override
	public Product create(Product newData) {
		// TODO Auto-generated method stub
		log.info("Adding Product ..." + newData);
		return repository.save(newData);
	}

	@Transactional
	@Override
	public Optional<Product> get(Integer id) {
		// TODO Auto-generated method stub
		log.info("Get Product ..." + id);
		return repository.findById(id);
	}

	@Transactional
	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		log.info("Products ...");
		return repository.findAll();
	}

	@Transactional
	@Override
	public Product update(Integer id, Product updateData) {
		// TODO Auto-generated method stub
		log.info("Update Product ..." + id);
		updateData.setId(id);
		
		return repository.save(updateData);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		log.info("Delete Product ..." + id);
		repository.deleteById(id);
	}
}
