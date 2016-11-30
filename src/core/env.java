/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author jubayed
 */
public class env {

    public static void main(String[] arg) {
        boolean re= checkadminLogin();
        System.out.println(re);
    }

    public static boolean checkadminLogin() {
        boolean result = false;
            try {
            Scanner sc = new Scanner(new File(".login"));
            String texta ="";
            while (sc.hasNextLine()) {
                 texta=sc.nextLine();
                 System.out.println(texta);
                if(texta.equals("admin")){
                   result =true;
                }
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return result;
    }

    public static boolean adminLogin(String email, String password, boolean remmenber) {
      //====
      email="ADMIN_EMAIL="+email;
      password="ADMIN_PASSWORD="+password;  
      boolean a=false,b=false;
      
        boolean result = false;
        try {
            Scanner sc = new Scanner(new File("src/.env"));
            String texta ="";
            while (sc.hasNextLine()) {
                 texta=sc.nextLine();
                if(email.equals(texta)){
                    a=true;
                }else if(password.equals(texta)){
                    b=true;
                }
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
        if(a&&b){
            result = setAdminLogin();
            
            if(result){
                result=checkadminLogin();
            }
        }

        //============write file .evn=============
        
        return result;
    }

    private static boolean setAdminLogin() {
        boolean result = false;
        try {
            File configFileEnv = new File(".login");
            FileWriter fw = new FileWriter(configFileEnv);
            fw.write("admin");
            fw.close();
            result = true;

        } catch (Exception e) {
            System.out.println("Please check your file permition.");
        }
        return result;
    }

}
