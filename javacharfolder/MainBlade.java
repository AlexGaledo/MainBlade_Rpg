package javacharfolder;


import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class MainBlade {
    public static HashMap<String,user> registered_users = new HashMap<String,user>();
    public static user current_user = null;
    public static character bench[] = new character[5];
    public static characterpool heroes = new characterpool();
    public static enemypool enemies = new enemypool();

    public static ImageIcon logo = new ImageIcon("javacharfolder/assets/Mainblade.png");
    public static ImageIcon bg = new ImageIcon("javacharfolder/assets/MainBladePage.png");
    public static ImageIcon loginicon = new ImageIcon("javacharfolder/assets/Albert.png");
    
    public static void main(String[] args) {
        retrieve();
        login();
    }
    //set current user sa run na toh
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
                initializelevels();
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
    //return boolean class
    public static boolean accexist(String username){
        return registered_users.containsKey(username);
    }
    //return user class
    public static user getuser(String username){
        return registered_users.get(username);
    }
    //self explanatory
    public static void create_newaccount(String username, String password){
        boolean[] tempowned = new boolean[10];
        Arrays.fill(tempowned,false);
        //free 5 characters agad
        for(int i = 0;i < 5;i++){
            tempowned[i] = true;
        }
        String initialLevels = "00000000000000000000";
        user newuser = new user(username,password,tempowned,initialLevels,1);
        registered_users.put(username,newuser);
        current_user = newuser;
        System.out.println("Successfully created account welcome to MainBlade!" + current_user.getusername());
        
    }
    //para sa new users at retrieve
    public static void add_users(String username,user info){
        registered_users.put(username,info);
    }
    //para sa login
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
    //login logic 
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
    //save 4lines sa txt file den
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
                String leveltext = element.getlevels();
                writer.write(leveltext +"\n");
                writer.write(Integer.toString(element.getcoins())+"\n");
            }

            writer.close();
        } catch (IOException e) {
                System.out.println("Error occured");
                e.printStackTrace();
            }
    }
    //retrieve 4lines sa txt file
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
                        if(ownedchars[i] = identifier.charAt(i) == '1'){
                            heroes.characterpool[i].unlockcharacter();
                        }
                    }
                    String level = filereader.readLine();
                    System.out.println("debug tool: " + level);
                    String coin = filereader.readLine();
                    int coins = Integer.parseInt(coin); 
                    user tempuser = new user(username, password, ownedchars,level,coins);
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
    //debugtools kung right on track paba ginagawa naten ( sa console nakikita)
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
            System.out.println("DEBUG: characterpool length = " + heroes.characterpool.length);
        }
        System.out.println(current_user.getlevels());
        System.out.println(current_user.getusername());
        System.out.println(current_user.getpassword());
        for(character elements:heroes.characterpool){
        System.out.println("hero name: "+ elements.getname());
        System.out.println("hero multiplier: "+ elements.getmultiplier());
        System.out.println("hero health: "+ elements.gethealth());
        System.out.println("hero level: "+ elements.getlevel());   
        }
        
        for(boolean owned:current_user.getownedinfo()){
            System.out.println(owned + "\n");
        }
        try {
          benchdebug();  
        } catch (Exception e) {
            System.out.println("team not yet full max is 5");
        }
        
    }
    // set level ng character galing sa save file sa txt 
    public static void initializelevels(){
        int[] levelarray = current_user.getLevelsAsIntegers();
        for(int index = 0; index < levelarray.length; index++){
            for(int i = 1; i < levelarray[index] ; i++){
                heroes.characterpool[index].levelup();
            }
        }
    }
    
    // pat paayos ng itsura neto whahaha
    public static void menu(){
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
                    int herolevel = heroes.characterpool[index].getlevel();
                    
                   
                    String spritePath = heroes.characterpool[index].getsprite();
                    File spriteFile = new File(spritePath);
                    
                    

                    if (spriteFile.exists()) {
                        ImageIcon herosprite = new ImageIcon(spritePath);
                        
                        heroDisplay.setIcon(herosprite); 
                        heroDisplay.setText(
                            "<html>" +
                            heroname + "<br>" + 
                            " | Skill: " + heroskill.getmovename() + "<br>" + 
                            " | Ult: " + heroult.getmovename() + "<br>" + 
                            " | Level: " + herolevel + 
                            "</html>"
                        );//pwede pla html
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
        JPanel modifypanel = new JPanel();
        modifypanel.setPreferredSize(new Dimension(200,50));
        modifypanel.setLayout(new FlowLayout());
        modifypanel.setBackground(Color.black);
        debugtools();
        JButton gachabutton = new JButton();
            gachabutton.setText("MainBlade Gacha");
            gachabutton.addActionListener(gb ->{
                menuframe.dispose();
                gacha();
            });
        JButton savebutton = new JButton();
            savebutton.setSize(100,50);
            savebutton.setText("SAVE PROGRESS");
            savebutton.addActionListener(sb2->{
                save();
                menuframe.dispose();
            });

        JButton benchbutton = new JButton();
            benchbutton.setSize(100,50);
            benchbutton.setText("Modify Bench");
            benchbutton.addActionListener(bb->{
                menuframe.dispose();
                benched();
            });

        JButton battlebutton = new JButton();
        battlebutton.setSize(100,50);
        battlebutton.setText("Battle");
        battlebutton.addActionListener(bb->{
            if(bench[0]!=null){
            menuframe.dispose();
            initializebattle();
            }
            else{
                JOptionPane.showMessageDialog(null, "Bench is empty","Empty Bench Warning",JOptionPane.ERROR_MESSAGE);
            }
        });
        modifypanel.add(savebutton);
        modifypanel.add(gachabutton);
        modifypanel.add(benchbutton);
        modifypanel.add(battlebutton);
        menuframe.add(userPanel, BorderLayout.WEST);
        menuframe.add(modifypanel, BorderLayout.SOUTH);
        menuframe.add(heroDisplay, BorderLayout.CENTER);
        menuframe.setVisible(true); 
    }

    public static boolean rollconfirmation(){
        int response = JOptionPane.showConfirmDialog(
            null, // Parent component
            "Do you want to roll? , Current coins amount: " + current_user.getcoins(), // Message
            "Confirm Roll", // Title
            JOptionPane.YES_NO_OPTION, // Yes/No options
            JOptionPane.QUESTION_MESSAGE // Icon type
        );
        if(response == JOptionPane.YES_OPTION){
            return true;
        }
        else{
            return false;
        }
    }
    //Gacha
    public static void gacha(){
        
        JDialog gachaframe = new JDialog();
        gachaframe.setLayout(new BorderLayout());
        gachaframe.setSize(1200, 720);
        gachaframe.getContentPane().setBackground(Color.BLACK);
        gachaframe.setTitle("MainBlade_Gacha");
        gachaframe.setIconImage(logo.getImage());
        gachaframe.setBackground(Color.BLACK);

        JPanel gachapanel = new JPanel();
        gachapanel.setLayout(new FlowLayout());
        gachapanel.setBackground(Color.BLACK);
        gachapanel.setSize(800,70);




        JButton menubutton = new JButton();
        menubutton.setText("Back to menu");
        menubutton.setSize(100,50);
        menubutton.addActionListener(mb->{
            gachaframe.dispose();
            menu();
        });
        

        Random random = new Random();
        JButton rollbutton = new JButton();
        rollbutton.setSize(100,50);
        rollbutton.setText("ROLL");
        rollbutton.addActionListener(rb ->{
            if(current_user.getcoins() <= 0){
                JOptionPane.showMessageDialog(null, "Insufficient Coins, Fight more to get coins", null, JOptionPane.ERROR_MESSAGE);
                }else{
                    if(rollconfirmation()){
                        int index = random.nextInt(heroes.characterpool.length);
                        JOptionPane.showMessageDialog(null,"You Got : " + heroes.characterpool[index].getname(),
                        "Gacha Prize",JOptionPane.INFORMATION_MESSAGE);
                            if (current_user.getownedinfo()[index]) {
                                heroes.characterpool[index].levelup();
                                System.out.println("Debugcurrentownedlength: "+current_user.getownedinfo().length);
                            } else {
                                current_user.getownedinfo()[index] = true;
                            }   
                        current_user.setcurrentlevel(heroes.getcpool());
                        debugtools();
                        current_user.deductcoin();
                    }
                }
            });
        
        String[][] data = new String[heroes.characterpool.length][4];
        for(int i = 0 ; i < heroes.characterpool.length; i++){
            data[i][0] = heroes.characterpool[i].getname();
            data[i][1] = heroes.characterpool[i].getclass();
            data[i][2] = heroes.characterpool[i].getskill().getmovename();
            data[i][3] = heroes.characterpool[i].getUlt().getmovename();
        }
        String[] columnNames = {"Character", "Class", "Skill", "Ultimate"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.white);
        table.setGridColor(Color.red);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.YELLOW);
        table.setTableHeader(header);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
    
        gachaframe.add(scrollPane,BorderLayout.CENTER);
        gachapanel.add(rollbutton);
        gachapanel.add(menubutton);
        gachaframe.add(gachapanel,BorderLayout.SOUTH);
        gachaframe.setVisible(true);
    }

   //Bench Characters
    public static int counter = 0;
    public static void benched(){
        counter = 0;
        Arrays.fill(bench,null);
        JDialog benchframe = new JDialog();
        benchframe.setLayout(new FlowLayout());
        benchframe.setSize(500,200);
        benchframe.setTitle("Select Characters you want to use: ");
        Boolean[] activearray = new Boolean[10];
        Arrays.fill(activearray, false);
        boolean[] ownedchars = current_user.getownedinfo();
        for(int index = 0; index < ownedchars.length; index++){
            String heroname = heroes.characterpool[index].getname();
            final int x = index;
            if(ownedchars[index]){
                JButton herobutton = new JButton();
                
                herobutton.setSize(50,50);
                herobutton.setText(heroname);
                herobutton.setForeground(Color.GRAY); 
                herobutton.addActionListener(hb->{
                    if(activearray[x]){
                       herobutton.setForeground(Color.GRAY);
                       activearray[x] = false;
                       counter --;
                    }
                    else{
                        activearray[x] = true;
                        herobutton.setForeground(Color.GREEN);
                        counter ++;
                    }
                });
                benchframe.add(herobutton);
            }
        }

        JButton confirmbutton = new JButton();
        confirmbutton.setText("Confirm Bench");
        confirmbutton.setSize(50,50);
        confirmbutton.addActionListener(cb->{
            System.out.println("counter: " + counter);
            if(counter > 5 || counter < 5){
                JOptionPane.showMessageDialog(benchframe, "Choose exactly 5 characters only", null, JOptionPane.WARNING_MESSAGE);
            }
            else{
                try{
                int benchindex = 0;
                for(int index = 0; index < activearray.length; index++){
                    if(activearray[index]){
                        bench[benchindex] = heroes.characterpool[index];
                        benchindex++;    
                    }
                }
                benchdebug();
            }catch(Exception e){
                System.out.println("bench not full");
            }
            
            benchframe.dispose();
            menu();
            
        }});
        benchframe.add(confirmbutton);
        benchframe.setVisible(true);   
    }
    
    public static void benchdebug(){
        for(character element:bench){
            System.out.println(element.getname());
        }
    }
    //create copies
    public static void initializebattle(){
        try{
            int[] health = new int[5];
            for(int i = 0; i < bench.length;i++){
                if(bench[i] == null){
                    health[i] = 0;
                }else{
                health[i] = bench[i].gethealth();
                }
            }
            Random random = new Random();
            enemy[] enemylineup = new enemy[5];
            //initialize enemylineup
            for(int i = 0; i < enemylineup.length; i++){
                int randomindex = random.nextInt(enemies.enemypool.length); 
                enemylineup[i] = enemies.enemypool[randomindex];
            }
            int[] enemyhealth = new int[5];
            for(int i = 0; i < enemylineup.length; i++){
                enemyhealth[i] = enemylineup[i].gethealth();
            }

            if(battlesystem.battle(bench, health, enemylineup, enemyhealth)){
                current_user.earncoins();}
        }catch(Exception e){
            System.out.println("Character not Found");
        }
        }
    } 




