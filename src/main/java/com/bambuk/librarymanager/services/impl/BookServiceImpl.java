package com.bambuk.librarymanager.services.impl;

import com.bambuk.librarymanager.dao.impl.BookDaoImpl;
import com.bambuk.librarymanager.model.Book;
import com.bambuk.librarymanager.services.BookService;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Implementation of {@link BookService} interface.
 *
 * @author Den Boyko
 * @version 1.0
 */
public class BookServiceImpl implements BookService {


    private static BookDaoImpl bookDao;

    public BookServiceImpl() {
        bookDao = new BookDaoImpl();
    }

    public void add(Book entity) {
        bookDao.openSessionWithTransaction();
        bookDao.add(entity);
        bookDao.closeSessionWithTransaction();
    }

    public void update(String oldName, String newName) {
        bookDao.openSessionWithTransaction();
        Book book = bookDao.findAll().stream().filter(p -> p.getName().toLowerCase()
                .equals(oldName.toLowerCase())).findFirst().get();
        book.setName(newName);
        bookDao.update(book);
        bookDao.closeSessionWithTransaction();
    }

    public void delete(String name) {
        bookDao.openSessionWithTransaction();
        Book book = bookDao.findAll().stream().filter(p -> p.getName().toLowerCase()
                .equals(name.toLowerCase())).findFirst().get();
        bookDao.delete(book);
        bookDao.closeSessionWithTransaction();
    }

    public List<Book> getAll() {
        bookDao.openSession();
        List<Book> books = bookDao.findAll();
        bookDao.closeSession();
        return books;
    }

    public Book getByName(String name) {
        bookDao.openSession();
        Book book = bookDao.findAll().stream().filter(p -> p.getName().toLowerCase()
                .equals(name.toLowerCase())).findFirst().get();
        bookDao.closeSession();
        return book;
    }

    public List<Book> getByAuthor(String author) {
        bookDao.openSession();
        List<Book> books = bookDao.findAll().stream().filter(p -> p.getAuthor().toLowerCase()
                .equals(author.toLowerCase())).collect(Collectors.toList());
        bookDao.closeSession();
        return books;
    }

    public String help() throws IOException {
        String help = "";
        String key;
        String value;
        Enumeration<String> enumerationKeys = ResourceBundle.getBundle("help").getKeys();
        while(enumerationKeys.hasMoreElements()){
            key = enumerationKeys.nextElement();
            value = ResourceBundle.getBundle("help").getString(key);
            help = help + key + "\t-\t" + value + "\n";
        }
        return help;
    }

}
