package choresmania;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class ChoresRandomizer 
{
    JFrame window5 = new JFrame("Duty Assignment");
    public ChoresRandomizer()
    {
      //Read file for chores, display the randomized chores under each person
      String fileName = "user_information.txt";
      String savedUsername = null;
      String passwordLine = null;
      String email = null;
      String groceryList = null;
      ArrayList<String> choresArrayList = new ArrayList<String>();
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
          if (line.startsWith("Name: ")) {
            line.substring(6);
            savedUsername = reader.readLine();
            savedUsername = savedUsername.substring(10);
            passwordLine = reader.readLine();
            passwordLine = passwordLine.substring(10);
            String loginCheck = reader.readLine();
            email = reader.readLine();
            email = email.substring(7);
            reader.readLine();
            reader.readLine();
            String choresList = reader.readLine();
            choresList = choresList.substring(9+savedUsername.length());
            System.out.println("Chores list is: " +choresList);
            choresArrayList = convertToArrayList(choresList);
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
      window5.setSize(1500,1500);

      Container rp = window5.getContentPane();
      rp.setBackground(new Color(247, 205, 208));
      
      ImageIcon persons = new ImageIcon("/Users/kuroliu/chores mania/demo/src/main/java/choresmania/catfriends.png");
      JLabel label_persons = new JLabel(persons);
      label_persons.setBounds(50,80, 1600, 1500);
      window5.add(label_persons);

      JLabel person1 = new JLabel("Person #1");
      person1.setBounds(320,300,180,40);
      person1.setFont(new Font("Serif", Font.BOLD, 20));
      window5.add(person1);

      //make label for person1
      JLabel person1_label = new JLabel(choresArrayList.get(0));
      person1_label.setBounds(320,350,200,40);
      person1_label.setFont(new Font("Serif", Font.BOLD, 20));
      window5.add(person1_label);

      JLabel person2 = new JLabel("Person #2");
      person2.setBounds(965,300,180,40);
      person2.setFont(new Font("Serif", Font.BOLD, 20));
      window5.add(person2);

      JLabel person2_label = new JLabel(choresArrayList.get(1));
      person2_label.setBounds(965,350,200,40);
      person2_label.setFont(new Font("Serif", Font.BOLD, 20));
      window5.add(person2_label);

      JLabel person3 = new JLabel("Person #3");
      person3.setBounds(320,780,180,40);
      person3.setFont(new Font("Serif", Font.BOLD, 20));
      window5.add(person3);

      JLabel person3_label = new JLabel(choresArrayList.get(2));
      person3_label.setBounds(320,830,200,40);
      person3_label.setFont(new Font("Serif", Font.BOLD, 20));
      window5.add(person3_label);

      JLabel person4 = new JLabel("Person #4");
      person4.setBounds(965,780,180,40);
      person4.setFont(new Font("Serif", Font.BOLD, 20));
      window5.add(person4);

      JLabel person4_label = new JLabel(choresArrayList.get(3));
      person4_label.setBounds(965,830,200,40);
      person4_label.setFont(new Font("Serif", Font.BOLD, 20));
      window5.add(person4_label);

      JButton back_button = new JButton("Go Back");
      back_button.setBounds(90, 50, 100, 40);
      window5.add(back_button);
      back_button.addActionListener(new ActionListener() 
      {
          public void actionPerformed(ActionEvent e) 
          {
            Dashboard menu = new Dashboard();
          }
      });

      


      window5.setLayout(null);
      window5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window5.setVisible(true);
      window5.setResizable(false);
      window5.addWindowListener(new java.awt.event.WindowAdapter() {
      //exit and log out
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            if (JOptionPane.showConfirmDialog(window5, 
                "Are you sure you want to exit? (This will log you out)", "Close Window?", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                String fileName = "user_information.txt";
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    Scanner sc = new Scanner(new File(fileName));
                    StringBuffer buffer = new StringBuffer();
                    while (sc.hasNextLine()) {
                        buffer.append(sc.nextLine()+System.lineSeparator());
                    }
                    String fileContents = buffer.toString();
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
