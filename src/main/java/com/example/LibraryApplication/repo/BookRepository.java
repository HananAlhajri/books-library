package com.example.LibraryApplication.repo;

import com.example.LibraryApplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * @author Hanan Al-Hajri
 * @created 2023/11/08
 **/

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Transactional
    @Modifying
    @Query("update Book b set b.name=?2, b.category=3, b.author=4, b.publisher=5, b.edition=6 where b.id=?1")
    int update(long id, String name, String category, String author, String publisher, String edition);


}
