package com.ideas2it.databaseconnection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.logger.CustomLogger;

public class HibernateConfig {
    private static CustomLogger logger = new CustomLogger(HibernateConfig.class);
    private static SessionFactory sessionFactory = null;

    private HibernateConfig() {
    }
  
    public static SessionFactory getSessionFactory() {
        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return sessionFactory;
    }
}