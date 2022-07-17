/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biz.aavaz.aavazapplicantfreshjrproject.controller;

import biz.aavaz.aavazapplicantfreshjrproject.dao.SkillDao;
import biz.aavaz.aavazapplicantfreshjrproject.dao.Userdao;
import biz.aavaz.aavazapplicantfreshjrproject.model.Skill;
import biz.aavaz.aavazapplicantfreshjrproject.model.User;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class UserController {
    
    Userdao userdao = new Userdao();
    private User user;

    public void addUser(String path) throws FileNotFoundException {
        // deserialize the skill file at path using gson getting a skill object
        // skill object should be saved with SkillDAO.save(skill)

        Gson gson = new Gson();
        File file = new File(path);
        file.setReadable(true);
        JsonReader reader = new JsonReader(new FileReader(file));
        Skill skill = gson.fromJson(reader, User.class);
        userdao.save(user);
        
    }
    
    public void printAllUser() {

        List<User> listOfUser  = Userdao.getUserAll();
        listOfUser.forEach(System.out::println);
    }
    
    
}
