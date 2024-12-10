package javacharfolder;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.FocusEvent;



public class MainBlade {
    public static HashMap<String,user> registered_users = new HashMap<String,user>();
    public static user current_user = null;

    public static ImageIcon logo = new ImageIcon("javacharfolder/assets/Mainblade.png");
    public static ImageIcon bg = new ImageIcon("javacharfolder/assets/MainBladePage.png");
    
    

    public static void setcurrentuser(String username, String password){
        if(accexist(username)){
            if(verifyaccount(username,password)){
                    System.out.println("Logged in suzesfoley");    

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
                    create_newaccount(newusername, newpassword);
                    newaccframe.dispose();  
                }
            });

        
            newaccframe.add(username_input);
            newaccframe.add(password_input);
            newaccframe.add(newaccbutton);
            newaccframe.pack();
            newaccframe.setVisible(true);
        }
    }

    public static boolean accexist(String username){
        return registered_users.containsKey(username);
    }

    public static void create_newaccount(String username, String password){
        boolean[] tempowned = new boolean[10];
        Arrays.fill(tempowned,false);
        tempowned[0] = true;
        user newuser = new user(username,password,tempowned);
        registered_users.put(username,newuser);
    }

    public static boolean verifyaccount(String username,String password){
        if(registered_users.containsKey(username)){
            if(registered_users.get(username).getpassword().equals(password)){
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid password", "Wrong password", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return registered_users.containsKey(username);
    }
    
  

    public static void main(String[] args) {
        login();
    }

    public static void login(){
        JFrame loginframe = new JFrame();
        loginframe.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    //Albert Image
        ImageIcon loginicon = new ImageIcon("javacharfolder/assets/loginblade.png");
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

        JTextField password_input = new JTextField();
        password_input.setPreferredSize(new Dimension(250,40));
        password_input.setText("Input your password here."); 

        JButton loginbutton = new JButton();
        loginbutton.setSize(100, 100);
        loginbutton.setText("Login");
        loginbutton.setFont(new Font("OLD English Text MT", Font.BOLD, 20));
        loginbutton.setBackground(Color.BLACK);
        loginbutton.setForeground(Color.GRAY);
        loginbutton.setBorder(new EmptyBorder(0,0,0,0));
        loginbutton.addActionListener(e->{
            loginframe.dispose();
            setcurrentuser("", "");
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


    // public static void battle(Character player, Character Enemy){

    // }
}
