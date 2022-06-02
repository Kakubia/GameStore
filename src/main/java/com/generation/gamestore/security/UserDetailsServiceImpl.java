package com.generation.gamestore.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.gamestore.model.User;
import com.generation.gamestore.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	//Injetando o repository
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUser(userName);
		user.orElseThrow(()-> new UsernameNotFoundException(userName + "User not found"));
		//atribui o resultado encontrado no optional para alimentar o UserDetailsImpl
		return user.map(UserDetailsImpl::new).get();
	}
}