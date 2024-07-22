/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.Personal;
import com.spacevent.spacevent_desktop.Modelo.PersonalConsulta;
import com.spacevent.spacevent_desktop.Vista.TrabajadoresVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class PersonalControlador implements MouseListener {

    private final Personal modelo;
    private final PersonalConsulta consulta;
    private final TrabajadoresVista vista;

    public PersonalControlador(Personal modelo, PersonalConsulta consulta, TrabajadoresVista vista) {
        this.modelo = modelo;
        this.consulta = consulta;
        this.vista = vista;
        //Botones
        this.vista.btnCrearAdmin.addMouseListener(this);
        this.vista.btnCrearAsesor.addMouseListener(this);
        this.vista.btnModificar.addMouseListener(this);
        this.vista.btnEliminar.addMouseListener(this);
    }

    public void iniciar() {
        vista.setTitle("Personal");
        vista.setLocationRelativeTo(null);
        consulta.llenarTabla(vista.jtPersonal);
    }
    
    public TrabajadoresVista getFrm(){
        return vista;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnCrearAsesor) {
            String user = JOptionPane.showInputDialog("Ingrese el username");
            modelo.setUsername(user);
            String pass = JOptionPane.showInputDialog("Ingrese el password");
            modelo.setUserpassword(pass);
            modelo.setRol("Asesor");

            if (consulta.createPersonal(modelo)) {
                consulta.llenarTabla(vista.jtPersonal);
                JOptionPane.showMessageDialog(null, "Personal creado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en crear personal");
            }

        }

        if (e.getSource() == vista.btnCrearAdmin) {
            String user = JOptionPane.showInputDialog("Ingrese el username");
            modelo.setUsername(user);
            String pass = JOptionPane.showInputDialog("Ingrese el password");
            modelo.setUserpassword(pass);
            modelo.setRol("Admin");

            if (consulta.createPersonal(modelo)) {
                consulta.llenarTabla(vista.jtPersonal);
                JOptionPane.showMessageDialog(null, "Personal creado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en crear personal");
            }

        }

        if (e.getSource() == vista.btnModificar) {

            //Obligatoriamente seleccionar columna
            if (vista.jtPersonal.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }
            int fila = vista.jtPersonal.getSelectedRow();
            long id = (long) vista.jtPersonal.getValueAt(fila, 0);
            modelo.setId(id);
            String fn = vista.jtPersonal.getValueAt(fila, 1).toString();
            modelo.setUsername(fn);
            String ln = vista.jtPersonal.getValueAt(fila, 2).toString();
            modelo.setUserpassword(ln);
            // Supongamos que ya tienes el valor del rol
            String rol = vista.jtPersonal.getValueAt(fila, 3).toString();
            if (!rol.equals("Admin") && !rol.equals("Asesor")) {
                // Mostrar un JOptionPane para seleccionar el rol válido
                String[] options = {"Admin", "Asesor"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Seleccione un rol válido",
                        "Rol Inválido",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[0]);

                // Establecer el rol basado en la elección del usuario
                if (choice == 0) {
                    rol = "Admin";
                } else if (choice == 1) {
                    rol = "Asesor";
                } else {
                    // En caso de que el usuario cierre el JOptionPane o haga una selección inválida, puedes manejarlo como prefieras
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un rol válido.");
                    return; // Salir del método para evitar continuar con un rol inválido
                }
            }

            modelo.setRol(rol);

            if (consulta.updatePersonal(modelo)) {
                consulta.llenarTabla(vista.jtPersonal);
                JOptionPane.showMessageDialog(null, "Personal modificado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en personal usuario");
            }

        }

        if (e.getSource() == vista.btnEliminar) {

            //Obligatoriamente seleccionar columna
            if (vista.jtPersonal.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }
            int fila = vista.jtPersonal.getSelectedRow();
            long id = (long) vista.jtPersonal.getValueAt(fila, 0);

            if (consulta.deletePersonal(id)) {
                consulta.llenarTabla(vista.jtPersonal);
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
