package com.metaphorce.exam.ecommerce.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaphorce.exam.ecommerce.product.model.impl.Discount;
import com.metaphorce.exam.ecommerce.product.repository.DiscountRepository;
import com.metaphorce.exam.ecommerce.product.service.IService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiscountService implements IService<Discount> {
	@Autowired
	private DiscountRepository repository;

	@Transactional
	@Override
	public Discount create(Discount newData) {
		// TODO Auto-generated method stub
		log.info("Adding Discount ..." + newData);
		return repository.save(newData);
	}

	@Transactional
	@Override
	public Optional<Discount> get(Integer id) {
		// TODO Auto-generated method stub
		log.info("Get Discount ..." + id);
		return repository.findById(id);
	}

	@Transactional
	@Override
	public List<Discount> getAll() {
		// TODO Auto-generated method stub
		log.info("Discounts ...");
		return repository.findAll();
	}

	@Transactional
	@Override
	public Discount update(Integer id, Discount updateData) {
		// TODO Auto-generated method stub
		log.info("Update Discount ..." + id);
		updateData.setId(id);
		
		return repository.save(updateData);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		log.info("Delete Discount ..." + id);
		repository.deleteById(id);
	}
}
