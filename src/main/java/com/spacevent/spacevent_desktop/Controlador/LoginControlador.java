/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.Personal;
import com.spacevent.spacevent_desktop.Modelo.PersonalConsulta;
import com.spacevent.spacevent_desktop.Vista.LoginVista;
import com.spacevent.spacevent_desktop.Vista.OpcionAdmin;
import com.spacevent.spacevent_desktop.Vista.OpcionAsesor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class LoginControlador implements MouseListener {
    
    public static long id_usuario;
    private final LoginVista vista;
    private final Personal model;
    private final PersonalConsulta consulta;

    public LoginControlador(LoginVista vista, Personal model, PersonalConsulta consulta) {
        this.vista = vista;
        this.model = model;
        this.consulta = consulta;
        //Botones
        this.vista.btnIngresar.addMouseListener(this);
    }

    public void iniciar() {
        vista.setTitle("Login");
        vista.setLocationRelativeTo(null);

    }
    
    public LoginVista getfrm(){
        return vista;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnIngresar) {

            String user = vista.txtUser.getText();
            String pass = vista.txtPass.getText();

            if (consulta.iniciarSesion(user, pass)) {
                Integer id = consulta.obtenerID(user, pass);
                String rol = consulta.readPersonal(id).getRol();
                id_usuario = id;
                if ("Admin".equals(rol)) {
                    vista.dispose();
                    OpcionAdmin frm = new OpcionAdmin();
                    frm.setVisible(true);
                    
                } else if ("Asesor".equals(rol)) {
                    vista.dispose();
                    OpcionAsesor frm = new OpcionAsesor();
                    frm.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Personal no encontrado");

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
