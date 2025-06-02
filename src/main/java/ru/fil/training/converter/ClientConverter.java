package ru.fil.training.converter;

import org.springframework.stereotype.Component;
import ru.fil.training.model.dto.ClientRequest;
import ru.fil.training.model.dto.ClientResponse;
import ru.fil.training.model.entity.Client;

@Component
public class ClientConverter {

    public ClientResponse mapToClientResponse(Client client) {
        return ClientResponse.builder()
                .name(client.getName())
                .age(client.getAge())
                .description(client.getDescription())
                .build();
    }

    public Client mapToClient(ClientRequest clientRequest) {
        return Client.builder()
                .name(clientRequest.getName())
                .age(clientRequest.getAge())
                .description(clientRequest.getDescription())
                .build();
    }
}
