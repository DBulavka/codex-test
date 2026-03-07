package com.example.api.rest;

import com.example.api.dto.ClientSearchRequest;
import com.example.api.dto.ClientSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/clients")
public interface ClientSearchApi {

    @GetMapping("/search")
    ClientSearchResponse searchClients(@ModelAttribute ClientSearchRequest request);
}
