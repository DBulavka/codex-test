package com.example.api.rest;

import com.example.api.dto.ClientSearchRequest;
import com.example.api.dto.ClientSearchResponse;
import com.example.api.dto.ClientDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/clients")
public interface ClientSearchApi {

    @GetMapping
    List<ClientDto> getAllClients();

    @GetMapping("/{id}")
    ClientDto getClientById(@PathVariable Long id);

    @GetMapping("/search")
    ClientSearchResponse searchClients(@ModelAttribute ClientSearchRequest request);
}
