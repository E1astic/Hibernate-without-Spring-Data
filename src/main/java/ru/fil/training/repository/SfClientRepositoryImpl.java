package ru.fil.training.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.fil.training.model.entity.Client;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Profile("hibernate")
public class SfClientRepositoryImpl implements ClientRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void save(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(client);
    }

    @Override
    public List<Client> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Client", Client.class).getResultList();
    }

    @Override
    public Optional<Client> findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Client.class, id));
    }
}
