package com.ordermanagement.user.model.dto;

import java.util.Set;

public record AuthResponse(
        Long userId,
        String email,
        String firstName,
        String lastName,
        Set<String> roles,
        String accessToken,
        String tokenType,
        Long expiresIn
) {
}