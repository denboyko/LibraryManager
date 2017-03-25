package com.bambuk.librarymanager.dao;

import com.bambuk.librarymanager.model.Book;

import java.io.Serializable;
import java.util.List;

/**
 * DAO class for {@link Book}.
 *
 * @author Den Boyko
 * @version 1.0
 */
public interface BookDao<T, Id extends Serializable> {

    void add(T entity);

    void update(Book entity);

    void delete(T entity);

    List<T> findAll();
}
