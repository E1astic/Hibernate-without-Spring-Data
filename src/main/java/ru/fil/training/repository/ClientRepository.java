package ru.fil.training.repository;

import ru.fil.training.model.entity.Client;

import java.util.List;
import java.util.Optional;


public interface ClientRepository {

    void save(Client client);

    List<Client> findAll();

    Optional<Client> findById(int id);
}
