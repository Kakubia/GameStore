package com.generation.gamestore.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.gamestore.model.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findAllByNameContainingIgnoreCase(@Param("name") String name);
	
	public List<Product> findByPrice(@Param("price") BigDecimal price);
}
