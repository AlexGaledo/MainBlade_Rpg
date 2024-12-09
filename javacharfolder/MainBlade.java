package javacharfolder;

import java.util.ArrayList;

public class MainBlade {
    public static ArrayList<String> registered_users = new ArrayList<String>();
    public static void addaccount(String username, String password){
        if(findaccount(username)){
            System.out.println("Account User Found");
            if(security(password)){
                System.out.println("Successfully Logged in");

            }
        }
        else{
            //error message placeholder
            return;
        }
    }

    public static boolean findaccount(String username){

    }
    
    public static boolean security(String password){

    }

    public static void debug() {
        registered_users.add("ALEX");
        registered_users.add("ADRIAN");
        characterpool charlist = new characterpool();
        System.out.println(charlist.characterpool[0].getname());
        System.out.println(charlist.characterpool[0].getlevel());
        for(var x = 0; x < 100 ; x++){charlist.characterpool[0].levelup();}
        System.out.println(charlist.characterpool[0].getlevel());
        for(String element:registered_users){
            System.out.println(element);
        }

    }

    public static void login(String name, String password){

    }
    public static void battle(Character player, Character Enemy){

    }
}
