package com.bambuk.librarymanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * JavaBean object.
 *
 * @author Den Boyko
 * @version 1.0
 */

@Entity
@Table(name="tBooks")
public class Book {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    public Book() {
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toString() {
        return "" + this.getAuthor() + "\t\"" + this.getName() + "\" ";
    }
}
