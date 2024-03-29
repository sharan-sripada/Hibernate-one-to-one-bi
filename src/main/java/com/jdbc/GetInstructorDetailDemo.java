package com.jdbc;

import com.jdbc.entity.Instructor;
import com.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {

            session.beginTransaction();

            int id=2;
            InstructorDetail instructorDetail=session.get(InstructorDetail.class,id);
            System.out.println(instructorDetail);

            System.out.println("associated: "+ instructorDetail.getInstructor());

            session.getTransaction().commit();

            System.out.println("Done");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            factory.close();
        }
    }

}
