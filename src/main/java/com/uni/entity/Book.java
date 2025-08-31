package com.uni.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbnnumber;


    public Book(String title, String author, String isbnnumber) {
        this.title = title;
        this.author = author;
        this.isbnnumber = isbnnumber;
    }



}
