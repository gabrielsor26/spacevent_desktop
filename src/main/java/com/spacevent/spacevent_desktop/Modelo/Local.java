/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Modelo;

public class Local {
    
    private long ID;
    private String Descrip;
    private String Direction;
    private int Prize;
    private String Imageroute;

    public Local() {
    }

    public Local(String Descrip, String Direction, int Prize, String Imageroute) {
        this.Descrip = Descrip;
        this.Direction = Direction;
        this.Prize = Prize;
        this.Imageroute = Imageroute;
    }

    public Local(long ID, String Descrip, String Direction, int Prize, String Imageroute) {
        this.ID = ID;
        this.Descrip = Descrip;
        this.Direction = Direction;
        this.Prize = Prize;
        this.Imageroute = Imageroute;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getDescrip() {
        return Descrip;
    }

    public void setDescrip(String Descrip) {
        this.Descrip = Descrip;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String Direction) {
        this.Direction = Direction;
    }

    public int getPrize() {
        return Prize;
    }

    public void setPrize(int Prize) {
        this.Prize = Prize;
    }

    public String getImageroute() {
        return Imageroute;
    }

    public void setImageroute(String Imageroute) {
        this.Imageroute = Imageroute;
    }

    @Override
    public String toString() {
        return "Local{" + "ID=" + ID + ", Descrip=" + Descrip + ", Direction=" + Direction + ", Prize=" + Prize + ", Imageroute=" + Imageroute + '}';
    }

    
    
}
