package ru.fil.training.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fil.training.converter.ClientConverter;
import ru.fil.training.exception.ClientNotFoundException;
import ru.fil.training.model.dto.ClientRequest;
import ru.fil.training.model.dto.ClientResponse;
import ru.fil.training.model.entity.Client;
import ru.fil.training.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientConverter::mapToClientResponse)
                .toList();
    }

    public ClientResponse getClientById(int id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(String.format("Клиент с id = %d не найден", id)));
        return clientConverter.mapToClientResponse(client);
    }

    @Transactional
    public void addClient(ClientRequest client) {
        clientRepository.save(clientConverter.mapToClient(client));
    }
}
