package com.example.logic.controller;

import com.example.api.dto.ClientSearchRequest;
import com.example.api.dto.ClientSearchResponse;
import com.example.api.rest.ClientSearchApi;
import com.example.logic.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientSearchController implements ClientSearchApi {

    private final ClientService clientService;

    @Override
    public ClientSearchResponse searchClients(ClientSearchRequest request) {
        return new ClientSearchResponse(clientService.search(request));
    }
}
