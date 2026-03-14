package com.example.HQLDemo;

import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.entity.Student;
import com.example.util.HibernateUtil;

public class HCQlDemo
{

    public static void main(String[] args) 
    {

        Scanner sc = new Scanner(System.in);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        int choice;

        do 
        {
            System.out.println("\n STUDENT CRITERIA MENU ");
            System.out.println("1. Marks > 80");
            System.out.println("2. Marks BETWEEN 70 AND 90");
            System.out.println("3. Marks < 60");
            System.out.println("4. Marks >= 75 AND Department = CSE");
            System.out.println("5. Sort by Marks (DESC)");
            System.out.println("6. Count Students with Marks > 80");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) 
            {

                
                case 1: 
                {
                    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
                    Root<Student> root = cq.from(Student.class);

                    cq.select(root).where(cb.gt(root.get("marks"), 80));

                    List<Student> list = session.createQuery(cq).getResultList();
                    list.forEach(s ->System.out.println(s.getName() + " - " + s.getMarks()));
                    break;
                }

                
                case 2: 
                {
                    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
                    Root<Student> root = cq.from(Student.class);

                    cq.select(root).where(cb.between(root.get("marks"), 70, 90));

                    List<Student> list = session.createQuery(cq).getResultList();
                    list.forEach(s ->System.out.println(s.getName() + " - " + s.getMarks()));
                    break;
                }

                
                case 3: 
                {
                    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
                    Root<Student> root = cq.from(Student.class);

                    cq.select(root).where(cb.lt(root.get("marks"), 60));

                    List<Student> list = session.createQuery(cq).getResultList();
                    list.forEach(s ->System.out.println(s.getName() + " - " + s.getMarks()));
                    break;
                }

                
                case 4: 
                {
                    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
                    Root<Student> root = cq.from(Student.class);

                    cq.select(root).where(cb.and(cb.ge(root.get("marks"), 75),
                              cb.equal(root.get("department"), "CSE")
                          )
                      );

                    List<Student> list = session.createQuery(cq).getResultList();
                    list.forEach(s ->System.out.println(s.getName() + " - " + s.getMarks())
                    );
                    break;
                }

                
                case 5: 
                {
                    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
                    Root<Student> root = cq.from(Student.class);

                    cq.select(root).orderBy(cb.desc(root.get("marks")));

                    List<Student> list = session.createQuery(cq).getResultList();
                    list.forEach(s ->System.out.println(s.getName() + " - " + s.getMarks())
                    );
                    break;
                }

                
                case 6: 
                {
                    CriteriaQuery<Long> cq = cb.createQuery(Long.class);
                    Root<Student> root = cq.from(Student.class);

                    cq.select(cb.count(root)).where(cb.gt(root.get("marks"), 80));

                    Long count = session.createQuery(cq).getSingleResult();
                    System.out.println("Students with Marks > 80 = " + count);
                    break;
                }

                case 0:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 0);

        tx.commit();
        session.close();
        sessionFactory.close();
        sc.close();
    }
}
