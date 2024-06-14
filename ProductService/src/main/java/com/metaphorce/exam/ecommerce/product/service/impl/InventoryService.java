package com.metaphorce.exam.ecommerce.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaphorce.exam.ecommerce.product.model.impl.Inventory;
import com.metaphorce.exam.ecommerce.product.repository.InventoryRepository;
import com.metaphorce.exam.ecommerce.product.service.IService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService implements IService<Inventory> {
	@Autowired
	private InventoryRepository repository;

	@Transactional
	@Override
	public Inventory create(Inventory newData) {
		// TODO Auto-generated method stub
		log.info("Adding Inventory ..." + newData);
		return repository.save(newData);
	}

	@Transactional
	@Override
	public Optional<Inventory> get(Integer id) {
		// TODO Auto-generated method stub
		log.info("Get Inventory ..." + id);
		return repository.findById(id);
	}

	@Transactional
	@Override
	public List<Inventory> getAll() {
		// TODO Auto-generated method stub
		log.info("Inventories ...");
		return repository.findAll();
	}

	@Transactional
	@Override
	public Inventory update(Integer id, Inventory updateData) {
		// TODO Auto-generated method stub
		log.info("Update Inventory ..." + id);
		updateData.setId(id);
		
		return repository.save(updateData);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		log.info("Delete Inventory ..." + id);
		repository.deleteById(id);
	}
}
