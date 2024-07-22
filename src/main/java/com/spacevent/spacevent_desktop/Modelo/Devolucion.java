/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Modelo;

import java.sql.Date;

public class Devolucion {
    
    private long ID;
    private long ID_Reserva;
    private String Estade;
    private double montoDevolver;
    private Integer numeroOperacion;
    private Date fechaAbono;

    public Devolucion() {
    }

    public Devolucion(long ID, long ID_Reserva, String Estade, double montoDevolver, Integer numeroOperacion, Date fechaAbono) {
        this.ID = ID;
        this.ID_Reserva = ID_Reserva;
        this.Estade = Estade;
        this.montoDevolver = montoDevolver;
        this.numeroOperacion = numeroOperacion;
        this.fechaAbono = fechaAbono;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getID_Reserva() {
        return ID_Reserva;
    }

    public void setID_Reserva(long ID_Reserva) {
        this.ID_Reserva = ID_Reserva;
    }

    public String getEstade() {
        return Estade;
    }

    public void setEstade(String Estade) {
        this.Estade = Estade;
    }

    public double getMontoDevolver() {
        return montoDevolver;
    }

    public void setMontoDevolver(double montoDevolver) {
        this.montoDevolver = montoDevolver;
    }

    public Integer getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(Integer numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public Date getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(Date fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    @Override
    public String toString() {
        return "Devolucion{" + "ID=" + ID + ", ID_Reserva=" + ID_Reserva + ", Estade=" + Estade + ", montoDevolver=" + montoDevolver + ", numeroOperacion=" + numeroOperacion + ", fechaAbono=" + fechaAbono + '}';
    }

    
}
