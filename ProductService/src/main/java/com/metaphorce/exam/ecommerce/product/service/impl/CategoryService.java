package com.metaphorce.exam.ecommerce.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaphorce.exam.ecommerce.product.model.Category;
import com.metaphorce.exam.ecommerce.product.repository.CategoryRepository;
import com.metaphorce.exam.ecommerce.product.service.IService;

@Service
public class CategoryService implements IService<Category> {
	@Autowired
	private CategoryRepository repository;

	@Override
	public Category create(Category newData) {
		// TODO Auto-generated method stub
		return repository.save(newData);
	}

	@Override
	public Optional<Category> get(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Category update(Integer id, Category updateData) {
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
