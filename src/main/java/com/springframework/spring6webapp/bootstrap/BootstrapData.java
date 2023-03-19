package com.springframework.spring6webapp.bootstrap;

import com.springframework.spring6webapp.domain.Author;
import com.springframework.spring6webapp.domain.Book;
import com.springframework.spring6webapp.repositories.AuthorRepository;
import com.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private  final  BookRepository bookRepository;
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository= bookRepository;
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
        Eric.setFirstName("John");
        Eric.setLastName("Rod");

        Book ejb = new Book();
        ddd.setTitle("J2EE");
        ddd.setIsbn("365823");

        Author JohnSaved = authorRepository.save(John);
        Book ejbSaved = bookRepository.save(ejb);

        EricSaved.getBooks().add(dddSaved);
        JohnSaved.getBooks().add(ejbSaved);

        authorRepository.save(EricSaved);
        authorRepository.save(JohnSaved);

        System.out.println("In Bootstrap");

        System.out.println("Author Count: "+ authorRepository.count());
        System.out.println("Book Count: "+ bookRepository.count());

    }
}
