/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.Personal;
import com.spacevent.spacevent_desktop.Modelo.Visita;
import com.spacevent.spacevent_desktop.Modelo.VisitaConsulta;
import com.spacevent.spacevent_desktop.Vista.ListaEmpleadosVista;
import com.spacevent.spacevent_desktop.Vista.VisitaAdminVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Usuario
 */
public class AsignarEmpleadoAVisitaControlador implements MouseListener {

    private final Long id_visita;
    private final VisitaConsulta consulta;
    private final ListaEmpleadosVista vista;

    public AsignarEmpleadoAVisitaControlador(Long id_visita, VisitaConsulta consulta, ListaEmpleadosVista vista) {
        this.id_visita = id_visita;
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

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnAsignaPersonal) {

            Personal personalSeleccionado = (Personal) vista.cbxEmpleados.getSelectedItem();

            if (consulta.asignarPersonalAVisita(id_visita, personalSeleccionado)) {
                vista.dispose();
            } else {

            };
            Visita modelo = new Visita();
            VisitaConsulta consulta = new VisitaConsulta();
            VisitaAdminVista vista = new VisitaAdminVista();
            VisitaAdminControlador ctrl = new VisitaAdminControlador(modelo, consulta, vista);
            ctrl.iniciar();
            vista.setVisible(true);

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
