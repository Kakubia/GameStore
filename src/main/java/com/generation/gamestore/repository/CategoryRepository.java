package com.generation.gamestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.gamestore.model.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	public List<Category> findAllByNameContainingIgnoreCase(String name);
}
