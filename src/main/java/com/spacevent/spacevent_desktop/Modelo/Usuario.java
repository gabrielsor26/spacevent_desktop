
package com.spacevent.spacevent_desktop.Modelo;

public class Usuario {
    
    private long id;
    private String firstname;
    private String lastname;
    private int phone;
    private String email;
    private String username;
    private String userpassword;

    public Usuario() {
    }

    public Usuario(String firstname, String lastname, int phone, String email, String username, String userpassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.userpassword = userpassword;
    }

    
    
    public Usuario(long id, String firstname, String lastname, int phone, String email, String username, String userpassword) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.userpassword = userpassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone + ", email=" + email + ", username=" + username + ", userpassword=" + userpassword + '}';
    }
    
}
