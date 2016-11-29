/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package installer;

import java.io.File;
import java.io.FileWriter;
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
      
          try {
            if (result) {
                saveEnv(DB_DATABASE, DB_USERNAME, DB_PASSWORD, DB_HOST, DB_TABLEPREFIX);
            }
        } catch (Exception e) {
            System.out.println("file can not writ");
        }
        
        return result;
    }
    
    //===========================save information on file ================================
    private static boolean saveEnv(String DB_DATABASE, String DB_USERNAME, String DB_PASSWORD, String DB_HOST, String DB_TABLEPREFIX) {
        boolean result = false;

        try {
            File configFileEnv = new File("src/.env");
            FileWriter fw = new FileWriter(configFileEnv);
            fw.write("DB_CONNECTION=mysql");
            fw.write("\r\nDB_HOST=" + DB_HOST);
            fw.write("\r\nDB_DATABASE=" + DB_DATABASE);
            fw.write("\r\nDB_TABLEPREFIX=" + DB_TABLEPREFIX);
            fw.write("\r\nDB_USERNAME=" + DB_USERNAME);
            fw.write("\r\nDB_PASSWORD=" + DB_PASSWORD);
            fw.close();
            result = true;

        } catch (Exception e) {
            System.out.println("Please check your file permition.");
        }
        return result;
    }
    
}
