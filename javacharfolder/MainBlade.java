package javacharfolder;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;


public class MainBlade {
    public static HashMap<String,user> registered_users = new HashMap<String,user>();
    public static user current_user = null;
    public static character bench[] = new character[5];
    public static  characterpool heroes = new characterpool();

    public static ImageIcon logo = new ImageIcon("javacharfolder/assets/Mainblade.png");
    public static ImageIcon bg = new ImageIcon("javacharfolder/assets/MainBladePage.png");
    public static ImageIcon loginicon = new ImageIcon("javacharfolder/assets/Albert.png");
    
    public static void main(String[] args) {
        retrieve();
        login();
        // menu();
    }

    public static boolean setcurrentuser(String username, String password){
        if(accexist(username)){
            if(!verifyaccount(username,password)){
                    System.out.println("Invalid password");
                    JOptionPane.showMessageDialog(null, "wrong password", "wrong password",JOptionPane.WARNING_MESSAGE);
                    return false;
            }
            else{
                System.out.println("Logged in suzesfoley");
                current_user = getuser(username);
                menu();
                return true;   
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Account not found, creating new instead. ","Account not Found",JOptionPane.WARNING_MESSAGE);
            JFrame newaccframe = new JFrame();
            newaccframe.setLayout(new FlowLayout(FlowLayout.LEFT));
            newaccframe.setTitle("MainBlade_Register");
            newaccframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newaccframe.setSize(1280, 720);
            newaccframe.setIconImage(logo.getImage());
            newaccframe.getContentPane().setBackground(Color.BLACK);
            
           
            JTextField username_input = new JTextField();
            username_input.setPreferredSize(new Dimension(250,40));
            username_input.setText("Input your username here.");
                    

            JTextField password_input = new JTextField();
            password_input.setPreferredSize(new Dimension(250,40));
            password_input.setText("Input your password here."); 
        

            JButton newaccbutton = new JButton();
            newaccbutton.setText("Create Account");

            newaccbutton.addActionListener(e->{
                String newusername = username_input.getText().trim();
                String newpassword = password_input.getText().trim();
                if(accexist(newusername)){
                    JOptionPane.showMessageDialog(null, "User name is already taken, please use a different one","Username is already used." , JOptionPane.WARNING_MESSAGE);
                }else{
                    if (newusername.length() < 4) {
                        JOptionPane.showMessageDialog(newaccframe, 
                            "Username must be at least 4 characters!", 
                            "Input Error", 
                            JOptionPane.ERROR_MESSAGE);
                        username_input.requestFocus();
                    } else if (newpassword.length() < 4) {
                        JOptionPane.showMessageDialog(newaccframe, 
                            "Password must be at least 4 characters!", 
                            "Input Error", 
                            JOptionPane.ERROR_MESSAGE);
                        password_input.requestFocus();
                    } else {
                        System.out.println("Successfully created account");
                        create_newaccount(newusername, newpassword);save();
                        newaccframe.dispose();
                    }
                }
            });

            newaccframe.add(username_input);
            newaccframe.add(password_input);
            newaccframe.add(newaccbutton);
            newaccframe.pack();
            newaccframe.setVisible(true);
            return true;
        }
    }

    public static boolean accexist(String username){
        return registered_users.containsKey(username);
    }

    public static user getuser(String username){
        return registered_users.get(username);
    }

    public static void create_newaccount(String username, String password){
        boolean[] tempowned = new boolean[10];
        Arrays.fill(tempowned,false);
        tempowned[0] = true;String initialLevels = "00000000000000000000";
        user newuser = new user(username,password,tempowned,initialLevels);
        registered_users.put(username,newuser);
        current_user = newuser;
        System.out.println("Successfully created account welcome to MainBlade!" + current_user.getusername());
        
    }

    public static void add_users(String username,user info){
        registered_users.put(username,info);
    }

    public static boolean verifyaccount(String username,String password){
        if(registered_users.containsKey(username)){
            if(registered_users.get(username).getpassword().equals(password)){
                return true;
            }
            else{
                return false;
            }
        }
        return registered_users.containsKey(username);
    } 

    public static void login(){
        JFrame loginframe = new JFrame();
        loginframe.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton adminbutton = new JButton();
        adminbutton.setText("admin login");
        adminbutton.addActionListener(e3 ->{
            setcurrentuser("admin", "mainblade");
            loginframe.dispose();
        });
        loginframe.add(adminbutton);
    
    //Albert Image
        JLabel userprompt = new JLabel();
        userprompt.setText("Traveler, wouldst thou input thy soul's context?");
        userprompt.setHorizontalTextPosition(JLabel.CENTER);
        userprompt.setVerticalTextPosition(JLabel.TOP);
        userprompt.setIcon(loginicon);
        userprompt.setIconTextGap(-50);
        userprompt.setFont(new Font("OLD English Text MT", Font.BOLD, 20));
    //Text Fields and Button
        JPanel loginpanel = new JPanel();
        loginpanel.setBackground(Color.BLACK);

        JTextField username_input = new JTextField();
            username_input.setPreferredSize(new Dimension(250,40));
            username_input.setText("Input your username here.");

        JPasswordField password_input = new JPasswordField();
        password_input.setPreferredSize(new Dimension(250,40));
        password_input.setText("Input your password here."); 

        JButton loginbutton = new JButton();
        loginbutton.setSize(100, 100);
        loginbutton.setText("Login");
        loginbutton.setFont(new Font("OLD English Text MT", Font.BOLD, 20));
        loginbutton.setBackground(Color.BLACK);
        loginbutton.setForeground(Color.GRAY);
        loginbutton.setBorder(new EmptyBorder(0,0,0,0));
        loginbutton.addActionListener(e -> {
            String username = username_input.getText().trim();
            char[] passwordChars = password_input.getPassword();
            String password = new String(passwordChars).trim();
            Arrays.fill(passwordChars, '\0');
            if(setcurrentuser(username, password)){
            loginframe.dispose();
            }
        });
        
        loginpanel.add(username_input);
        loginpanel.add(password_input);
        loginpanel.add(loginbutton);
        

        loginframe.add(userprompt);
        loginframe.add(loginpanel); 

        loginframe.setTitle("MainBlade_Login");
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginframe.setSize(1280, 720);
        loginframe.setIconImage(loginicon.getImage());
        loginframe.getContentPane().setBackground(Color.BLACK);

        loginframe.setVisible(true);
    }

    public static void save(){
        try {
            String filename = "registeredusers.txt";
            File savefile = new File(filename);
            if(savefile.createNewFile()){
                System.out.println("Save File Created " + savefile.getName());  
            }
            else{
                System.out.println("File Already Exists, Overwriting...");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(savefile));
            
            for(user element:registered_users.values()){
            writer.write(element.getusername() + "\n");
            writer.write(element.getpassword() + "\n");
                for (boolean owned : element.getownedinfo()) {
                    writer.write(owned ? '1' : '0');
                }
                writer.write("\n");
            }
            String leveltext ="";
            for(int elements:current_user.getLevelsAsIntegers()){
                String elementSTR = Integer.toString(elements);
                leveltext += elementSTR;
            }
            writer.write(leveltext +"\n");
            
            writer.close();
        } catch (IOException e) {
                System.out.println("Error occured");
                e.printStackTrace();
            }
    }

    public static void retrieve(){
        try {
            String filename = "registeredusers.txt";
            BufferedReader filereader = new BufferedReader(new FileReader(filename));
            File savefile = new File(filename);
            if(savefile.exists()){
                String line;
                while((line = filereader.readLine()) != null){
                    String username = line;
                    String password = filereader.readLine();
                    String identifier = filereader.readLine();
                    boolean[] ownedchars = new boolean[identifier.length()];
                    for(int i = 0 ; i < identifier.length() ; i++) {
                        ownedchars[i] = identifier.charAt(i) == '1';
                    }
                    String level = filereader.readLine();
                    System.out.println("debug tool: " + level);
                    user tempuser = new user(username, password, ownedchars,level);
                    add_users(username, tempuser);
                }
                filereader.close();
            }
            else{
                System.out.println("file does not exist");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while retrieving data");
            e.printStackTrace();
        }
    }

    public static void debugtools(){
        for (String username : registered_users.keySet()) {
            user users = registered_users.get(username);
    
            System.out.println("Username: " + username);
            System.out.println("Password: " + users.getpassword());
            System.out.print("Owned Characters: ");
            for (boolean owned : users.getownedinfo()) {
                System.out.print(owned ? '1' : '0');
            }
            System.out.println("\n---");

        }
        System.out.println(current_user.getlevels());
        System.out.println(current_user.getusername());
        System.out.println(current_user.getpassword());
        System.out.println("hero name: "+ heroes.characterpool[0].getname());
        System.out.println("hero multiplier: "+ heroes.characterpool[0].getmultiplier());
        System.out.println("hero health: "+ heroes.characterpool[0].gethealth());
        System.out.println("---------------------------------------------------------");
        System.out.println("hero name: "+ heroes.characterpool[1].getname());
        System.out.println("hero multiplier: "+ heroes.characterpool[1].getmultiplier());
        System.out.println("hero health: "+ heroes.characterpool[1].gethealth());
        System.out.println("---------------------------------------------------------");
        System.out.println("hero name: "+ heroes.characterpool[2].getname());
        System.out.println("hero multiplier: "+ heroes.characterpool[2].getmultiplier());
        System.out.println("hero health: "+ heroes.characterpool[2].gethealth());
        System.out.println("---------------------------------------------------------");

    
        
        for(boolean owned:current_user.getownedinfo()){
            System.out.println(owned + "\n");
        }
    }

    public static void setlevels(){
        int[] levelarray = current_user.getLevelsAsIntegers();
            for(int i = 0; i < levelarray.length; i++){
                for(int j = 0; j < levelarray[i] ; j++){
                    heroes.characterpool[i].levelup();
                    System.out.println(heroes.characterpool[i].getname() + " LEVEL IS  : " + heroes.characterpool[i].getlevel());
                }
            }
    }
    // pat paayos ng itsura neto whahaha
    public static void menu() {
        JFrame menuframe = new JFrame();
        menuframe.setLayout(new BorderLayout());
        menuframe.setSize(1280, 720);
        menuframe.getContentPane().setBackground(Color.BLACK);
        menuframe.setTitle("MainBlade_Menu");
        menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuframe.setIconImage(logo.getImage());
        debugtools();
    
        JPanel userPanel = new JPanel();
        userPanel.setPreferredSize(new Dimension(300, 720));
        userPanel.setBackground(Color.DARK_GRAY);
    
        
        JLabel heroDisplay = new JLabel();
        heroDisplay.setHorizontalAlignment(JLabel.CENTER);
        heroDisplay.setVerticalAlignment(JLabel.CENTER);
    
       
        boolean[] ownedchars = current_user.getownedinfo();
       
    
        for (int i = 0; i < ownedchars.length; i++) {
            final int index = i;
            if (ownedchars[i]) {
                JButton ownedbutton = new JButton(heroes.characterpool[i].getname());
                ownedbutton.setForeground(Color.GRAY);
    
                
                ownedbutton.addActionListener(e -> {

                    System.out.println("Selected: " + heroes.characterpool[index].getname());
                    String heroname = heroes.characterpool[index].getname();
                    moveset heroskill = heroes.characterpool[index].getskill();
                    moveset heroult = heroes.characterpool[index].getUlt();
                    
                   
                    String spritePath = heroes.characterpool[index].getsprite();
                    File spriteFile = new File(spritePath);
                    
                    

                    if (spriteFile.exists()) {
                        ImageIcon herosprite = new ImageIcon(spritePath);
                        
                        heroDisplay.setIcon(herosprite); 
                        heroDisplay.setText(heroname + " |Skill: " + heroskill.getmovename() + " |Ult: " + heroult.getmovename());
                        heroDisplay.setFont(new Font("OLD English Text MT", Font.BOLD, 20));
                        heroDisplay.setForeground(Color.red);
                        System.out.println(heroes.characterpool[index].getname() + " : " + heroes.characterpool[index].getlevel());
                    } else {
                        System.out.println("Image not found: " + spritePath);
                        JOptionPane.showMessageDialog(menuframe, 
                            "Image not found: " + spritePath, 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    menuframe.revalidate();
                    menuframe.repaint(); 
                });
                userPanel.add(ownedbutton);
            }
            
            
        }
        setlevels();
        debugtools();
        menuframe.add(userPanel, BorderLayout.WEST);
        menuframe.add(heroDisplay, BorderLayout.CENTER);
       
        menuframe.setVisible(true); 
    }

    //Gacha
    

    //Battle logic:  
    //Duplicate copies of characters 
    //Turn points mechanics before a button would be active

    // public static void battle(Character player, Character Enemy){
        // moveset heroskill = heroes.characterpool[index].getskill();
        // moveset heroult = heroes.characterpool[index].getUlt();

        // JDialog movebox = new JDialog();
        // movebox.setLayout(new FlowLayout());
        // movebox.setSize(500,200);
        // movebox.setTitle("Choose Move and target");
        // //Gagamitin sa Fight logic
        // JButton skillButton = new JButton();
        // skillButton.setText(heroskill.getmovename());
        // skillButton.setPreferredSize(new Dimension(100,100));
        // skillButton.addActionListener(sb->{
        //     System.out.println(heroskill.getmovename() + " was used");
        // });
        // movebox.add(skillButton);

        // JButton ultButton = new JButton();
        // ultButton.setText(heroult.getmovename());
        // ultButton.setPreferredSize(new Dimension(100,100));
        // ultButton.addActionListener(ub->{
        //     System.out.println(heroult.getmovename() + " was used");
        // });
        // movebox.add(ultButton);

        // movebox.setVisible(true);
    // }
}
