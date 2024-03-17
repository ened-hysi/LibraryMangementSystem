package repository;

import entity.Author;
import entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AuthorRepository {
    private final EntityManager em;

    public AuthorRepository(EntityManager em) {
        this.em = em;
    }

    public Author add(Author author){
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            em.persist(author);
            transaction.commit();
            return author;
        } catch (final Exception e) {

            return null;
        }

    }

    public Integer removeAuthor(Integer id){
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("DELETE from Author a WHERE a.id = :id");
            query.setParameter("id",id);
            int deletedRows = query.executeUpdate();
            transaction.commit();
            return deletedRows;
        } catch (Exception e){
            System.err.println("something went wrong" +e);
        }
        return 0;
    }


    public List<String> searchByNationality(String nationality){
        try{
            TypedQuery<String> query = em.createQuery("SELECT a.authorName from Author a WHERE a.nationality = :nationality", String.class);
            query.setParameter("nationality",nationality);
            List<String> authors = query.getResultList();
            if (authors == null) {
                return Collections.emptyList();
            }
            return authors;
        } catch (Exception e){
            System.err.println("something went wrong");
        }
        return Collections.emptyList();
    }

}
