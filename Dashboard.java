package choresmania;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Dashboard 
{
 private final JTextField chore1 = new JTextField();
 private final JTextField chore2 = new JTextField();
 private final JTextField chore3 = new JTextField();
 private final JTextField chore4 = new JTextField();
 JTextArea grocerylist = new JTextArea();
 JFrame window4 = new JFrame("Dashboard");
     public Dashboard()
    {

        String fileName = "user_information.txt";
        String name = null;
        String securityQuestion = null;
        String securityAnswer = null;
        String savedUsername = null;
        String passwordLine = null;
        String email = null;
        String groceryList = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Scanner sc = new Scanner(new File(fileName));
            //instantiating the StringBuffer class
            StringBuffer buffer = new StringBuffer();
            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            //closing the Scanner object
            sc.close();
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    name = line.substring(6);
                    savedUsername = reader.readLine();
                    savedUsername = savedUsername.substring(10);
                    // Read the next line for password
                    passwordLine = reader.readLine();
                    passwordLine = passwordLine.substring(10);
                    String loginCheck = reader.readLine();
                    email = reader.readLine();
                    email = email.substring(7);
                    securityQuestion = reader.readLine();
                    securityAnswer = reader.readLine();
                    String choresList = reader.readLine();
                    choresList = choresList.substring(9+savedUsername.length());
                    String grocery = reader.readLine();
                    groceryList = grocery.substring(15+savedUsername.length());
                    if(loginCheck.endsWith("true")){
                        break;
                    }
                    }
                }
            } catch (IOException e) {
            e.printStackTrace();
            }
      
      //make login window
      window4.setSize(1000,1000);
      
      //set background color
      Container dp = window4.getContentPane();
      dp.setBackground(new Color(247, 205, 208));
      
      //Create header label
      JLabel header1 = new JLabel("Welcome " + name);
      header1.setBounds(100,100,500,80);
      header1.setFont(new Font("Serif", Font.BOLD,55));
      window4.add(header1);

      JLabel dash_board = new JLabel("DASHBOARD");
      dash_board.setBounds(100,200,500,80);
      dash_board.setFont(new Font("Serif", Font.ITALIC,35));
      window4.add(dash_board);

      JLabel td_chores = new JLabel("Todays Chores:");
      td_chores.setBounds(100,350,500,80);
      td_chores.setFont(new Font("Serif",Font.PLAIN,30));
      window4.add(td_chores);

      
      chore1.setBounds(100,450,200,40);
      window4.add(chore1);
      chore1.getText();
      
      chore2.setBounds(100,530,200,40);
      window4.add(chore2);
      chore2.getText();

      //JTextField chore3 = new JTextField();
      chore3.setBounds(100,610,200,40);
      window4.add(chore3);
      chore3.getText();

      //JTextField chore4 = new JTextField();
      chore4.setBounds(100,690,200,40);
      window4.add(chore4);
      chore4.getText();

      JButton gobtn = new JButton("GO");
      gobtn.setBounds(700,850,200,40);
      window4.add(gobtn);

      JLabel grocery = new JLabel("Grocery List:");
      grocery.setFont(new Font("Serif",Font.PLAIN,30));
      grocery.setBounds(550,300,500,70);
      window4.add(grocery);
     
      grocerylist.setText(groceryList);
      grocerylist.setBounds(510,400,300,400);
      window4.add(grocerylist);



      gobtn.addActionListener(new ActionListener() 
      {
        public void actionPerformed(ActionEvent e) 
        {
            String fileName = "user_information.txt";
            String securityQuestion = null;
            String securityAnswer = null;
            String savedUsername = null;
            String passwordLine = null;
            String email = null;
            String chores = null;
            String groceryList = null;
            String name = null;
            ArrayList<String> allChores = new ArrayList<String>();
            allChores.add(chore1.getText());
            allChores.add(chore2.getText());
            allChores.add(chore3.getText());
            allChores.add(chore4.getText());
            System.out.println(allChores);
            Collections.shuffle(allChores);
            System.out.println(allChores);
            String formattedChores = formatArrayList(allChores);
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                Scanner sc = new Scanner(new File(fileName));
                StringBuffer buffer = new StringBuffer();
                while (sc.hasNextLine()) {
                    buffer.append(sc.nextLine()+System.lineSeparator());
                }
                String fileContents = buffer.toString();
                sc.close();
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Username: ")) {
                        savedUsername = line.substring(10);

                        // Read the next line for password
                        passwordLine = reader.readLine();
                        passwordLine = passwordLine.substring(10);
                        String loginCheck = reader.readLine();
                        email = reader.readLine();
                        email = email.substring(7);
                        securityQuestion = reader.readLine();
                        securityAnswer = reader.readLine();
                        String choresList = reader.readLine();
                        chores = choresList.substring(9+savedUsername.length());
                        String grocery = reader.readLine();
                        groceryList = grocery.substring(15+savedUsername.length());

                        if(loginCheck.endsWith("true")){
                            if (!chore1.getText().equals("")&&!chore2.getText().equals("")&&!chore3.getText().equals("")&&!chore4.getText().equals("")){
                                fileContents = fileContents.replace(choresList, savedUsername + " Chores: " + formattedChores);
                            }
                            System.out.println("Saved username is: "+savedUsername);
                            fileContents = fileContents.replace(grocery, savedUsername + " Grocery List: " + grocerylist.getText());
                            FileWriter writer = new FileWriter(fileName);
                            System.out.println("");
                            System.out.println("new data: "+fileContents);
                            writer.append(fileContents);
                            writer.close();
                            System.out.println("Current user: " + savedUsername);
                            System.out.println("Chores: " + choresList);
                            
                            break;
                        }
                        }
                    }
                } catch (IOException ea) {
                ea.printStackTrace();
                }
            chore1.setText("");
            chore2.setText("");
            chore3.setText("");
            chore4.setText("");
            ChoresRandomizer menu = new ChoresRandomizer();
        }
        
           
      });


  
      window4.setLayout(null);
      window4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window4.setVisible(true);
      window4.setResizable(false);
      window4.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            String fileName = "user_information.txt";
            String savedUsername = null;
            String groceryList = null;
            if (JOptionPane.showConfirmDialog(window4, 
                "Are you sure you want to exit? (This will log you out)", "Close Window?", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    Scanner sc = new Scanner(new File(fileName));
                    StringBuffer buffer = new StringBuffer();
                    while (sc.hasNextLine()) {
                    buffer.append(sc.nextLine()+System.lineSeparator());
                }
                String fileContents = buffer.toString();
                sc.close();
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Username: ")) {
                        savedUsername = line.substring(10);

                        // Read the next line for password
                        reader.readLine();
                        String loginCheck = reader.readLine();
                        reader.readLine();
                        reader.readLine();
                        reader.readLine();
                        reader.readLine();
                        String grocery = reader.readLine();
                        groceryList = grocery.substring(15+savedUsername.length());
                        
                        //save the grocery list to the file on exit
                        if(loginCheck.endsWith("true")){
                            fileContents = fileContents.replace(grocery, savedUsername + " Grocery List: " + grocerylist.getText());
                            System.out.println("Saved username is: "+savedUsername);   
                            FileWriter writer = new FileWriter(fileName);
                            writer.append(fileContents);
                            writer.close();
                            break;
                        }
                        }
                    }
                    //log this account (everyone) out
                    fileContents = fileContents.replace("Login: true", "Login: false");
                    FileWriter writer = new FileWriter(fileName);
                    writer.append(fileContents);
                    writer.close();

                } catch (IOException ea) {
                ea.printStackTrace();
                }
                System.exit(0);
            }
            }
        });

    }
    public static String formatArrayList(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    //for the next page
    /* Example usage 
        String inputString = "A, B, C, D";
        ArrayList<String> arrayList = convertToArrayList(inputString);
        System.out.println(arrayList);
    */
    public static ArrayList<String> convertToArrayList(String inputString) {
        List<String> stringList = Arrays.asList(inputString.split(",\\s*"));
        return new ArrayList<>(stringList);
    }
    
}
