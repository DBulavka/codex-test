package com.example.logic.model;

public record Client(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String city,
        Integer age
) {
}
