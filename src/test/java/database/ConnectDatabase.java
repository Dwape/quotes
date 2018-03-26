package database;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Dwape on 3/26/18.
 */
public class ConnectDatabase {

    public static Connection connect(){
        Connection con = null;

        try {
            //Registering the HSQLDB JDBC driver
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            //Creating the connection with HSQLDB
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", ""); // jdbc:hsqldb:mem:.
            if (con!= null){
                System.out.println("Connection created successfully");

            }else{
                System.out.println("Problem with creating connection");
            }

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return con;
    }
}
