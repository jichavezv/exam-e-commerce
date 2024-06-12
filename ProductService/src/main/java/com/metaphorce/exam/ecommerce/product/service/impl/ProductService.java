package com.metaphorce.exam.ecommerce.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaphorce.exam.ecommerce.product.model.impl.Product;
import com.metaphorce.exam.ecommerce.product.repository.ProductRepository;
import com.metaphorce.exam.ecommerce.product.service.IService;

@Service
public class ProductService implements IService<Product> {
	@Autowired
	private ProductRepository repository;

	@Override
	public Product create(Product newData) {
		// TODO Auto-generated method stub
		return repository.save(newData);
	}

	@Override
	public Optional<Product> get(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Product update(Integer id, Product updateData) {
		// TODO Auto-generated method stub
		updateData.setId(id);
		
		return repository.save(updateData);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}
}
