package com.jdbc;

import com.jdbc.entity.Instructor;
import com.jdbc.entity.InstructorDetail;
import com.jdbc.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Instructor tempInstructor =
                    new Instructor("Chad", "Darby", "darby@jdbc.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail(
                            "http://www.luv2code.com/youtube",
                            "Luv 2 code!!!");
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.beginTransaction();

            //save ;also saves detail
            //because of cascade
            System.out.println("saving"+tempInstructor);
            session.save(tempInstructor);


            session.getTransaction().commit();

            System.out.println("Done");
        }
        finally {
            factory.close();
        }
    }

}
