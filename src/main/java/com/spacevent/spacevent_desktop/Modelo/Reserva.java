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
public class Reserva {

    private long ID;
    private long ID_Usuario;
    private long ID_Local;
    private int starttime;
    private int endtime;
    private Date dates;
    private long id_pago;
    private double mount;
    private long id_contrato;
    private Long ID_Personal;

    public Reserva() {
    }

    public Reserva(long ID, long ID_Usuario, long ID_Local, int starttime, int endtime, Date dates, long id_pago, double mount, long id_contrato, Long ID_Personal) {
        this.ID = ID;
        this.ID_Usuario = ID_Usuario;
        this.ID_Local = ID_Local;
        this.starttime = starttime;
        this.endtime = endtime;
        this.dates = dates;
        this.id_pago = id_pago;
        this.mount = mount;
        this.id_contrato = id_contrato;
        this.ID_Personal = ID_Personal;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(long ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public long getID_Local() {
        return ID_Local;
    }

    public void setID_Local(long ID_Local) {
        this.ID_Local = ID_Local;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public long getId_pago() {
        return id_pago;
    }

    public void setId_pago(long id_pago) {
        this.id_pago = id_pago;
    }

    public double getMount() {
        return mount;
    }

    public void setMount(double mount) {
        this.mount = mount;
    }

    public long getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(long id_contrato) {
        this.id_contrato = id_contrato;
    }

    public Long getID_Personal() {
        return ID_Personal;
    }

    public void setID_Personal(Long ID_Personal) {
        this.ID_Personal = ID_Personal;
    }

    @Override
    public String toString() {
        return "Reserva{" + "ID=" + ID + ", ID_Usuario=" + ID_Usuario + ", ID_Local=" + ID_Local + ", starttime=" + starttime + ", endtime=" + endtime + ", dates=" + dates + ", id_pago=" + id_pago + ", mount=" + mount + ", id_contrato=" + id_contrato + ", ID_Personal=" + ID_Personal + '}';
    }

    
    
}
