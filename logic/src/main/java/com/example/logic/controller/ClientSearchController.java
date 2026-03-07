package com.example.logic.controller;

import com.example.api.dto.ClientDto;
import com.example.api.dto.ClientSearchRequest;
import com.example.api.dto.ClientSearchResponse;
import com.example.api.rest.ClientSearchApi;
import com.example.logic.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class ClientSearchController implements ClientSearchApi {

    private final ClientService clientService;

    @Override
    public List<ClientDto> getAllClients() {
        return clientService.getAll();
    }

    @Override
    public ClientDto getClientById(Long id) {
        return clientService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Client not found with id=" + id));
    }

    @Override
    public ClientSearchResponse searchClients(ClientSearchRequest request) {
        return new ClientSearchResponse(clientService.search(request));
    }
}
