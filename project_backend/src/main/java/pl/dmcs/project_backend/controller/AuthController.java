package pl.dmcs.project_backend.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.message.request.LoginForm;
import pl.dmcs.project_backend.message.request.SignUpForm;
import pl.dmcs.project_backend.message.response.JwtResponse;
import pl.dmcs.project_backend.message.response.ResponseMessage;
import pl.dmcs.project_backend.model.Account;
import pl.dmcs.project_backend.model.Role;
import pl.dmcs.project_backend.model.RoleName;
import pl.dmcs.project_backend.repository.AccountRepository;
import pl.dmcs.project_backend.repository.RoleRepository;
import pl.dmcs.project_backend.security.jwt.JwtProvider;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {
    private DaoAuthenticationProvider daoAuthenticationProvider;
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;

    @Autowired
    public AuthController(DaoAuthenticationProvider daoAuthenticationProvider, AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = daoAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (accountRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken."), HttpStatus.BAD_REQUEST);
        }
        // Create user account
        Account user = new Account(signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: Admin Role not found."));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: User Role not found."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        accountRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully."), HttpStatus.OK);
    }
}
