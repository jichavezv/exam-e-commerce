package com.metaphorce.exam.ecommerce.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaphorce.exam.ecommerce.product.model.Inventory;
import com.metaphorce.exam.ecommerce.product.repository.InventoryRepository;
import com.metaphorce.exam.ecommerce.product.service.IService;

@Service
public class InventoryService implements IService<Inventory> {
	@Autowired
	private InventoryRepository repository;

	@Override
	public Inventory create(Inventory newData) {
		// TODO Auto-generated method stub
		return repository.save(newData);
	}

	@Override
	public Optional<Inventory> get(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public List<Inventory> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Inventory update(Integer id, Inventory updateData) {
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
