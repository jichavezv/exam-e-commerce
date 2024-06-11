package com.metaphorce.exam.ecommerce.product.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
	T create(T newData);
	Optional<T> get(Integer id);
	List<T> getAll();
	T update(Integer id, T updateData);
	void delete(Integer id);
}
