package com.springframework.spring6webapp.bootstrap;

import com.springframework.spring6webapp.domain.Author;
import com.springframework.spring6webapp.domain.Book;
import com.springframework.spring6webapp.domain.Publisher;
import com.springframework.spring6webapp.repositories.AuthorRepository;
import com.springframework.spring6webapp.repositories.BookRepository;
import com.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private  final  BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository= bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author Eric = new Author();
        Eric.setFirstName("Eric");
        Eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author EricSaved = authorRepository.save(Eric);
        Book dddSaved = bookRepository.save(ddd);

        Author John = new Author();
        John.setFirstName("John");
        John.setLastName("Rod");

        Book ejb = new Book();
        ejb.setTitle("J2EE");
        ejb.setIsbn("365823");

        Author JohnSaved = authorRepository.save(John);
        Book ejbSaved = bookRepository.save(ejb);

        EricSaved.getBooks().add(dddSaved);
        JohnSaved.getBooks().add(ejbSaved);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("My publisher");
        publisher.setAddress("123 Main");
        Publisher savedPublisher = publisherRepository.save(publisher);

        dddSaved.setPublisher(savedPublisher);
        ejbSaved.setPublisher(savedPublisher);

        authorRepository.save(EricSaved);
        authorRepository.save(JohnSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(ejbSaved);



        System.out.println("In Bootstrap");

        System.out.println("Author Count: "+ authorRepository.count());
        System.out.println("Book Count: "+ bookRepository.count());
        System.out.println("Publisher Count: "+ publisherRepository.count());

    }
}
