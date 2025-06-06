package ru.fil.training.repository;

import ru.fil.training.model.dto.ClientRequest;
import ru.fil.training.model.entity.Client;

import java.util.List;
import java.util.Optional;


public interface ClientRepository {

    void save(Client client);

    List<Client> findAll();

    Optional<Client> findById(int id);

    boolean updateById(int id, ClientRequest updatedClient);

    boolean removeById(int id);
}
