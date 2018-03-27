package hibernate;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/hemrajdb --dbname.0 testdb
import java.sql.Date;

public class ManageTables {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("Dwape");
        user.setEmail("emailDwape@gmail.com");
        user.setPassword("password");
        user.setDateOfBirth(new Date(234234234));
        user.setName("Gianluca");
        user.setSurname("Scolaro");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();

        user = null;

        session = sessionFactory.openSession();
        session.beginTransaction();
        session.get(User.class,1);
    }
}
