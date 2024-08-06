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

import com.metaphorce.exam.ecommerce.product.dto.ProductDTO;
import com.metaphorce.exam.ecommerce.product.mapper.ProductMapper;
import com.metaphorce.exam.ecommerce.product.model.impl.Product;
import com.metaphorce.exam.ecommerce.product.service.impl.ProductService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/product")
@RestController
@Slf4j
public class ProductController {
	@Autowired
	private ProductService service;
	
	/**
	 * Implements the HTTP Post to create a Product
	 * @param Product Data to insert
	 * @return New Product create
	 * @author Juan Chavez
	 * @since Jun/11/2024 
	 */
	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productRequest) {
		log.info("Product to create: " + productRequest);
		ResponseEntity<ProductDTO> response = null;
		Product newProduct = this.service.create(ProductMapper.mapper.toEntity(productRequest));

		if(newProduct != null) {
			response = ResponseEntity.ok(ProductMapper.mapper.toDTO(newProduct));
			log.info("Product created: " + newProduct);
		} else {
			response = ResponseEntity.badRequest().build();
			log.info("Error to create Product: " + productRequest);
		}

		return response;
	}

	/**
	 * Implements the HTTP Get to find all Products
	 * @return List of Products
	 * @author Juan Chavez
	 * @since Jun/11/2024 
	 */
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		log.info("Get All Products ...");
		ResponseEntity<List<ProductDTO>> response = null;
		List<Product> listProducts = this.service.getAll();

		if(listProducts != null) {
			log.info("Products[" + listProducts.size() + "]");
			response = ResponseEntity.ok(listProducts.stream().map(ProductMapper.mapper::toDTO).toList());
		} else {
			response = ResponseEntity.notFound().build();
			log.info("Error to get all Products");
		}

		return response;
	}

	/**
	 * Implements HTTP Get to find a Product by id
	 * @param id Product Id
	 * @return Product data
	 * @return New Product create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
		log.info("Get Product[" + id + "]");
		return service.get(id).map((Product p) -> {
			log.info("Product found: " + p);
			return ResponseEntity.ok(ProductMapper.mapper.toDTO(p));
		}).orElseGet(() -> {
			log.warn("Product not found: " + id);
			return ResponseEntity.notFound().build();
		});
	}

	/**
	 * Implements HTTP Put for update a Product
	 * @param id Product Id
	 * @param Product Product data to update
	 * @return Product modified
	 * @return New Product create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductDTO Product) {
		log.info("Product[" + id + "] to update: " + Product);
		ResponseEntity<ProductDTO> response = null;
		Product updateProductData = this.service.update(id, ProductMapper.mapper.toEntity(Product));

		if(updateProductData != null) {
			response = ResponseEntity.ok(ProductMapper.mapper.toDTO(updateProductData));
			log.info("Product[" + id + "] updated");
		} else {
			response = ResponseEntity.badRequest().build();
			log.info("Product[" + id + "] not updated");
		}

		return response;
	}

	/**
	 * Implements HTTP Delete for delete a Product
	 * @param id Product Id
	 * @return Response
	 * @return New Product create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
		log.info("Product[" + id + "] to delete");
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
