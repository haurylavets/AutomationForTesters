package com.by.mantis.appmanager;

import com.by.mantis.model.MantisUserData;
import com.by.mantis.model.MantisUsers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public MantisUsers users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<MantisUserData> result = session.createQuery("from MantisUserData").list();
        session.getTransaction().commit();
        session.close();
        return new MantisUsers(result);
    }
}
