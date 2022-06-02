package com.generation.gamestore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.gamestore.model.Category;
import com.generation.gamestore.repository.CategoryRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Category>> getByName(@PathVariable String name){
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
	}
	
	@PostMapping
	public ResponseEntity<Category> post(@Valid @RequestBody Category category){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(category));
	}
	
	@PutMapping
	public ResponseEntity<Category> put(@Valid @RequestBody Category category){
		return ResponseEntity.ok(repository.save(category));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
