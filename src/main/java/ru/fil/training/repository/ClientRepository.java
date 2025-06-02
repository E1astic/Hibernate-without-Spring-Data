package ru.fil.training.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.fil.training.model.entity.Client;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Client client) {
        entityManager.persist(client);
    }

    public List<Client> findAll() {
        System.out.println(entityManager);
        return entityManager.createQuery("FROM Client", Client.class).getResultList();
    }

    public Optional<Client> findById(int id) {
        return Optional.ofNullable(entityManager.find(Client.class, id));
    }
}
