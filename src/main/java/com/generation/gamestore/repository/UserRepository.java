package com.generation.gamestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.gamestore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	//Quando não se sabe qual tipo de resposta pode ter ao buscar na requisição e tem mais
	//de uma possível
	public Optional<User> findByUser(String user);
}

