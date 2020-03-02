/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Chris
 */
public class Developers {
    private Integer id;
    private String userId;
    private String name;
    private String passwordHash;
    private String salt;

    public Developers() {
    }

    public Developers(Integer id, String userId, String name, String passwordHash, String salt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Developer{id=" + id + 
                ",userId=" + userId + 
                ",name=" + name + 
                ",passwordHash=" + passwordHash + 
                ",salt=" + salt + "}";
    }
    
    
    
}
