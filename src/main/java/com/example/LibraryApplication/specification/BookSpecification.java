package com.example.LibraryApplication.specification;

import com.example.LibraryApplication.entity.Book;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Hanan Al-Hajri 2024/01/28
 */

public class BookSpecification {
    public static Specification<Book> getByCategory(String category) {
        return (root, query, builder) -> builder.equal(root.get("category"), category);
    }
}
