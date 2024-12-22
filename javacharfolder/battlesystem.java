package javacharfolder;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class battlesystem {

    public static boolean  battle(character[] character_lineup, int[] character_healths, enemy[] enemy_lineup, int[] enemy_healths) {
        JFrame battleFrame = new JFrame("MainBlade_Battle");
        battleFrame.setLayout(new FlowLayout());
        battleFrame.setSize(1280, 720);
        battleFrame.getContentPane().setBackground(Color.BLACK);
        battleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Random random = new Random();

        System.out.println("Battle Start!");

        // Battle loop: continues until one team is defeated
        while (isAlive(character_healths) && isAlive(enemy_healths)) {
            // ----- Player Turn -----
            for (int i = 0; i < character_lineup.length; i++) {
                if (!isAlive(enemy_healths)) {
                    System.out.println("All enemies have been defeated! You win!");
                    JOptionPane.showMessageDialog(null, "All enemies have been defeated! You win!");
                    MainBlade.menu();
                    return true;
                }
                checkhealth(character_healths, character_lineup);
                if (character_healths[i] > 0) {
                    System.out.println(character_lineup[i].getname() + "'s turn.");
                    moveset heroskill = character_lineup[i].getskill();
                    moveset heroult = character_lineup[i].getUlt();
                    int teamhealth = 0;
                    for(int health:character_healths){
                        teamhealth+=health;
                    }
               // condition < 3000 total team health before can use ult
                    String[] moveOptions;

                    if (teamhealth < 3000) {
                        moveOptions = new String[]{heroskill.getmovename(), heroult.getmovename()};
                    } else {
                        
                        moveOptions = new String[]{heroskill.getmovename()};
                    }
                    ImageIcon icon = new ImageIcon(character_lineup[i].getsprite());
                    int action = JOptionPane.showOptionDialog(
                            null,
                            "Choose an action for " + character_lineup[i].getname(),
                            "Player Turn",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            icon,
                            moveOptions,
                            moveOptions[0]
                    );
                    int targetIndex = chooseTarget(enemy_lineup, enemy_healths, "Choose an enemy to attack:");
                    if (targetIndex != -1) {
                        if (action == 0) {
                            System.out.println(character_lineup[i].getname() + " used " + heroskill.getmovename());
                            enemy_healths[targetIndex] -= (heroskill.getrawdamage() * character_lineup[i].getmultiplier());
                        } else if (action == 1) {
                            System.out.println(character_lineup[i].getname() + " used " + heroult.getmovename());
                            enemy_healths[targetIndex] -= (heroult.getrawdamage() * character_lineup[i].getmultiplier());
                        }

                        if (enemy_healths[targetIndex] <= 0) {
                            System.out.println(enemy_lineup[targetIndex].getname() + " has been defeated!");
                            enemy_healths[targetIndex] = 0;
                        }
                    }
                }
            }

            // Check if enemies are all defeated
            if (!isAlive(enemy_healths)) {
                System.out.println("All enemies have been defeated! You win!");
                JOptionPane.showMessageDialog(null, "All enemies have been defeated! You win!");
                MainBlade.menu();
                return true;
            }

            // ----- Enemy Turn -----
            for (int i = 0; i < enemy_lineup.length; i++) {
                if (!isAlive(character_healths)) {
                    System.out.println("All characters have been defeated! Enemies win!");
                    JOptionPane.showMessageDialog(null, "All characters have been defeated! Enemies win!");
                    MainBlade.menu();
                    return false;
                }
                if (enemy_healths[i] > 0) {
                    int targetIndex = findRandomTarget(character_healths);
                    if (targetIndex != -1) {
                        int healthsum = 0;
                        for(int health:enemy_healths){
                            healthsum+=health;
                        }
                        if(healthsum > 2000){
                        System.out.println(enemy_lineup[i].getname() + " used "+ enemy_lineup[i].getskill().getmovename() + " on " + character_lineup[targetIndex].getname() );
                        character_healths[targetIndex] -= enemy_lineup[i].getskill().getrawdamage();
                        }else{
                            int move = random.nextInt(2);
                            switch (move){
                                case 1:System.out.println(enemy_lineup[i].getname() + " used "+ enemy_lineup[i].getskill().getmovename() + " on " + character_lineup[targetIndex].getname() );
                                character_healths[targetIndex] -= enemy_lineup[i].getskill().getrawdamage();
                                break;
                                case 0:System.out.println(enemy_lineup[i].getname() + " used "+ enemy_lineup[i].getult().getmovename() + " on " + character_lineup[targetIndex].getname() );
                                character_healths[targetIndex] -= enemy_lineup[i].getult().getrawdamage();
                            }
                        }
                        if (character_healths[targetIndex] <= 0) {
                            System.out.println(character_lineup[targetIndex].getname() + " has been defeated!");
                            character_healths[targetIndex] = 0; 
                        }
                    }
                }
            }
            if (!isAlive(character_healths)) {
                System.out.println("All characters have been defeated! Enemies win!");
                JOptionPane.showMessageDialog(null, "All characters have been defeated! Enemies win!");
                MainBlade.menu();
                return false;
            }
        }
        System.out.println("Battle Over!");
        battleFrame.setVisible(true);
        return true;
    }

    // choose an enemy target using a dialog
    private static int chooseTarget(enemy[] enemy_lineup, int[] enemy_healths, String message) {
        String[] enemyNames = new String[enemy_lineup.length];
        for (int i = 0; i < enemy_lineup.length; i++) {
            if (enemy_healths[i] > 0) {
                enemyNames[i] = enemy_lineup[i].getname() + " (HP: " + enemy_healths[i] + ")";
            } else {
                enemyNames[i] = enemy_lineup[i].getname() + " (Defeated)";
            }
        }

        return JOptionPane.showOptionDialog(null, message, "Choose Target",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, enemyNames, enemyNames[0]);
    }

    // find a random alive target
    private static int findRandomTarget(int[] healths) {
        Random random = new Random();
        while (true) {
            int index = random.nextInt(healths.length);
            if (healths[index] > 0) {
                return index;
            }
        }
    }

    // check if team is alive
    private static boolean isAlive(int[] healths) {
        for (int health : healths) {
            if (health > 0) {
                return true;
            }
        }
        return false;
    }

    // debug team health in console
    public static void checkhealth(int[] character_healths, character[] lineup){
        System.out.println("------------------------------");
        for(int i = 0; i < character_healths.length; i++){
            System.out.println(lineup[i].getname() + " : " + character_healths[i]);
        }
        System.out.println("------------------------------");
    }
}
