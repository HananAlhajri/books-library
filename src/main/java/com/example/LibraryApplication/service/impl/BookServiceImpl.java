package com.example.LibraryApplication.service.impl;

import com.example.LibraryApplication.entity.Book;
import com.example.LibraryApplication.repo.BookRepository;
import com.example.LibraryApplication.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Hanan Al-Hajri
 * @created 2023/11/08
 **/

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        log.info("adding new book");
        return bookRepository.save(book);
    }

    @Override
    @CachePut(cacheNames = "books", key = "#book.id")
    public Book updateBook(Book book) {
        bookRepository.update(book.getId(), book.getName(), book.getCategory(), book.getAuthor(), book.getPublisher(), book.getEdition());
        log.info("book with id - {} updated", book.getId());
        return book;
    }

    @Override
    @Cacheable(cacheNames = "books", key = "#id" )
    public Book getBook(long id) {
        log.info("fetching book with id - {} from db", id);
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseGet(Book::new);
    }

    @Override
    @CacheEvict(cacheNames = "books", key = "#id")
    public String deleteBook(long id) {
        bookRepository.deleteById(id);
        log.info("book with id - {} is deleted", id);

        return "Book deleted";
    }

}
