package com.example.api.dto;

public record ClientDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String city,
        Integer age
) {
}
