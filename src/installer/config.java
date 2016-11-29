/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package installer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author jubayed
 */
public class config {
    
    static boolean saveDatabaseInfo(String DB_DATABASE, String DB_USERNAME, String DB_PASSWORD, String DB_HOST, String DB_TABLEPREFIX) {
        boolean result = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":3306/" + DB_DATABASE, DB_USERNAME, DB_PASSWORD);
            //here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();
            result = true;
            con.close();
            con=null;
            stmt=null;
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("conntion status= " + result);
      
        return result;
    }
    
    //===========================================================
}
