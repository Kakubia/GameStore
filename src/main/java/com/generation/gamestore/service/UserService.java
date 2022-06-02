package com.generation.gamestore.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.generation.gamestore.model.User;
import com.generation.gamestore.model.UserLogin;
import com.generation.gamestore.repository.UserRepository;

@Service
	public class UserService {
		@Autowired
		private UserRepository repository;
		
		//função para cadastrar um usuário
		public Optional<User> registerUser(User user){
			
			//verificar se já há o usuário no BD
			if(repository.findByUser(user.getUser()).isPresent())
				return Optional.empty();
			
			//caso não exista, vamos criptografar a senha do user 
			user.setPassword(encryptPassword(user.getPassword()));
			
			//por fim, salvar o usuário com a senha já criptografada no BD
			return Optional.of(repository.save(user));
		}
		//Função para criptografar a senha digitada pelo usuário
		public String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder.encode(password);
		}
		
		public Optional<UserLogin> authenticateUser(Optional<UserLogin> userLogin){
			Optional<User> user = repository.findByUser(userLogin.get().getUser());
			
			if(user.isPresent()) {
				if(comparePasswords(userLogin.get().getPassword(), user.get().getPassword())) {
					userLogin.get().setId(user.get().getId());
					userLogin.get().setToken(generateBasicToken(userLogin.get().getUser(), userLogin.get().getPassword()));
					userLogin.get().setPassword(user.get().getPassword());
					
					return userLogin;
				}
			}

			return Optional.empty();
		}
			
		private boolean comparePasswords(String passwordTyped, String passwordDatabase) {
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			return encoder.matches(passwordTyped, passwordDatabase);

		}
		
		private String generateBasicToken(String user, String password) {

			String token = user + ":" + password;
			byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
			return "Basic " + new String(tokenBase64);

		}


}	