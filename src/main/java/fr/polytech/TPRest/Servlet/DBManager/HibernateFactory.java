package fr.polytech.TPRest.Servlet.DBManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateFactory {

    public static Session getSession() {
        SessionFactory sessionFactory;
        try {
            Configuration cfg = new Configuration();
            sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            Logger.getLogger(HibernateFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory.openSession();
    }
}
