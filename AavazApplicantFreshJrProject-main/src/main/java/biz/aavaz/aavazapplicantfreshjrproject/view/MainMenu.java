package biz.aavaz.aavazapplicantfreshjrproject.view;

import biz.aavaz.aavazapplicantfreshjrproject.controller.SkillController;
import biz.aavaz.aavazapplicantfreshjrproject.controller.UserController;
import static biz.aavaz.aavazapplicantfreshjrproject.dao.SkillDao.dbUrl;
import biz.aavaz.aavazapplicantfreshjrproject.model.Skill;
import biz.aavaz.aavazapplicantfreshjrproject.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainMenu {

    public static void main(String[] args) throws FileNotFoundException, SQLException {

        boolean isContinue = true;

        do {
            paintMenu();

            switch (processInput()) {

                case 1:// add skill
                    addSkill();
                    break;
                case 2:
                    printSkills();
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    getUser();
                    break;
                case 5:// exit
                    System.out.println("Good bye");
                    isContinue = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }

        } while (isContinue);
    }

    public static void paintMenu() {
        System.out.println("=========================");
        System.out.println("1. Add a Skill by reading a JSON file");
        System.out.println("2. Print all skills in database");
        System.out.println("3. Add a User by reading a json file");
        System.out.println("4. Get all Users by skill name");
        System.out.println("5. Exit");
    }

    public static int processInput() {

        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    private static void addSkill() {

        String defaultSkillsFolder = "src/main/resources/skills/";
        String filePath = "";
        SkillController skillController = new SkillController();

        System.out.println("Default skills folder -> [" + defaultSkillsFolder + " ]");
        System.out.println("1. Use default folder");
        System.out.println("2. Specify full path");

        Scanner userScanner = new Scanner(System.in);

        if (userScanner.nextInt() == 2) {
            System.out.println("Enter full path including filename & extention:");
            filePath = userScanner.next();
        } else {
            System.out.println("Enter filename with extention ( eq. english.json) :");
            filePath = defaultSkillsFolder + userScanner.next();
        }

        try {
            skillController.addSkill(filePath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There was an error while processing");
        }
    }

    private static void printSkills() {
        
        SkillController skillController = new SkillController();
        
        Skill skill = new Skill();
        try
        {
            if(skill!=null)
            {
                System.out.println("User Skill is required"); //validating 
            }
            else 
            {
                skillController.printAllSkills();
            }
        }
        catch(Exception e)
        {
            System.out.println("Skills are not presented"+e);
        }
   
    }
    
    
    private static void addUser()
    {
        String defaultUserFolder = "src/main/resources/user/";
        String filePath = "";
        UserController userController = new UserController();

        System.out.println("Default Userfolder -> [" + defaultUserFolder + " ]");
        System.out.println("1. Use default folder");
        System.out.println("2. Specify full path");

        Scanner userScanner = new Scanner(System.in);

        if (userScanner.nextInt() == 2) {
            System.out.println("Enter full path including filename & extention:");
            filePath = userScanner.next();
        } else {
            System.out.println("Enter filename with extention3 ( eq. Biden.json) :");
            filePath = defaultUserFolder + userScanner.next();
        }

        try {
            userController.addUser(filePath);
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry! Processing Failed.");
        }
        
        
    }
    
    private static void getUser() throws SQLException
    {
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement st = conn.prepareStatement("insert into UserSkill values(?,?,?)");)
        {
                User user = new User();
                Skill skill = new Skill();
                st.setInt(1, user.getId());
                st.setInt(2, user.getId());
                st.setInt(3, skill.getId());
                st.executeUpdate();
                
        }
        catch(SQLException e)
        {
            System.out.println("Data faiied to save.\nInsertion is failed");
        }
        
        try (Connection conn = DriverManager.getConnection(dbUrl);
                Statement stmt = conn.createStatement();
                ResultSet resultSet = conn.createStatement().executeQuery("select firstName from User inner join Skill on User.id==Skill.id");)
        {
            UserController userController = new UserController();    
            User user = new User();
            if (resultSet.next()==user.getFirstName(resultSet.getString(2))) {
                
            userController.printAllUser();
            }
            
                
            
        }
        catch(SQLException e)
        {
        System.out.println(e);
        }
                        
    }

    
    
}
