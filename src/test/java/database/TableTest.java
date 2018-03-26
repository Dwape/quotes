package database;
import org.junit.Test;

import java.sql.Statement;
import java.sql.Connection;

/**
 * Created by Dwape on 3/26/18.
 */
public class TableTest {

    @Test
    public void tableTest(){
        Connection con = ConnectDatabase.connect();
        try{
            Statement stmt = con.createStatement();
            //stmt.executeUpdate("CREATE TABLE user ( username VARCHAR(16) NOT NULL, email VARCHAR(50) NOT NULL, password VARCHAR(20) NOT NULL, PRIMARY KEY (username)); ");
            //stmt.executeUpdate("INSERT INTO user VALUES ('Dwape','eduardo.lalor@ing.austral.edu.ar', '1234')");
            //stmt.executeUpdate("INSERT INTO user VALUES ('Giansco','gianluca.scolaro@ing.austral.edu.ar', '352312')");
            //stmt.executeUpdate("INSERT INTO user VALUES ('Gengu','gengu_main@gmail.com', 'ilovegengu')");
            con.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Table created successfully");
    }
}
