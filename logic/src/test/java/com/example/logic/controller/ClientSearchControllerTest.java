package com.example.logic.controller;

import com.example.api.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientSearchControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllClients() {
        ClientDto[] response = restTemplate.getForObject("/clients", ClientDto[].class);

        assertThat(response).isNotNull();
        assertThat(response).hasSize(10);
        assertThat(response[0].id()).isEqualTo(1L);
    }

    @Test
    void shouldReturnClientById() {
        ClientDto response = restTemplate.getForObject("/clients/3", ClientDto.class);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(3L);
        assertThat(response.firstName()).isEqualTo("Алексей");
    }

    @Test
    void shouldReturnNotFoundForUnknownClientId() {
        HttpStatusCode statusCode = restTemplate.getForEntity("/clients/999", String.class).getStatusCode();

        assertThat(statusCode.value()).isEqualTo(404);
    }
}
