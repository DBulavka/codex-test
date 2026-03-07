package com.example.api.dto;

public record ClientSearchRequest(
        String firstName,
        String lastName,
        String email,
        String phone,
        String city,
        Integer minAge,
        Integer maxAge
) {
}
