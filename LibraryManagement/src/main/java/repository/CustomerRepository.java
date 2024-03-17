package repository;

import entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class CustomerRepository {
    private final EntityManager em;

    public CustomerRepository(EntityManager em) {
        this.em = em;
    }

    public List<String> searchByBookTitle(String title){
        try{
           TypedQuery<String> query = em.createQuery("SELECT c.userName from Customer c WHERE c.book.title = :title", String.class);

            query.setParameter("title",title);
            List<String> customers = query.getResultList();
            if (customers == null) {
                return Collections.emptyList();
            }
            return customers;
        } catch (Exception e){
            System.err.println("something went wrong " + e);
        }
        return Collections.emptyList();
    }

}
