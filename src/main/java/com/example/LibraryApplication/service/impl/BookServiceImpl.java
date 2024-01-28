package com.example.LibraryApplication.service.impl;

import com.example.LibraryApplication.dto.PagingResponse;
import com.example.LibraryApplication.entity.Book;
import com.example.LibraryApplication.repo.BookRepository;
import com.example.LibraryApplication.service.BookService;
import com.example.LibraryApplication.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

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
    @Cacheable(cacheNames = "books", key = "#id")
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

    @Override
    public PagingResponse<Book> getBooksByCategory(String category, Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAll(
                getBookSpecification(category),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").ascending())
        );
        return new PagingResponse<>(
                bookPage.stream().collect(Collectors.toList()),
                bookPage.getTotalElements(),
                bookPage.getTotalPages()
        );
    }

    private Specification<Book> getBookSpecification(String category) {
        Specification<Book> specification = Specification.where(null);
        specification = specification.or(category == null ? null : BookSpecification.getByCategory(category));
        return specification;
    }


}
