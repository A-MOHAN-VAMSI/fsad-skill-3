package com.example.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Product;
import com.example.entity.Student;
public class HibernateUtil 
{
    private static SessionFactory factory;

    static 
    {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() 
    {
        return factory;
    }
}
