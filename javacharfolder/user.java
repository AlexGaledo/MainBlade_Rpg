package javacharfolder;

public class user {
    private String username;
    private String password;
    private boolean[] owned_characters = new boolean[10];

    public user(String name, String password, boolean[] owned_characters){
        this.username = name;
        this.password = password;
        this.owned_characters = owned_characters;
    }

    public String getusername(){
        return username;
    }

    public String getpassword(){
        return password;
    }

    public boolean[] getownedinfo(){
        return owned_characters;
    }
}