import java.util.Scanner;

import javax.swing.JOptionPane;

public class test {
    public static void main(String[] args){
        String fileName = "user_information.txt";
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
                if (line.startsWith("Username: ")) {
                    String savedUsername = line.substring(10);

                    // Read the next line for password
                    String passwordLine = reader.readLine();
                    passwordLine = passwordLine.substring(10);
                    String loginCheck = reader.readLine();
                    String email = reader.readLine();
                    email = email.substring(7);
                    String securityQuestion = reader.readLine();
                    String securityAnswer = reader.readLine();
                    String choresList = reader.readLine();
                    choresList = choresList.substring(8);
                    if(loginCheck.endsWith("true")){
                        System.out.println("Current user: " + savedUsername);
                        System.out.println("Chores: " + choresList);
                        break;
                    }
                    }
                }
            } catch (IOException e) {
            e.printStackTrace();
            }
    }
}
