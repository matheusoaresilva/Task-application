package com.api.controller;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.models.UserModel;
import com.api.repository.UserRepository;
import com.api.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class LoginController {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> findAllUsers() {
	    List<UserModel> users = userService.findAll();

	    if (users.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	}

	
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
	    // Verifica se o usuário já existe
	    Optional<UserModel> existingUser = userRepository.findByUsername(userModel.getUsername());
	    if (existingUser.isPresent()) {
	        return new ResponseEntity<>("Usuário já existe", HttpStatus.BAD_REQUEST);
	    }

	    // Criptografa a senha antes de salvar no banco de dados
	    userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
	    userRepository.save(userModel);
	    return ResponseEntity.ok("Usuário criado com sucesso");
	}

	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserModel user) {
	    String username = user.getUsername();
	    String password = user.getPassword();

	    // Busca o usuário no banco de dados pelo nome de usuário
	    Optional<UserModel> existingUser = userRepository.findByUsername(username);

	    if (existingUser.isPresent()) {
	        UserModel storedUser = existingUser.get();
	        String storedPassword = storedUser.getPassword();

	        // Verifica se a senha fornecida corresponde à senha armazenada
	        if (passwordEncoder.matches(password, storedPassword)) {
	            // Senha válida, gera e retorna o token JWT
	            String token = generateJwtToken(username);
	            // Cria um mapa para armazenar o token
	            Map<String, String> response = new HashMap<>();
	            response.put("token", token);
	            		System.out.println("Acesso liberado: " + token);
	            return ResponseEntity.ok("Acesso liberado: " + response);
	            
	        }
	    }

	    // Nome de usuário ou senha inválidos
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nome de usuário ou senha inválidos");
	}

	
	
	private String generateJwtToken(String username) {
	    // Gera uma chave segura para o algoritmo HS256
	    Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	    // Define a duração do token em milissegundos (1 hora neste exemplo)
	    long tokenExpiration = 3600000;

	    // Gera o token JWT
	    String token = Jwts.builder()
	            .setSubject(username)
	            .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
	            .signWith(signingKey)
	            .compact();

	    return token;
	}
}
