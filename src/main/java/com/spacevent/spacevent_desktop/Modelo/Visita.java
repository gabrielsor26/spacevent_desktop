
package com.spacevent.spacevent_desktop.Modelo;

import java.sql.Date;

public class Visita {
  
    private long ID;
    private String Username;
    private int Phone;
    private String Email;
    private long ID_Local;
    private Date Dates;
    private long Hours;
    private String Estade;
    private Long ID_Personal;

    public Visita() {
    }

    public Visita(String Username, int Phone, String Email, long ID_Local, Date Dates, long Hours, String Estade, Long ID_Personal) {
        this.Username = Username;
        this.Phone = Phone;
        this.Email = Email;
        this.ID_Local = ID_Local;
        this.Dates = Dates;
        this.Hours = Hours;
        this.Estade = Estade;
        this.ID_Personal = ID_Personal;
    }

    public Visita(long ID, String Username, int Phone, String Email, long ID_Local, Date Dates, long Hours, String Estade, Long ID_Personal) {
        this.ID = ID;
        this.Username = Username;
        this.Phone = Phone;
        this.Email = Email;
        this.ID_Local = ID_Local;
        this.Dates = Dates;
        this.Hours = Hours;
        this.Estade = Estade;
        this.ID_Personal = ID_Personal;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public long getID_Local() {
        return ID_Local;
    }

    public void setID_Local(long ID_Local) {
        this.ID_Local = ID_Local;
    }

    public Date getDates() {
        return Dates;
    }

    public void setDates(Date Dates) {
        this.Dates = Dates;
    }

    public long getHours() {
        return Hours;
    }

    public void setHours(long Hours) {
        this.Hours = Hours;
    }

    public String getEstade() {
        return Estade;
    }

    public void setEstade(String Estade) {
        this.Estade = Estade;
    }

    public Long getID_Personal() {
        return ID_Personal;
    }

    public void setID_Personal(Long ID_Personal) {
        this.ID_Personal = ID_Personal;
    }

    @Override
    public String toString() {
        return "Visita{" + "ID=" + ID + ", Username=" + Username + ", Phone=" + Phone + ", Email=" + Email + ", ID_Local=" + ID_Local + ", Dates=" + Dates + ", Hours=" + Hours + ", Estade=" + Estade + ", ID_Personal=" + ID_Personal + '}';
    }

    
    
    

}
