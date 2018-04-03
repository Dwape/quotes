package database;

import hibernate.ManageUser;
import model.User;
import org.junit.Test;

import java.sql.Date;


public class HibernateTest {

    @Test
    public void addUserTest(){
        ManageUser manager = new ManageUser();
        //manager.addUser("Giansco", "gianluca.scolaro@ing.austral.edu.ar", "352312", "Gianluca", "Scolaro", new Date(1232332));
        //manager.addUser("Dwape", "eduardo.lalor@ing.austral.edu.ar", "1234323", "Eduardo", "Lalor", new Date(123233221));
        User user1 = new User("Gengu", "noway@mail.com", "afdafa", "Jeff", "Fromtheoverwatchteam", new Date(123434432));
        User user2 = new User("Giansco", "gianluca.scolaro@ing.austral.edu.ar", "352312", "Gianluca", "Scolaro", new Date(1232332));
        User user3 = new User("Dwape", "eduardo.lalor@ing.austral.edu.ar", "1234323", "Eduardo", "Lalor", new Date(123233221));
        manager.addUser(user1);
        manager.addUser(user2);
        manager.addUser(user3);
        //User user4 = new User("Dope", "mymail@mail.com", "145455", "LUL", "OMEGA", new Date(122345234));
        //manager.addUser(user4);
    }

    @Test
    public void deleteUserTest(){
        ManageUser manager = new ManageUser();
        manager.deleteUser(1L);
    }

    @Test
    public void updateUserTest(){
        ManageUser manager = new ManageUser();
        //manager.changePassword(3L, "woninfornite");
        //manager.changeName("Giansco", "Gianni");
        //manager.changeSurname("Giansco", "LeagueofLegends");
        //manager.changeDateOfBirth("Dwape", new Date(122));
    }

    @Test
    public void verifyTest(){
        User user = ManageUser.verifyUser("Giansco", "352312");
        assert(user != null);
        /*User user2 = ManageUser.verifyUser("Dwape", "34434");
        assert(user2 == null);
        User user3 = ManageUser.verifyUser("Giannini", "5454");
        assert(user3 == null);*/
    }
}
