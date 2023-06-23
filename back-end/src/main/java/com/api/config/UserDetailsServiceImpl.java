package com.api.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.domain.models.UserModel;
import com.api.repository.UserRepository;

import jakarta.transaction.Transactional;



	@Service
	@Transactional
	public class UserDetailsServiceImpl implements UserDetailsService {

	    final UserRepository userRepository;

	    public UserDetailsServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        UserModel userModel = userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
	        return userModel; // Retornar a instância de UserModel encontrada
	    }

	}

