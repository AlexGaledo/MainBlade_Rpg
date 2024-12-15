package javacharfolder;
public class user {
    private final String username;
    private final String password;
    private boolean[] owned_characters = new boolean[10];
    private String levels;

    public user(String name, String password, boolean[] owned_characters,String levels){
        this.username = name;
        this.password = password;
        this.owned_characters = owned_characters;
        this.levels = levels;
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

    public String getlevels(){
        return levels;
    }
    public int[] getLevelsAsIntegers() {
        int[] levelArray = new int[levels.length() / 2];
        for (int i = 0; i < levels.length(); i += 2) {
            String levelStr = levels.substring(i, i + 2); 
            levelArray[i / 2] = Integer.parseInt(levelStr); 
        }
        return levelArray;
    }

    public void setcurrentlevel(character[] characterpool){
        StringBuilder levelstr = new StringBuilder();
        for(character elements:characterpool){
            int level = elements.getlevel();
            String levelstring = String.format("%02d", level);
            levelstr.append(levelstring);
        }
        System.out.println("Debug: "+ levelstr.toString());
        levels = levelstr.toString();
        System.out.println("setcurrentleveldebug = " + levels);
    }
    


}