/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * New developer model, for capturing required input on ADD requests
 * @author Chris
 */
public class NewDeveloper {
    
    private String userId;
    private String name;
    private String password;
    
    public NewDeveloper() {
    }

    public NewDeveloper(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   

    @Override
    public String toString() {
        return "NewDeveloper{UserID=" + userId + 
                ",name=" + name + 
                ",password=" + password + "}";
    }
    
    
}
