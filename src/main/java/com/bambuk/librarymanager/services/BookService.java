package com.bambuk.librarymanager.services;

import com.bambuk.librarymanager.model.Book;

import java.io.IOException;
import java.util.List;

/**
 * Service class for {@link Book}.
 *
 * @author Den Boyko
 * @version 1.0
 */
public interface BookService {

    void add(Book entity);

    void update(String oldName, String newName);

    void delete(String name);

    List<Book> getAll();

    Book getByName(String name);

    List<Book> getByAuthor(String author);

    String help() throws IOException;


}
