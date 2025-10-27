package com.stenaeke.spring_webapp.bootstrap;

import com.stenaeke.spring_webapp.domain.Author;
import com.stenaeke.spring_webapp.domain.Book;
import com.stenaeke.spring_webapp.domain.Publisher;
import com.stenaeke.spring_webapp.repositories.AuthorRepository;
import com.stenaeke.spring_webapp.repositories.BookRepository;
import com.stenaeke.spring_webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author john = new Author();
        john.setFirstName("John");
        john.setLastName("Doe");

        Book theRoad = new Book();
        theRoad.setTitle("The Road");
        theRoad.setISBN("22551133");

        Author johnSaved = authorRepository.save(john);
        Book theRoadSaved = bookRepository.save(theRoad);

        Author jane = new Author();
        jane.setFirstName("Jane");
        jane.setLastName("Doe");

        Book theStreet = new Book();
        theStreet.setTitle("The Street");
        theStreet.setISBN("12312312");

        Author janeSaved = authorRepository.save(jane);
        Book theStreetSaved = bookRepository.save(theStreet);

        johnSaved.getBooks().add(theRoadSaved);
        janeSaved.getBooks().add(theStreetSaved);
        theRoadSaved.getAuthor().add(johnSaved);
        theStreetSaved.getAuthor().add(janeSaved);

        Publisher pengiunPublishing = new Publisher();
        pengiunPublishing.setPublisherName("Penguin Publishing");
        pengiunPublishing.setAddress("North Pole st");
        pengiunPublishing.setCity("Pole City");
        pengiunPublishing.setZip("00001");
        pengiunPublishing.setState("West north pole");

        Publisher savedPublisher = publisherRepository.save(pengiunPublishing);

        theRoadSaved.setPublisher(savedPublisher);
        theStreetSaved.setPublisher(savedPublisher);
        bookRepository.save(theRoadSaved);
        bookRepository.save(theStreetSaved);

        authorRepository.save(janeSaved);
        authorRepository.save(johnSaved);


        System.out.println("In bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
