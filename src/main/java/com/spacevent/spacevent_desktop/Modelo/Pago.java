/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Modelo;

import java.sql.Date;

public class Pago {
    
    private int ID;
    private String Estade;
    private double total_monto;
    private Date fecha;

    public Pago() {
    }

    public Pago(String Estade, double total_monto, Date fecha) {
        this.Estade = Estade;
        this.total_monto = total_monto;
        this.fecha = fecha;
    }

    public Pago(int ID, String Estade, double total_monto, Date fecha) {
        this.ID = ID;
        this.Estade = Estade;
        this.total_monto = total_monto;
        this.fecha = fecha;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEstade() {
        return Estade;
    }

    public void setEstade(String Estade) {
        this.Estade = Estade;
    }

    public double getTotal_monto() {
        return total_monto;
    }

    public void setTotal_monto(double total_monto) {
        this.total_monto = total_monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Pago{" + "ID=" + ID + ", Estade=" + Estade + ", total_monto=" + total_monto + ", fecha=" + fecha + '}';
    }
    
    
}
