package com.metaphorce.exam.ecommerce.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaphorce.exam.ecommerce.product.model.Discount;
import com.metaphorce.exam.ecommerce.product.repository.DiscountRepository;
import com.metaphorce.exam.ecommerce.product.service.IService;

@Service
public class DiscountService implements IService<Discount> {
	@Autowired
	private DiscountRepository repository;

	@Override
	public Discount create(Discount newData) {
		// TODO Auto-generated method stub
		return repository.save(newData);
	}

	@Override
	public Optional<Discount> get(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public List<Discount> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Discount update(Integer id, Discount updateData) {
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
