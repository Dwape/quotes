package database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by Dwape on 3/26/18.
 */
public class QueryTest {

    @Test
    public void queryTest(){
        Connection con = ConnectDatabase.connect();
        try{
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT username, email FROM user");

            while(result.next()){
                System.out.println(result.getString("username")+" | "+
                        result.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Table created successfully");
    }
}
