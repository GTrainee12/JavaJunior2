package ru.geekbrains.lesson4.HomeWork;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {

    public static void main(String[] args) {

        // Конфигурация Hibernate
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Course.class);

        // Настройка фабрики сессий Hibernate
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Создание объекта сеанса Hibernate
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            // Вставка данных в таблицу Courses
            transaction = session.beginTransaction();
            Course course1 = new Course("Math", 60);
            session.save(course1);
            Course course2 = new Course("Physics", 90);
            session.save(course2);
            transaction.commit();

            // Чтение данных из таблицы Courses
            transaction = session.beginTransaction();
            List<Course> courses = session.createQuery("from Course", Course.class).list();
            for (Course course : courses) {
                System.out.println("ID: " + course.getId());
                System.out.println("Title: " + course.getTitle());
                System.out.println("Duration: " + course.getDuration());
                System.out.println("-------------------");
            }
            transaction.commit();

            // Обновление данных в таблице Courses
            transaction = session.beginTransaction();
            Course courseToUpdate = session.get(Course.class, 1);
            courseToUpdate.setTitle("Mathematics");
            session.update(courseToUpdate);
            transaction.commit();

            // Удаление данных из таблицы Courses
            transaction = session.beginTransaction();
            Course courseToDelete = session.get(Course.class, 2);
            session.delete(courseToDelete);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
