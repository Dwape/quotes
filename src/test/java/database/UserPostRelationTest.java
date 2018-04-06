package database;

import hibernate.ManagePost;
import hibernate.ManageUser;
import model.Post;
import model.User;
import org.junit.Test;

import java.sql.Date;

public class UserPostRelationTest {

    @Test public void evaluateRelation() {
        User user = new User("Giansco","email@email","pass123","Gianluca","Scolaro","22-8-1967");

        //managerPost.addPost(new Post("To be, or not to be",new Date(2132312),"description",))
        Post post1 = new Post();
        post1.setDescription("Asdasdasd");
        Post post2 = new Post();
        post2.setDescription("HEllo mas gasd");

        user.getPostArray().add(post1);
        user.getPostArray().add(post2);

        ManageUser managerUser = new ManageUser();
        ManagePost managerPost = new ManagePost();
        managerPost.addPost(post1);
        managerPost.addPost(post2);
        managerUser.addUser(user);

    }
}
