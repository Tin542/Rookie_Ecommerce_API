package com.tinnt.AssigmentRookie.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinnt.AssigmentRookie.entity.Account;
import com.tinnt.AssigmentRookie.constans.ERole;
import com.tinnt.AssigmentRookie.entity.Role;
import com.tinnt.AssigmentRookie.payload.request.LoginRequest;
import com.tinnt.AssigmentRookie.payload.request.SignupRequest;
import com.tinnt.AssigmentRookie.payload.response.JwtResponse;
import com.tinnt.AssigmentRookie.payload.response.MessageResponse;
import com.tinnt.AssigmentRookie.repository.AccountRepository;
import com.tinnt.AssigmentRookie.repository.RoleRepository;
import com.tinnt.AssigmentRookie.security.jwt.JwtUtils;
import com.tinnt.AssigmentRookie.security.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AccountRepository accRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	PasswordEncoder encode;
	
	 @PostMapping("/signin")
	 public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		 //tell to authenticationManager how we load data from database and the password encoder
		 Authentication authentication = authenticationManager.authenticate(
		            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		 
		// if go there, the user/password is correct
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    // generate jwt to return to client
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, 
        										userDetails.getId(), 
        										userDetails.getUsername(),
        										userDetails.getFullname(),
        										userDetails.getEmail(),
        										userDetails.getPhone(),
        										userDetails.getAddress(),
        										roles));
	 }
	 
	 @PostMapping("/signup")
	 public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest){
		 
		 if (accRepository.existsByUsername(signUpRequest.getUsername())) {
			 return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		 }

		 if(accRepository.existsByEmail(signUpRequest.getEmail())){
			 return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
		 }

		 //create account
		 Account acc = new Account(signUpRequest.getUsername(),
				 					signUpRequest.getEmail(),
				 					encode.encode(signUpRequest.getPassword()), 
				 					signUpRequest.getFullname(),
				 					signUpRequest.getAddress(),
				 					signUpRequest.getPhone());
		 
		 Set<String> strRoles = signUpRequest.getRole();
	     Set<Role> roles = new HashSet<>();
		 
	     if (strRoles == null) {
	    	 Role accountRole = roleRepository.findByName(ERole.ROLE_USER)
	    			 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    	 roles.add(accountRole);
	     }else {
	    	 strRoles.forEach(role ->{
	    		 switch (role.toLowerCase()) {
	    		 case "admin":
	    			 Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	    			 		.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    			 
	    			 roles.add(adminRole);
	    			 break;
	    			 
	    		 default:
	    			 Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	    			 		.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    			 
	    			 roles.add(userRole);
	    		 }
	    	 });
	     }
	     
	     acc.setRoles(roles);
	     accRepository.save(acc);
	     
	     return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	 }
}
