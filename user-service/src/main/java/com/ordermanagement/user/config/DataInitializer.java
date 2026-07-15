package com.ordermanagement.user.config;

import com.ordermanagement.user.model.entity.Role;
import com.ordermanagement.user.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        createRoleIfNotExists(Role.RoleName.ROLE_CUSTOMER);
        createRoleIfNotExists(Role.RoleName.ROLE_ADMIN);
        createRoleIfNotExists(Role.RoleName.ROLE_VENDOR);
    }

    private void createRoleIfNotExists(Role.RoleName roleName) {
        roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(roleName)));
    }
}