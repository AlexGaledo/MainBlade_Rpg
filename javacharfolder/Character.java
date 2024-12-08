package javacharfolder;

import java.util.Random;

public class Character {
    private String name;
    private int level;
    private int health;
    private String characterclass;
    private double multiplier; // Changed to double
    private Moveset skill;
    private Moveset Ultimate;

    public Character(String name, int level, int health, String characterclass, Moveset skill, Moveset Ultimate) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.characterclass = characterclass;
        this.multiplier = getmultiplier(characterclass); // multiplier calculated here
        this.skill = skill;
        this.Ultimate = Ultimate;
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

    public Moveset getskill() {
        return skill;
    }

    public Moveset getUlt() {
        return Ultimate;
    }

    public void levelup() {
        level++;
        health += 10;
        multiplier += 0.1;
    }
}
