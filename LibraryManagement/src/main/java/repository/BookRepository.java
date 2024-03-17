package repository;

import entity.Author;
import entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class BookRepository {
    private final EntityManager em;

    public BookRepository(EntityManager em) {
        this.em = em;
    }

    public Book add(Book book){
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e){
            System.err.println("something went wrong");
        }
        return null;
    }


    public Long noOfBooksByAuthorName (String name){
        try{
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(b.id) FROM Book b WHERE b.author.authorName = :name",
                    Long.class);

            query.setParameter("name",  name);
            Long result = query.getSingleResult();
            if (result == null) {
                return null;
            }
            return result;
        } catch (Exception e){
            System.err.println("something went wrong");
        }
        return null;
    }
}
