package com.ordermanagement.user.service;

import com.ordermanagement.common.exception.DuplicateResourceException;
import com.ordermanagement.common.exception.ResourceNotFoundException;
import com.ordermanagement.user.model.dto.AuthResponse;
import com.ordermanagement.user.model.dto.LoginRequest;
import com.ordermanagement.user.model.dto.RegisterRequest;
import com.ordermanagement.user.model.entity.Role;
import com.ordermanagement.user.model.entity.User;
import com.ordermanagement.user.repository.RoleRepository;
import com.ordermanagement.user.repository.UserRepository;
import com.ordermanagement.user.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email is already registered");
        }

        Role customerRole = roleRepository.findByName(Role.RoleName.ROLE_CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", Role.RoleName.ROLE_CUSTOMER));

        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPhone(request.phone());
        user.setStatus(User.UserStatus.ACTIVE);
        Set<Role> roles = new java.util.HashSet<>();
                  roles.add(customerRole);
                  user.setRoles(roles);

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        return buildAuthResponse(savedUser, token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return buildAuthResponse(user, token);
    }

    private AuthResponse buildAuthResponse(User user, String token) {
        Set<String> roles = user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());

        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                roles,
                token,
                "Bearer",
                jwtService.getExpirationSeconds()
        );
    }
}