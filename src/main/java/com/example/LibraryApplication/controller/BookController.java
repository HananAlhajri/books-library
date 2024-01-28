package com.example.LibraryApplication.controller;

import com.example.LibraryApplication.dto.PagingResponse;
import com.example.LibraryApplication.entity.Book;
import com.example.LibraryApplication.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

/**
 * @author Hanan Al-Hajri
 * @created 2023/11/08
 **/

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBook(id);
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("/books-by-category")
    public PagingResponse<Book> getBooksByCategory(Pageable pageable,
                                                   @RequestParam(name = "category") String category) {
        return bookService.getBooksByCategory(category, pageable);
    }
}
