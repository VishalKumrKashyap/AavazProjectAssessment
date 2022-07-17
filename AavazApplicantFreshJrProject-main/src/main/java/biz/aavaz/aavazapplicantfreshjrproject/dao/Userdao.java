/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biz.aavaz.aavazapplicantfreshjrproject.dao;
import static biz.aavaz.aavazapplicantfreshjrproject.dao.SkillDao.dbUrl;
import biz.aavaz.aavazapplicantfreshjrproject.model.Skill;
import biz.aavaz.aavazapplicantfreshjrproject.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Userdao {

    private int userid;
    private String first_name;
    private String last_name;
    private int age;
    
      
    public void save(User user) 
    {
    try{
    String db = "jdbc:sqlite:src/main/resources/database/UserDB.work.sql";
        Connection conn = DriverManager.getConnection(db);
        PreparedStatement pmt = conn.prepareStatement("INSERT INTO User values (?, ?, ?, ?)");
            pmt.setInt(1, userid);
            pmt.setString(2, first_name);
            pmt.setString(3, last_name);
            pmt.setInt(4,age);
            pmt.executeUpdate();
            
    }
    catch(SQLException e)
    {
    System.out.println("Faled to upload data"+e);
    }
    }
    
    static public List<User> getUserAll() {

        List<User> UserList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbUrl);
                Statement stmt = conn.createStatement();
                ResultSet resultSet = conn.createStatement().executeQuery("select * from User");) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setlastName(resultSet.getString(3));
                UserList.add(user);
            }

        } catch (SQLException ex) {
            System.out.println("unable to fetch data");
        }

        return UserList;
    }
    
}
