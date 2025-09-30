package Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import entities.Client;

import java.util.List;

public class ClientDao {
    private final EntityManager em;

    public ClientDao() {
        em= Persistence.createEntityManagerFactory("Client").createEntityManager();

    }

    public void addClient(Client client) {
        em.getTransaction().begin();
        try{
            em.persist(client);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void removeClient(long id) {
        em.getTransaction().begin();
        Client c= em.find(Client.class, id);
        em.remove(c);
        em.getTransaction().commit();

    }

    public void updateClient(Client client) {
        em.getTransaction().begin();
        Client c= em.find(Client.class, client.getId());

        if(c!=null) {
            c.setFirstName(client.getFirstName());
            c.setLastName(client.getLastName());
            c.setEmail(client.getEmail());
            c.setPhone(client.getPhone());
            em.merge(c);
        }
            em.getTransaction().commit();

    }

    public Client getClient(long id) {
        em.getTransaction().begin();
        Client c= em.find(Client.class, id);
        if(c==null) {
            System.out.println("Client not found");
        }
        em.getTransaction().commit();
        return c;
    }

    public List<Client> getAllClients() {
        em.getTransaction().begin();
        List<Client> clients= em.createQuery("from Client", Client.class).getResultList();
        em.getTransaction().commit();
        return clients;
    }

}
