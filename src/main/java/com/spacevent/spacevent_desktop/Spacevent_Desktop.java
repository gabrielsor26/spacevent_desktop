
package com.spacevent.spacevent_desktop;


import com.spacevent.spacevent_desktop.Controlador.LoginControlador;
import com.spacevent.spacevent_desktop.Modelo.Personal;
import com.spacevent.spacevent_desktop.Modelo.PersonalConsulta;

import com.spacevent.spacevent_desktop.Vista.LoginVista;
//gg

public class Spacevent_Desktop {

    public static void main(String[] args) {
        
        //usuario
        System.out.println("HOLA MUNDO");
//        Usuario modelo = new Usuario();
//        UsuarioConsulta consulta = new UsuarioConsulta();
//        UsuariosVista frm = new UsuariosVista();
//        UsuarioControlador ctrl = new UsuarioControlador(modelo, consulta, frm);
//        ctrl.iniciar();
//        frm.setVisible(true);
        
        
        //trabajador-personal
//        Personal modelo2 = new Personal();
//        PersonalConsulta consulta2 = new PersonalConsulta();
//        TrabajadoresVista frm2 = new TrabajadoresVista();
//        PersonalControlador ctrl2 = new PersonalControlador(modelo2, consulta2, frm2);
//        ctrl2.iniciar();
//        frm2.setVisible(true);

        //Visitante
//        Visita modelo = new Visita();
//        VisitaConsulta consulta = new VisitaConsulta();
//        VisitaAdminVista vista = new VisitaAdminVista();
//        VisitaAdminControlador ctrl = new VisitaAdminControlador(modelo, consulta, vista);
//        ctrl.iniciar();
//        vista.setVisible(true);
        
          //Login
//          LoginVista frm = new LoginVista();
//          Personal model = new Personal();
//          PersonalConsulta consulta = new PersonalConsulta();
//          
//          LoginControlador ctrl = new LoginControlador(frm, model, consulta);
//          ctrl.iniciar();
//          frm.setVisible(true);
          
          LoginControlador ctrl = new LoginControlador(new LoginVista(), new Personal(), new PersonalConsulta());
          ctrl.iniciar();
          ctrl.getfrm().setVisible(true);
        
        
        
        
    }
}
