package com.metaphorce.exam.ecommerce.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaphorce.exam.ecommerce.product.dto.DiscountDTO;
import com.metaphorce.exam.ecommerce.product.mapper.DiscountMapper;
import com.metaphorce.exam.ecommerce.product.model.impl.Discount;
import com.metaphorce.exam.ecommerce.product.service.impl.DiscountService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/discount")
@RestController
@Slf4j
public class DiscountController {
	@Autowired
	private DiscountService service;
	
	/**
	 * Implements the HTTP Post to create a Discount
	 * @param Discount Data to insert
	 * @return New Discount create
	 * @author Juan Chavez
	 * @since Jun/11/2024 
	 */
	@PostMapping
	public ResponseEntity<DiscountDTO> createDiscount(@Valid @RequestBody DiscountDTO DiscountRequest) {
		log.info("Discount to create: " + DiscountRequest);
		ResponseEntity<DiscountDTO> response = null;
		Discount newDiscount = this.service.create(DiscountMapper.mapper.toEntity(DiscountRequest));

		if(newDiscount != null) {
			response = ResponseEntity.ok(DiscountMapper.mapper.toDTO(newDiscount));
			log.info("Discount created: " + newDiscount);
		} else {
			response = ResponseEntity.badRequest().build();
			log.info("Error to create Discount: " + DiscountRequest);
		}

		return response;
	}

	/**
	 * Implements the HTTP Get to find all Discounts
	 * @return List of Discounts
	 * @author Juan Chavez
	 * @since Jun/11/2024 
	 */
	@GetMapping
	public ResponseEntity<List<DiscountDTO>> getAllDiscounts() {
		log.info("Get All Discounts ...");
		ResponseEntity<List<DiscountDTO>> response = null;
		List<Discount> listDiscounts = this.service.getAll();

		if(listDiscounts != null) {
			log.info("Discounts[" + listDiscounts.size() + "]");
			response = ResponseEntity.ok(listDiscounts.stream().map(DiscountMapper.mapper::toDTO).toList());
		} else {
			response = ResponseEntity.notFound().build();
			log.info("Error to get all Discounts");
		}

		return response;
	}

	/**
	 * Implements HTTP Get to find a Discount by id
	 * @param id Discount Id
	 * @return Discount data
	 * @return New Discount create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DiscountDTO> getDiscount(@PathVariable Integer id) {
		log.info("Get Discount[" + id + "]");
		return service.get(id).map((Discount Discount) -> {
			log.info("Discount found: " + Discount);
			return ResponseEntity.ok(DiscountMapper.mapper.toDTO(Discount));
		}).orElseGet(() -> {
			log.warn("Discount not found: " + id);
			return ResponseEntity.notFound().build();
		});
	}

	/**
	 * Implements HTTP Put for update a Discount
	 * @param id Discount Id
	 * @param Discount Discount data to update
	 * @return Discount modified
	 * @return New Discount create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@PutMapping("/{id}")
	public ResponseEntity<DiscountDTO> updateDiscount(@PathVariable Integer id, @Valid @RequestBody DiscountDTO Discount) {
		log.info("Discount[" + id + "] to update: " + Discount);
		ResponseEntity<DiscountDTO> response = null;
		Discount updateDiscountData = this.service.update(id, DiscountMapper.mapper.toEntity(Discount));

		if(updateDiscountData != null) {
			response = ResponseEntity.ok(DiscountMapper.mapper.toDTO(updateDiscountData));
			log.info("Discount[" + id + "] updated");
		} else {
			response = ResponseEntity.badRequest().build();
			log.info("Discount[" + id + "] not updated");
		}

		return response;
	}

	/**
	 * Implements HTTP Delete for delete a Discount
	 * @param id Discount Id
	 * @return Response
	 * @return New Discount create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDiscount(@PathVariable Integer id) {
		log.info("Discount[" + id + "] to delete");
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
