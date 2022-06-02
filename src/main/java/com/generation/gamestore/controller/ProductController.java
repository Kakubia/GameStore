package com.generation.gamestore.controller;

import java.math.BigDecimal;
import java.util.List;

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

import com.generation.gamestore.model.Product;
import com.generation.gamestore.repository.ProductRepository;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> GetById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Product>> getByName(@PathVariable String name){
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
	}
	
	@GetMapping("/price{price}")
	public ResponseEntity<List<Product>> getByPrice(@PathVariable BigDecimal price){
		return ResponseEntity.ok(repository.findByPrice(price));
	}
	
	@PostMapping
	public ResponseEntity<Product> post(@RequestBody Product product){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
	}
	
	@PutMapping
	public ResponseEntity<Product> put(@RequestBody Product product){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(product));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
}



