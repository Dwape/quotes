package database;

import model.ManageUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import model.User;
import org.junit.Test;

import java.sql.Date;


public class HibernateTest {

    @Test
    public void addUserTest(){
        ManageUser manager = new ManageUser();
        //manager.addUser("Giansco", "gianluca.scolaro@ing.austral.edu.ar", "352312", "Gianluca", "Scolaro", new Date(1232332));
        //manager.addUser("Dwape", "eduardo.lalor@ing.austral.edu.ar", "1234323", "Eduardo", "Lalor", new Date(123233221));
        manager.addUser("Gengu", "noway@mail.com", "afdafa", "Jeff", "Fromtheoverwatchteam", new Date(123434432));
    }

    @Test
    public void deleteUserTest(){
        ManageUser manager = new ManageUser();
        manager.deleteUser("Giansco");
    }

    @Test
    public void updateUserTest(){
        ManageUser manager = new ManageUser();
        manager.changePassword("Giansco", "thisiscool");
        manager.changeName("Giansco", "Gianni");
        manager.changeSurname("Giansco", "LeagueofLegends");
        manager.changeDateOfBirth("Dwape", new Date(122));
    }
}
