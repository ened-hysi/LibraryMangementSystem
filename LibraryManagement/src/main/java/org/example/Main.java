package org.example;

import entity.Author;
import entity.Book;
import entity.Customer;
import entity.Reservation;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.AuthorRepository;
import repository.BookRepository;
import repository.CustomerRepository;
import repository.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Reservation.class)
                .buildSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        AuthorRepository authorRepository = new AuthorRepository(entityManager);
        BookRepository bookRepository = new BookRepository(entityManager);
        CustomerRepository customerRepository = new CustomerRepository(entityManager);
        ReservationRepository reservationRepository = new ReservationRepository(entityManager);
        Scanner sc = new Scanner(System.in);
        Author author1 = new Author();
        Book book1 = new Book();
        System.out.println("Mire se erdhet ne Library Management System Console Application");
        System.out.println("1 - Shtypni 1 per te shtuar nje autor");
        System.out.println("2 - Shtypni 2 per te pare emrat e autoreve sipas kombesise");
        System.out.println("3 - Shtypni 3  per te numeruar se sa libra  te  te njetit autor ka ne databaze");
        System.out.println("4 - Shtypni  4 per te pare username e klienteve ne baze te titujt e librave");
        System.out.println("5 - Shtypni 5 per te pare emrin e rezervimit ne baze te id");
        System.out.println("6 - Shtypni 6 per te shtuar nje liber");
        System.out.println("7 - Shtypni  7 per te fshire nje rresht ne databaze");
        int cnt = sc.nextInt();
        while (cnt != -1){
            if (cnt == 1 ){
                sc.nextLine();
            System.out.println("Te lutem vendos emrin e autorit");
            author1.setAuthorName(sc.nextLine());
            System.out.println("Te lutem vendos ditelindjen");
            author1.setBirthDate(sc.nextLine());
            System.out.println("Te lutem vendos kombesine");
            author1.setNationality(sc.nextLine());
            Author authoradd = authorRepository.add(author1);
            System.out.println(authoradd);}
            else if (cnt == 2) {
                sc.nextLine();
                System.out.println("Vendos kombesine ");
               String nationality =  sc.nextLine();
                List<String> authors = authorRepository.searchByNationality(nationality);
                System.out.println("Emri i Autoreve sipas kombesise ==>" +authors);
            }
            else if (cnt == 3){
                sc.nextLine();
                System.out.println("Vendos emrin e Autorit ");
                String name = sc.nextLine();
                Long noOfBooksByAuthorName = bookRepository.noOfBooksByAuthorName(name);
                System.out.println("Numri i Librave me te njejtin Autor ==> "+noOfBooksByAuthorName);
            }
             else if (cnt == 4) {
                 sc.nextLine();
                System.out.println("Vendos titullin e librit");
                String title = sc.nextLine();
                List<String> customers = customerRepository.searchByBookTitle(title);
                System.out.println("Username ==>" +customers);
            }
             else if (cnt == 5) {
                 sc.nextLine();
                System.out.println("Vendos id e rezervimit");
                Integer id = sc.nextInt();
                String reservationName = reservationRepository.findByIDReservationDate(id);
                System.out.println("Emri i rezervimit ==> " +reservationName);
            }
             else if (cnt == 6){
                sc.nextLine();
                System.out.println("Te lutem vendos titullin e librit");
                book1.setTitle(sc.nextLine());
                System.out.println("Te lutem vendos llojin e zhanrit");
               book1.setGenre(sc.nextLine());
                System.out.println("Te lutem vendos kopjet ne total");
                book1.setTotalCopies(sc.nextInt());
                System.out.println("Te lutem vendos kopjet ne disponim");
                book1.setCopiesAvailable(sc.nextInt());
                System.out.println("Te lutem vendos publication year");
                book1.setPublicationYear(sc.nextInt());
                Book bookadd = bookRepository.add(book1);
                System.out.println(bookadd);
            }
             else if (cnt == 7){
                System.out.println("Te lutem vendos id e autorit qe do te heqesh nga lista");
                int id = sc.nextInt();
                System.out.println("Rreshti i fshire i konfirmuar  ==>" +authorRepository.removeAuthor(id));
            }

           cnt = sc.nextInt();
        }

    }
}