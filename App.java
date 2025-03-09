package choresmania;
//for later
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {
  private static final String JSON_FILE_PATH = "accounts.json";

  public static void main(String[] args) throws Exception {
    //creating the data structure
    JSONObject jo = new JSONObject();
    jo.put("username", "John");
    jo.put("email", "sdkn@gmail.com");
    jo.put("password", "1234AZ");
    jo.put("securityQuestion", "What's your favorite color?");
    jo.put("securityAnswer", "green");

    JSONObject member = new JSONObject();
    JSONArray assignedChores = new JSONArray();
    JSONArray unassignedChores = new JSONArray();
    member.put("personName","John");
    member.put("assignedChores",assignedChores);

    //add member to family
    JSONArray familyMembers = new JSONArray();
    familyMembers.add(member);

    jo.put("family",familyMembers);
    jo.put("unassignedChores",unassignedChores);
    jo.put("customTheme", "green");

    //add user to total list of users
    JSONArray ja = new JSONArray();
    ja.add(jo);

    System.out.println(ja);

    try (FileWriter file = new FileWriter("accounts.json")) {
            file.write(ja.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
  }
}