package com.bambuk.librarymanager.utils;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Simple realization of HibernateManager.
 *
 * @author Den Boyko
 * @version 1.0
 */

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());

        sessionFactory = cfg.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
