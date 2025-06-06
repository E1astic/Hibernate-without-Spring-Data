package ru.fil.training.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.fil.training.model.dto.ClientRequest;
import ru.fil.training.model.entity.Client;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("jpa")
public class EmfClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Client client) {
        entityManager.persist(client);
    }

    @Override
    public List<Client> findAll() {
        return entityManager.createQuery("FROM Client", Client.class).getResultList();
    }

    @Override
    public Optional<Client> findById(int id) {
        return Optional.ofNullable(entityManager.find(Client.class, id));
    }

    @Override
    public boolean updateById(int id, ClientRequest updatedClient) {
        int isUpdated = entityManager.createQuery(
                "UPDATE Client SET name = :name, age = :age, description = :description WHERE id = :id")
                .setParameter("name", updatedClient.getName())
                .setParameter("age", updatedClient.getAge())
                .setParameter("description", updatedClient.getDescription())
                .executeUpdate();
        return isUpdated != 0;
    }

    @Override
    public boolean removeById(int id) {
        int isDeleted = entityManager.createQuery("DELETE FROM Client c WHERE c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return isDeleted != 0;
    }
}
