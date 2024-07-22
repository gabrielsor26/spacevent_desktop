package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.Visita;
import com.spacevent.spacevent_desktop.Modelo.VisitaConsulta;
import com.spacevent.spacevent_desktop.Vista.ListaEmpleadosVista;
import com.spacevent.spacevent_desktop.Vista.VisitaAdminVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class VisitaAdminControlador implements MouseListener {

    private final Visita modelo;
    private final VisitaConsulta consulta;
    private final VisitaAdminVista vista;

    public VisitaAdminControlador(Visita modelo, VisitaConsulta consulta, VisitaAdminVista vista) {
        this.modelo = modelo;
        this.consulta = consulta;
        this.vista = vista;
        //Botones
        this.vista.btnCrear.addMouseListener(this);
        this.vista.btnEliminar.addMouseListener(this);
        this.vista.btnAsignaAuto.addMouseListener(this);
        this.vista.btnAsignaOne.addMouseListener(this);
    }

    public void iniciar() {
        vista.setTitle("Visita");
        vista.setLocationRelativeTo(null);
        consulta.llenarTabla(vista.jtVisitante);
    }

    public VisitaAdminVista getFrm() {
        return vista;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnCrear) {

            String username = JOptionPane.showInputDialog("Ingrese username");
            modelo.setUsername(username);
            int phone = Integer.parseInt(JOptionPane.showInputDialog("Ingrese phone"));
            modelo.setPhone(phone);
            String email = JOptionPane.showInputDialog("Ingrese email");
            modelo.setEmail(email);
            long id_local = Long.parseLong(JOptionPane.showInputDialog("Ingrese id_local"));
            modelo.setID_Local(id_local);
//            Date date = Date.valueOf(LocalDate.now());
//            modelo.setDates(date);
//            long hour = 0;
//            modelo.setHours(hour);
            String estade = "Pendiente";
            modelo.setEstade(estade);
            long id_personal = 0;
            modelo.setID_Personal(id_personal);

            if (consulta.createVisita(modelo)) {
                
                JOptionPane.showMessageDialog(null, "Visitante creado");
                consulta.llenarTabla(vista.jtVisitante);
            } else {
                JOptionPane.showMessageDialog(null, "Error en crear visitante");
            }

        }

        if (e.getSource() == vista.btnAsignaAuto) {

            consulta.asignarPersonalEquitativamente();
            consulta.llenarTabla(vista.jtVisitante);

        }

        if (e.getSource() == vista.btnAsignaOne) {

            //Obligatoriamente seleccionar columna
            if (vista.jtVisitante.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }
            int fila = vista.jtVisitante.getSelectedRow();
            System.out.println("La fila es: " + fila);

// Obtener el valor de la tabla y convertirlo correctamente
            Object idValue = vista.jtVisitante.getValueAt(fila, 0);
            long id_visita;

            if (idValue instanceof Integer) {
                id_visita = ((Integer) idValue).longValue();
            } else if (idValue instanceof Long) {
                id_visita = (Long) idValue;
            } else {
                JOptionPane.showMessageDialog(null, "El valor de ID no es del tipo esperado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

// Continuar con la lógica de abrir la nueva ventana
            ListaEmpleadosVista frmListEmpleado = new ListaEmpleadosVista();
            AsignarEmpleadoAVisitaControlador ctrl = new AsignarEmpleadoAVisitaControlador(id_visita, consulta, frmListEmpleado);
            ctrl.iniciar();
            frmListEmpleado.setVisible(true);
            vista.dispose();

        }

        if (e.getSource() == vista.btnEliminar) {

            // Obligatoriamente seleccionar columna
            if (vista.jtVisitante.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }
            int fila = vista.jtVisitante.getSelectedRow();
            int idInt = (Integer) vista.jtVisitante.getValueAt(fila, 0);
            long id = (long) idInt; // Convertir el int a long

            if (consulta.deleteVisita(id)) {
                consulta.llenarTabla(vista.jtVisitante);
                JOptionPane.showMessageDialog(null, "Usuario eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en eliminar usuario");
            }
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
