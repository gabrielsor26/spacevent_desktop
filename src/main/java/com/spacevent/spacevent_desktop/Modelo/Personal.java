/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Modelo;

/**
 *
 * @author Usuario
 */
public class Personal {

    private long id;
    private String username;
    private String userpassword;
    private String rol;

    public Personal() {
    }

    public Personal(String username, String userpassword, String rol) {
        this.username = username;
        this.userpassword = userpassword;
        this.rol = rol;
    }

    public Personal(long id, String username, String userpassword, String rol) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.rol = rol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return username;
    }

}
