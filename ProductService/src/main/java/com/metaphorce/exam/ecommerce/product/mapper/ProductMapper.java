package com.metaphorce.exam.ecommerce.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.metaphorce.exam.ecommerce.product.dto.ProductDTO;
import com.metaphorce.exam.ecommerce.product.model.impl.Category;
import com.metaphorce.exam.ecommerce.product.model.impl.Discount;
import com.metaphorce.exam.ecommerce.product.model.impl.Inventory;
import com.metaphorce.exam.ecommerce.product.model.impl.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
				
	@Mapping(source= "categoryId", target = "category", qualifiedByName = "getProductCategory")
	@Mapping(source= "discountId", target = "discount", qualifiedByName = "getProductDiscount")
	@Mapping(source= "inventoryId", target = "inventory", qualifiedByName = "getProductInventory")
	Product toEntity(ProductDTO dto);
	
	@Mapping(target = "categoryId", source = "category.id")
	@Mapping(target = "discountId", source = "discount.id")
	@Mapping(target = "inventoryId", source = "inventory.id")
	ProductDTO toDTO(Product entity);
	
	@Named("getProductCategory")
	default Category getProductCategory(Integer categoryId) {
		Category cat = Category.builder().build();
		
		cat.setId(categoryId);
		
		return cat;
	}
	
	@Named("getProductDiscount")
	default Discount getProductDiscount(Integer discountId) {
		Discount dis = Discount.builder().build();
		
		dis.setId(discountId);
		
		return dis;
	}
	
	@Named("getProductInventory")
	default Inventory getProductInventory(Integer id) {
		Inventory inv = Inventory.builder().build();
		
		inv.setId(id);
		
		return inv;
	}
}
