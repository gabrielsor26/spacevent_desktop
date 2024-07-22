/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Modelo;

import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class Contrato {
    
    private long id_contrato;
    private String Nombres;
    private String Apellidos;
    private int DNI;
    private long InicioHoraReserva;
    private long FinHoraReserva;
    private Date FechaReserva;
    private String Direccion;
    private Date Fecha_Contrato;
    private long ID_Local;

    public Contrato() {
    }

    public Contrato(String Nombres, String Apellidos, int DNI, long InicioHoraReserva, long FinHoraReserva, Date FechaReserva, String Direccion, Date Fecha_Contrato, long ID_Local) {
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.DNI = DNI;
        this.InicioHoraReserva = InicioHoraReserva;
        this.FinHoraReserva = FinHoraReserva;
        this.FechaReserva = FechaReserva;
        this.Direccion = Direccion;
        this.Fecha_Contrato = Fecha_Contrato;
        this.ID_Local = ID_Local;
    }
    
    public Contrato(long id_contrato, String Nombres, String Apellidos, int DNI, long InicioHoraReserva, long FinHoraReserva, Date FechaReserva, String Direccion, Date Fecha_Contrato, long ID_Local) {
        this.id_contrato = id_contrato;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.DNI = DNI;
        this.InicioHoraReserva = InicioHoraReserva;
        this.FinHoraReserva = FinHoraReserva;
        this.FechaReserva = FechaReserva;
        this.Direccion = Direccion;
        this.Fecha_Contrato = Fecha_Contrato;
        this.ID_Local = ID_Local;
    }

    public long getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(long id_contrato) {
        this.id_contrato = id_contrato;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public long getInicioHoraReserva() {
        return InicioHoraReserva;
    }

    public void setInicioHoraReserva(long InicioHoraReserva) {
        this.InicioHoraReserva = InicioHoraReserva;
    }

    public long getFinHoraReserva() {
        return FinHoraReserva;
    }

    public void setFinHoraReserva(long FinHoraReserva) {
        this.FinHoraReserva = FinHoraReserva;
    }

    public Date getFechaReserva() {
        return FechaReserva;
    }

    public void setFechaReserva(Date FechaReserva) {
        this.FechaReserva = FechaReserva;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public Date getFecha_Contrato() {
        return Fecha_Contrato;
    }

    public void setFecha_Contrato(Date Fecha_Contrato) {
        this.Fecha_Contrato = Fecha_Contrato;
    }

    public long getID_Local() {
        return ID_Local;
    }

    public void setID_Local(long ID_Local) {
        this.ID_Local = ID_Local;
    }

    @Override
    public String toString() {
        return "Contrato{" + "id_contrato=" + id_contrato + ", Nombres=" + Nombres + ", Apellidos=" + Apellidos + ", DNI=" + DNI + ", InicioHoraReserva=" + InicioHoraReserva + ", FinHoraReserva=" + FinHoraReserva + ", FechaReserva=" + FechaReserva + ", Direccion=" + Direccion + ", Fecha_Contrato=" + Fecha_Contrato + ", ID_Local=" + ID_Local + '}';
    }
    
    

}
