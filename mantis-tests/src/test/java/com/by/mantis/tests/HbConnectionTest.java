package com.by.mantis.tests;

import com.by.mantis.model.MantisUserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Test
    public void testHbConnection() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<MantisUserData> result = session.createQuery("from MantisUserData").list();
        for (MantisUserData user : result) {
            System.out.println(user);
        }
        session.getTransaction().commit();
        session.close();
    }
}
