package javacharfolder;
import java.util.Random;
public class character {
    private final String name;
    private int level;
    private int health;
    private final String characterclass;
    private double multiplier; // Changed to double
    private final moveset skill;
    private final moveset Ultimate;
    private boolean unlocked;
    private final String sprite;
    

    public character(String name, int level, int health, String characterclass, moveset skill, moveset Ultimate, boolean unlocked , String sprite) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.characterclass = characterclass;
        this.multiplier = multiplier == 0.0 ? getmultiplier() : multiplier;
        this.skill = skill;
        this.Ultimate = Ultimate;
        this.unlocked = unlocked;
        this.sprite = sprite;
    }

    public String getname() {
        return name;
    }

    public int getlevel() {
        return level;
    }

    public int gethealth() {
        return health;
    }

    public void sethealth(int health) {
        this.health = Math.max(0, health);
    }

    public String getclass() {
        return characterclass;
    }

    public double getmultiplier() { 
        Random rand = new Random();

        return switch (characterclass) {
            case "Trickster" -> Math.floor(rand.nextInt(5) + rand.nextDouble());
            case "Warrior" -> 2.0;
            case "Mage" -> 3.0;
            case "Vanguard" -> 1.0;
            case "Beast" -> 2.5;
            default -> 0.0;
        };
    }

    public  moveset getskill() {
        return skill;
    }

    public moveset getUlt() {
        return Ultimate;
    }

    public String getsprite(){
        return sprite;
    }

    public void levelup() {
        if(level < 100){
            level+=1;
            health += 10;
        }
        else{
            System.out.println("max level is 99");
        }
    }

    public boolean getunlockedstatus(){
        return unlocked;
    }

    public void unlockcharacter(){
        unlocked = true;
        
    }
}
