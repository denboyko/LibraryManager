package com.bambuk.librarymanager.dao.impl;

import com.bambuk.librarymanager.dao.BookDao;
import com.bambuk.librarymanager.model.Book;
import com.bambuk.librarymanager.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Implementation of {@link BookDao} interface.
 *
 * @author Den Boyko
 * @version 1.0
 */
public class BookDaoImpl implements BookDao<Book, Long> {

    private Session session;

    private Transaction transaction;

    public BookDaoImpl() {
    }

    public Session openSession() {
        session = getSessionFactory().openSession();
        return session;
    }

    public Session openSessionWithTransaction() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public void closeSessionWithTransaction() {
        transaction.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

    public void add(Book entity) {
        getSession().save(entity);
    }

    public void update(Book entity) {
        getSession().update(entity);
    }

    public void delete(Book entity) {
        getSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Book> findAll() {
        List<Book> books = (List<Book>) getSession().createQuery("from Book").list();
        return books;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction currentTransaction) {
        this.transaction = currentTransaction;
    }
}