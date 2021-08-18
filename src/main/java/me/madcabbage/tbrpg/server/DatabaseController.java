package me.madcabbage.tbrpg.server;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    @PersistenceUnit(name = "TextRPG")
    private static EntityManagerFactory Entity_Manager_Factory = Persistence.createEntityManagerFactory("TextRPG"); /*= */
    private final SettingsFile config;

    public DatabaseController(SettingsFile config) {
        this.config = config;
    }

    public static void addUser(String firstName, String lastName, String username, String email)  {
        EntityManager em = Entity_Manager_Factory.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmailAddress(email);
            user.setBalanceCents(150000L);

            em.persist(user);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static User getUser(int id) {
        EntityManager em = Entity_Manager_Factory.createEntityManager();
        String query = "SELECT u FROM User u WHERE u.id = :id";

        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("id", id);
        User user = null;
        try {
            user = tq.getSingleResult();
            System.out.println(user.getFirstName() + " " + user.getLastName());
        } catch(NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return user;
    }

    public static List<User> getUsers(int id) {
        EntityManager em = Entity_Manager_Factory.createEntityManager();
        String query = "SELECT u FROM User u WHERE u.id IS NOT NULL";

        TypedQuery<User> tq = em.createQuery(query, User.class);
        List<User> users = new ArrayList<>();
        try {
            users = tq.getResultList();
            users.forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName()));

        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return users;
    }

    public static void updateUser(User updatedUser) {
        EntityManager em = Entity_Manager_Factory.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            em.persist(updatedUser);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void close() {
        Entity_Manager_Factory.close();
    }

    public String getPassword(String username) {
        EntityManager em = Entity_Manager_Factory.createEntityManager();
        String query = "SELECT user. FROM User user WHERE user.username = user.username";

        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("username", username);

        String password = "";
        try {
            password = tq.getSingleResult();

        } catch(NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return password;
    }
}
