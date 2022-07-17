package biz.aavaz.aavazapplicantfreshjrproject.model;

//!!! Serialization to handle json snake case and java camel case
//!!! https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/annotations/SerializedName.html
//!!! https://sites.google.com/site/gson/gson-user-guide#TOC-JSON-Field-Naming-Support (JSON Field Naming Support)

public class User {
    private String first_name;
    private String last_name;
    private int age;
    private int userid;
    
    
     public int getId() {
        return userid;
    }

    public void setId(int id) {
        this.userid = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    
    public String getlastName() {
        return last_name;
    }

    public void setlastName(String last_name) {
        this.last_name = last_name;
    }
    
    public int getAge()
    {
    return age;
    }

    @Override
    public String toString() {
        return "User [id=" + userid + ", first_name=" + first_name + ", last_name=" + last_name +", age=" + age + "]";
    }  

    public boolean getFirstName(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
