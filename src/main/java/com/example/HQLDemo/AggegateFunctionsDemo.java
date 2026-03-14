package com.example.HQLDemo;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.example.entity.Student;
import com.example.util.HibernateUtil;
@SuppressWarnings("deprecation")
public class AggegateFunctionsDemo
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int choice;
        do 
        {
            System.out.println("\n HQL Operations on DB ");
            System.out.println("1. Get Students by Department");
            System.out.println("2. Get Students with Marks Greater Than");
            System.out.println("3. Total Students Count");
            System.out.println("4. Sort Students by Marks");
            System.out.println("5. Pagination");
            System.out.println("6. Criteria (Marks > 80)");
            System.out.println("7. Average Marks");
            System.out.println("8. Maximum Marks");
            System.out.println("9. Minimum Marks");
            System.out.println("10. Sum of Marks");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) 
            {
                case 1:
                    System.out.print("Enter Department: ");
                    String dept = sc.next();
                    Query<Student> q1 =session.createQuery("FROM Student WHERE department = :dept", Student.class);
                    q1.setParameter("dept", dept);
                    List<Student> list1 = q1.list();
                    list1.forEach(s -> System.out.println(s.getName()));
                    break;

                case 2:
                    System.out.print("Enter marks: ");
                    int marks = sc.nextInt();
                    Query<Student> q2 =session.createQuery("FROM Student WHERE marks > ?0", Student.class);
                    q2.setParameter(0, marks);
                    List<Student> list2 = q2.list();
                    list2.forEach(s -> System.out.println(s.getName()));
                    break;

                case 3:
                    Query<Long> q3 =session.createQuery("SELECT COUNT(s.id) FROM Student s", Long.class);
                    System.out.println("Total Students: " + q3.uniqueResult());
                    break;

                case 4:
                    Query<Student> q4 =session.createQuery("FROM Student ORDER BY marks DESC", Student.class);
                    List<Student> sorted = q4.list();
                    sorted.forEach(s -> System.out.println(s.getName() + " - " + s.getMarks()));
                    break;

                case 5:
                    Query<Student> q5 =session.createQuery("FROM Student", Student.class);
                    q5.setFirstResult(0);
                    q5.setMaxResults(3);
                    List<Student> page = q5.list();
                    page.forEach(s -> System.out.println(s.getName()));
                    break;
                case 6:
                    Criteria c = session.createCriteria(Student.class);
                    c.add(Restrictions.gt("marks", 80));
                    List<Student> clist = c.list();
                    clist.forEach(s -> System.out.println(s.getName()));
                    break;
                case 7:
                    Query<Double> avgQuery =session.createQuery("SELECT AVG(s.marks) FROM Student s", Double.class);
                    System.out.println("Average Marks: " + avgQuery.uniqueResult());
                    break;
                case 8:
                    Query<Integer> maxQuery =session.createQuery("SELECT MAX(s.marks) FROM Student s", Integer.class);
                    System.out.println("Maximum Marks: " + maxQuery.uniqueResult());
                    break;
                case 9:
                    Query<Integer> minQuery =session.createQuery("SELECT MIN(s.marks) FROM Student s", Integer.class);
                    System.out.println("Minimum Marks: " + minQuery.uniqueResult());
                    break;
                case 10:
                    Query<Long> sumQuery =session.createQuery("SELECT SUM(s.marks) FROM Student s", Long.class);
                    System.out.println("Sum of Marks: " + sumQuery.uniqueResult());
                    break;
                case 0:
                    System.out.println("Exiting Application");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 0);

        tx.commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        sc.close();
    }
}
