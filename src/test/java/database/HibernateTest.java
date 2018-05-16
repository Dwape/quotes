package database;

import hibernate.ManageBook;
import hibernate.ManagePost;
import hibernate.ManageUser;
import hibernate.ManageVote;
import model.Book;
import model.Post;
import model.User;
import model.Vote;
import org.junit.Test;

import java.sql.Date;


public class HibernateTest {

    @Test
    public void addUserTest(){
        //manager.addUser("Giansco", "gianluca.scolaro@ing.austral.edu.ar", "352312", "Gianluca", "Scolaro", new Date(1232332));
        //manager.addUser("Dwape", "eduardo.lalor@ing.austral.edu.ar", "1234323", "Eduardo", "Lalor", new Date(123233221));
        User user1 = new User("Gengu", "noway@mail.com", "afdafa", "Jeff", "Fromtheoverwatchteam", "1967-8-2");
        User user2 = new User("Giansco", "gianluca.scolaro@ing.austral.edu.ar", "pass", "Gianluca", "Scolaro", "1967-8-2");
        User user3 = new User("Dwape", "eduardo.lalor@ing.austral.edu.ar", "123", "Eduardo", "Lalor", "1967-8-2");
        ManageUser.addUser(user1);
        ManageUser.addUser(user2);
        ManageUser.addUser(user3);
        //User user4 = new User("Dope", "mymail@mail.com", "145455", "LUL", "OMEGA", new Date(122345234));
        //manager.addUser(user4);
    }

    @Test
    public void deleteUserTest(){
    }

    @Test
    public void updateUserTest(){
        //manager.changePassword(3L, "woninfornite");
        //manager.changeName("Giansco", "Gianni");
        //manager.changeSurname("Giansco", "LeagueofLegends");
        ManageUser.changeDateOfBirth("Giansco","1967-2-4");//YYYY-MM-DD
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

    @Test
    public void addVoteToPostTest(){
        Book book = new Book("1","Hobbit","JRR Tolkien");
        ManageBook.addBook(book);
        User user = new User("Giansco", "gianluca.scolaro@ing.austral.edu.ar", "pass", "Gianluca", "Scolaro", "1967-8-2");
        ManageUser.addUser(user);
        Post post = new Post("Hobbits are evil", new Date(2018,5,9),"Really? why?",book,user);
        ManagePost.addPost(post);

        Vote vote = new Vote(post,null,user,true);
        ManageVote.addVoteToPost(vote);
    }
}
