package com.metaphorce.exam.ecommerce.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaphorce.exam.ecommerce.product.model.Category;
import com.metaphorce.exam.ecommerce.product.repository.CategoryRepository;
import com.metaphorce.exam.ecommerce.product.service.IService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService implements IService<Category> {
	@Autowired
	private CategoryRepository repository;

	@Override
	public Category create(Category newData) {
		// TODO Auto-generated method stub
		log.info("Adding Category ..." + newData);
		return repository.save(newData);
	}

	@Override
	public Optional<Category> get(Integer id) {
		// TODO Auto-generated method stub
		log.info("Get Category ..." + id);
		return repository.findById(id);
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		log.info("Categories ...");
		return repository.findAll();
	}

	@Override
	public Category update(Integer id, Category updateData) {
		// TODO Auto-generated method stub
		log.info("Update Category ..." + id);
		updateData.setId(id);
		
		return repository.save(updateData);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		log.info("Delete Category ..." + id);
		repository.deleteById(id);
	}
}
