/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package installer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author jubayed
 */
public class config {

    public static boolean saveDatabaseInfo(String DB_DATABASE, String DB_USERNAME, String DB_PASSWORD, String DB_HOST, String DB_TABLEPREFIX) {
        boolean result = false;

        System.out.println(DB_DATABASE + DB_USERNAME + DB_PASSWORD+DB_HOST);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":3306/" + DB_DATABASE, DB_USERNAME, DB_PASSWORD);
            //here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();
            result = true;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("conntion status= faile");
        }
        System.out.println("conntion status= " + result);

        try {
            if (result) {
                System.out.println("sava ok");
                saveEnv(DB_DATABASE, DB_USERNAME, DB_PASSWORD, DB_HOST, DB_TABLEPREFIX);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public static boolean saveAdmin(String name, String email, String pass) {
        boolean result = false;
        Scanner sc = null;
        String[] recFormfile = new String[50];
        int i = 0;
        try {
            sc = new Scanner(new File("src/.env"));

            while (sc.hasNextLine()) {
                recFormfile[i] = sc.nextLine();
                i++;
            }
            sc.close();
            //add admin infomation on recformfile array
            recFormfile[7] = "\r\nADMIN_USERNAME=" + name;
            recFormfile[8] = "\r\nADMIN_EMAIL=" + email;
            recFormfile[9] = "\r\nADMIN_PASSWORD=" + pass;

        } catch (FileNotFoundException ex) {
            System.out.println("file not foud");
        }

        //============write file .evn=============
        try {
            File configFileEnvOv = new File("src/.env");
            FileWriter fow = new FileWriter(configFileEnvOv);
            for (int k = 0; k < 9; k++) {
                fow.write(recFormfile[i]);
            }
            System.out.println(recFormfile[i]);

            fow.close();
        } catch (Exception e) {
            System.out.println("writeEvnfull problem");
            result = false;
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
