/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.Usuario;
import com.spacevent.spacevent_desktop.Modelo.UsuarioConsulta;
import com.spacevent.spacevent_desktop.Vista.UsuariosVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class UsuarioControlador implements MouseListener {

    private final Usuario modelo;
    private final UsuarioConsulta consulta;
    private final UsuariosVista vista;

    public UsuarioControlador(Usuario modelo, UsuarioConsulta consulta, UsuariosVista vista) {
        this.modelo = modelo;
        this.consulta = consulta;
        this.vista = vista;
        //Botones
        this.vista.btnCrear.addMouseListener(this);
        this.vista.btnModificar.addMouseListener(this);
        this.vista.btnEliminar.addMouseListener(this);
    }

    public void iniciar() {
        vista.setTitle("Usuario");
        vista.setLocationRelativeTo(null);
        consulta.llenarTabla(vista.jtUsuarios);
    }

    public UsuariosVista getFrm() {
        return vista;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnCrear) {

            // Obtener y validar el firstname
            String fn = JOptionPane.showInputDialog("Ingrese el firstname");
            if (fn == null || fn.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada o el firstname no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            modelo.setFirstname(fn);

            // Obtener y validar el lastname
            String ln = JOptionPane.showInputDialog("Ingrese el lastname");
            if (ln == null || ln.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada o el lastname no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            modelo.setLastname(ln);

            // Obtener y validar el email
            String email = JOptionPane.showInputDialog("Ingrese el email");
            if (email == null || email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada o el email no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            modelo.setEmail(email);

            // Obtener y validar el phone
            String phoneInput = JOptionPane.showInputDialog("Ingrese el phone");
            if (phoneInput == null || phoneInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada o el phone no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int phone = 0;
            try {
                phone = Integer.parseInt(phoneInput);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El phone no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            modelo.setPhone(phone);

            // Obtener y validar el username
            String user = JOptionPane.showInputDialog("Ingrese el username");
            if (user == null || user.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada o el username no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            modelo.setUsername(user);

            // Obtener y validar el password
            String pass = JOptionPane.showInputDialog("Ingrese el password");
            if (pass == null || pass.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada o el password no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            modelo.setUserpassword(pass);

            // Intentar crear el usuario
            if (consulta.createUsuario(modelo) != -1) {
                JOptionPane.showMessageDialog(null, "Usuario creado con éxito");
                consulta.llenarTabla(vista.jtUsuarios);
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista.btnModificar) {

            //Obligatoriamente seleccionar columna
            if (vista.jtUsuarios.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }
            int fila = vista.jtUsuarios.getSelectedRow();
            long id = (long) vista.jtUsuarios.getValueAt(fila, 0);
            modelo.setId(id);
            String fn = vista.jtUsuarios.getValueAt(fila, 1).toString();
            modelo.setFirstname(fn);
            String ln = vista.jtUsuarios.getValueAt(fila, 2).toString();
            modelo.setLastname(ln);
            int phone = (int) vista.jtUsuarios.getValueAt(fila, 3);
            modelo.setPhone(phone);
            String email = vista.jtUsuarios.getValueAt(fila, 4).toString();
            modelo.setEmail(email);
            String user = vista.jtUsuarios.getValueAt(fila, 5).toString();
            modelo.setUsername(user);
            String pass = vista.jtUsuarios.getValueAt(fila, 6).toString();
            modelo.setUserpassword(pass);

            if (consulta.updateUsuario(modelo)) {
                consulta.llenarTabla(vista.jtUsuarios);
                JOptionPane.showMessageDialog(null, "Usuario modificado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en modificar usuario");
            }

        }

        if (e.getSource() == vista.btnEliminar) {

            //Obligatoriamente seleccionar columna
            if (vista.jtUsuarios.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }
            int fila = vista.jtUsuarios.getSelectedRow();
            long id = (long) vista.jtUsuarios.getValueAt(fila, 0);

            if (consulta.deleteUsuario(id)) {
                consulta.llenarTabla(vista.jtUsuarios);
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
