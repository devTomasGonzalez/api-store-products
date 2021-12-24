package com.falabella.products.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.falabella.products.api.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT * FROM products t WHERE t.sku = :sku", nativeQuery = true)
	Product getProductBySku(@Param(value = "sku") String sku);
}
