package com.example.logic.service;

import com.example.api.dto.ClientDto;
import com.example.api.dto.ClientSearchRequest;
import com.example.logic.model.Client;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ClientService {

    private final List<Client> clients = new ArrayList<>();

    @PostConstruct
    void initClients() {
        clients.add(new Client(1L, "Иван", "Петров", "ivan.petrov@mail.ru", "+79990000001", "Москва", 28));
        clients.add(new Client(2L, "Мария", "Соколова", "m.sokolova@mail.ru", "+79990000002", "Санкт-Петербург", 34));
        clients.add(new Client(3L, "Алексей", "Кузнецов", "alex.k@mail.ru", "+79990000003", "Казань", 25));
        clients.add(new Client(4L, "Ольга", "Смирнова", "olga.s@mail.ru", "+79990000004", "Новосибирск", 41));
        clients.add(new Client(5L, "Дмитрий", "Иванов", "d.ivanov@mail.ru", "+79990000005", "Екатеринбург", 30));
        clients.add(new Client(6L, "Елена", "Федорова", "elena.f@mail.ru", "+79990000006", "Самара", 27));
        clients.add(new Client(7L, "Сергей", "Орлов", "sergey.o@mail.ru", "+79990000007", "Москва", 38));
        clients.add(new Client(8L, "Наталья", "Морозова", "nat.m@mail.ru", "+79990000008", "Нижний Новгород", 22));
        clients.add(new Client(9L, "Павел", "Волков", "p.volkov@mail.ru", "+79990000009", "Ростов-на-Дону", 45));
        clients.add(new Client(10L, "Анна", "Лебедева", "anna.l@mail.ru", "+79990000010", "Краснодар", 31));
    }

    public List<ClientDto> getAll() {
        return clients.stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<ClientDto> getById(Long id) {
        return clients.stream()
                .filter(client -> client.id().equals(id))
                .findFirst()
                .map(this::toDto);
    }

    public List<ClientDto> search(ClientSearchRequest request) {
        return clients.stream()
                .filter(client -> containsIgnoreCase(client.firstName(), request.firstName()))
                .filter(client -> containsIgnoreCase(client.lastName(), request.lastName()))
                .filter(client -> containsIgnoreCase(client.email(), request.email()))
                .filter(client -> containsIgnoreCase(client.phone(), request.phone()))
                .filter(client -> containsIgnoreCase(client.city(), request.city()))
                .filter(client -> request.minAge() == null || client.age() >= request.minAge())
                .filter(client -> request.maxAge() == null || client.age() <= request.maxAge())
                .map(this::toDto)
                .toList();
    }

    private boolean containsIgnoreCase(String value, String query) {
        if (query == null || query.isBlank()) {
            return true;
        }
        return value.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT));
    }

    private ClientDto toDto(Client client) {
        return new ClientDto(
                client.id(),
                client.firstName(),
                client.lastName(),
                client.email(),
                client.phone(),
                client.city(),
                client.age()
        );
    }
}
