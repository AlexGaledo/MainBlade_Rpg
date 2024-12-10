package javacharfolder;

import java.util.Random;

public class character {
    private String name;
    private int level;
    private int health;
    private String characterclass;
    private double multiplier; // Changed to double
    private moveset skill;
    private moveset Ultimate;
    private boolean unlocked;
    private String sprite;
    

    public character(String name, int level, int health, String characterclass, moveset skill, moveset Ultimate, boolean unlocked , String sprite) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.characterclass = characterclass;
        this.multiplier = getmultiplier(characterclass);
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

    public double getmultiplier(String charClass) { 
        Random rand = new Random();

        return switch (charClass) {
            case "Tricker" -> rand.nextInt(5) + rand.nextDouble();
            case "Warrior" -> 2.0;
            case "Mage" -> 3.0;
            case "Vanguard" -> 1.0;
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
        level++;
        health += 10;
        multiplier += 0.1;
    }
}
