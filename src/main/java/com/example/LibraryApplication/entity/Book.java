package com.example.LibraryApplication.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Hanan Al-Hajri
 * @created 2023/11/08
 **/

@Entity
@Data
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String author;
    private String publisher;
    private String edition;
}
