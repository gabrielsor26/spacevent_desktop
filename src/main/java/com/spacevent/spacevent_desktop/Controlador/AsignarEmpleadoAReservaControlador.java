/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.ContratoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Detalle_Pago;
import com.spacevent.spacevent_desktop.Modelo.Detalle_PagoConsulta;
import com.spacevent.spacevent_desktop.Modelo.LocalConsulta;
import com.spacevent.spacevent_desktop.Modelo.Pago;
import com.spacevent.spacevent_desktop.Modelo.PagoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Personal;
import com.spacevent.spacevent_desktop.Modelo.Reserva;
import com.spacevent.spacevent_desktop.Modelo.ReservaConsulta;
import com.spacevent.spacevent_desktop.Modelo.Usuario;
import com.spacevent.spacevent_desktop.Modelo.UsuarioConsulta;
import com.spacevent.spacevent_desktop.Modelo.VisitaConsulta;
import com.spacevent.spacevent_desktop.Vista.ListaEmpleadosVista;
import com.spacevent.spacevent_desktop.Vista.ReservaAdminVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Usuario
 */
public class AsignarEmpleadoAReservaControlador implements MouseListener {

    private final Long id_reserva;
    private final ReservaConsulta consulta;
    private final ListaEmpleadosVista vista;

    public AsignarEmpleadoAReservaControlador(Long id_reserva, ReservaConsulta consulta, ListaEmpleadosVista vista) {
        this.id_reserva = id_reserva;
        this.consulta = consulta;
        this.vista = vista;
        //Botones
        this.vista.btnAsignaPersonal.addMouseListener(this);
    }

    public void iniciar() {
        vista.setTitle("ListaEmpleado");
        vista.setLocationRelativeTo(null);
        consulta.llenarComboBoxPersonales(vista.cbxEmpleados);
    }

    public ListaEmpleadosVista getFrm() {
        return vista;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnAsignaPersonal) {

            Personal personalSeleccionado = (Personal) vista.cbxEmpleados.getSelectedItem();

            if (consulta.asignarPersonalAReserva(id_reserva, personalSeleccionado)) {
                vista.dispose();
            } else {

            };

            ReservaAdminControlador ctrl = new ReservaAdminControlador(
                    new ReservaAdminVista(),
                    new Reserva(),
                    new ReservaConsulta(),
                    new ContratoConsulta(),
                    new Usuario(),
                    new UsuarioConsulta(),
                    new LocalConsulta(),
                    new Pago(),
                    new PagoConsulta(),
                    new Detalle_Pago(),
                    new Detalle_PagoConsulta());
            ctrl.iniciar();
            ctrl.getFrm().setVisible(true);

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
