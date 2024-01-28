package com.example.LibraryApplication.service;

import com.example.LibraryApplication.dto.PagingResponse;
import com.example.LibraryApplication.entity.Book;
import org.springframework.data.domain.Pageable;

/**
 * @author Hanan Al-Hajri
 * @created 2023/11/08
 **/

public interface BookService {
    Book addBook(Book book);

    Book updateBook(Book book);

    Book getBook(long id);

    String deleteBook(long id);

    PagingResponse<Book> getBooksByCategory(String category, Pageable pageable);
}
