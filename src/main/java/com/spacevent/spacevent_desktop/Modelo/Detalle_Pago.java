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
public class Detalle_Pago {
    
    private int id_detalle;
    private int id_pago;
    private String parte;
    private double monto;
    private int numero_operacion;
    private Date fecha_pago;
    private String status;

    public Detalle_Pago() {
    }

    public Detalle_Pago(int id_pago, String parte, double monto, int numero_operacion, Date fecha_pago, String status) {
        this.id_pago = id_pago;
        this.parte = parte;
        this.monto = monto;
        this.numero_operacion = numero_operacion;
        this.fecha_pago = fecha_pago;
        this.status = status;
    }

    public Detalle_Pago(int id_detalle, int id_pago, String parte, double monto, int numero_operacion, Date fecha_pago, String status) {
        this.id_detalle = id_detalle;
        this.id_pago = id_pago;
        this.parte = parte;
        this.monto = monto;
        this.numero_operacion = numero_operacion;
        this.fecha_pago = fecha_pago;
        this.status = status;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getNumero_operacion() {
        return numero_operacion;
    }

    public void setNumero_operacion(int numero_operacion) {
        this.numero_operacion = numero_operacion;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Detalle_Pago{" + "id_detalle=" + id_detalle + ", id_pago=" + id_pago + ", parte=" + parte + ", monto=" + monto + ", numero_operacion=" + numero_operacion + ", fecha_pago=" + fecha_pago + ", status=" + status + '}';
    }
}
