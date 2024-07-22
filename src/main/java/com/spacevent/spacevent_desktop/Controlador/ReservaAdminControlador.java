/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.Contrato;
import com.spacevent.spacevent_desktop.Modelo.ContratoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Detalle_Pago;
import com.spacevent.spacevent_desktop.Modelo.Detalle_PagoConsulta;
import com.spacevent.spacevent_desktop.Modelo.LocalConsulta;
import com.spacevent.spacevent_desktop.Modelo.Pago;
import com.spacevent.spacevent_desktop.Modelo.PagoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Reserva;
import com.spacevent.spacevent_desktop.Modelo.ReservaConsulta;
import com.spacevent.spacevent_desktop.Modelo.Usuario;
import com.spacevent.spacevent_desktop.Modelo.UsuarioConsulta;
import com.spacevent.spacevent_desktop.Vista.ListaEmpleadosVista;
import com.spacevent.spacevent_desktop.Vista.ReservaAdminVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class ReservaAdminControlador implements MouseListener {

    private final ReservaAdminVista vista;
    private final Reserva modelo;
    private final ReservaConsulta consulta;
    private final ContratoConsulta consultaContrato;
    private final Usuario modeloUsuario;
    private final UsuarioConsulta consultaUsuario;
    private final LocalConsulta consultaLocal;
    private final Pago modeloPago;
    private final PagoConsulta consultaPago;
    private final Detalle_Pago modeloDetallePago;
    private final Detalle_PagoConsulta consultaDetallePago;

    public ReservaAdminControlador(ReservaAdminVista vista, Reserva modelo, ReservaConsulta consulta, ContratoConsulta consultaContrato, Usuario modeloUsuario, UsuarioConsulta consultaUsuario, LocalConsulta consultaLocal, Pago modeloPago, PagoConsulta consultaPago, Detalle_Pago modeloDetallePago, Detalle_PagoConsulta consultaDetallePago) {
        this.vista = vista;
        this.modelo = modelo;
        this.consulta = consulta;
        this.consultaContrato = consultaContrato;
        this.modeloUsuario = modeloUsuario;
        this.consultaUsuario = consultaUsuario;
        this.consultaLocal = consultaLocal;
        this.modeloPago = modeloPago;
        this.consultaPago = consultaPago;
        this.modeloDetallePago = modeloDetallePago;
        this.consultaDetallePago = consultaDetallePago;
        //Botones
        this.vista.btnAgregarReserva.addMouseListener(this);
        this.vista.btnAsignaOne.addMouseListener(this);
        this.vista.btnCrear.addMouseListener(this);
        
    }

    public void iniciar() {
        vista.setTitle("Reserva Admin");
        vista.setLocationRelativeTo(null);
        consulta.llenarTabla(vista.jtReservaAdmin);
    }

    public ReservaAdminVista getFrm() {
        return vista;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnCrear) {

            long id_contrato = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del contrato"));
            int n_operacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de operacion del derecho a contrato"));
            String fechaUsuario = JOptionPane.showInputDialog("Introduce la fecha (yyyy-mm-dd) del abono:");
            java.sql.Date FechaAbono;
            try {
                FechaAbono = java.sql.Date.valueOf(fechaUsuario);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "La fecha debe estar en el formato yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contrato datoscontrato = consultaContrato.readContrato(id_contrato);
            String firstname = datoscontrato.getNombres();
            String lastname = datoscontrato.getApellidos();
            int precioHora = consultaLocal.readLocal(datoscontrato.getID_Local()).getPrize();
            int cantidadhoras = (int) (datoscontrato.getFinHoraReserva() - datoscontrato.getInicioHoraReserva());
            double monto = precioHora * cantidadhoras;
            java.util.Date today = Calendar.getInstance().getTime();
            java.sql.Date fecha_pago = new java.sql.Date(today.getTime());

            double monto1armada = (monto / 2) - 50;
            double monto2armada = monto / 2;

            // Asegúrate de que firstname y lastname no sean null o vacíos
            if (firstname != null && !firstname.trim().isEmpty() && lastname != null && !lastname.trim().isEmpty()) {
                // Obtener la primera letra del nombre (convertir a minúscula)
                String firstLetter = firstname.substring(0, 1).toLowerCase();

                // Obtener la primera palabra del apellido (convertir a minúscula)
                String[] lastnameParts = lastname.split("\\s+"); // Divide el apellido en partes usando espacios
                String firstWord = lastnameParts[0].toLowerCase();

                // Concatenar la primera letra del nombre y la primera palabra del apellido
                String user = firstLetter + firstWord;

                System.out.println("El nombre de usuario es: " + user);

                // Generar una contraseña aleatoria
                String pass = "123456";

                modeloUsuario.setFirstname(firstname);
                modeloUsuario.setLastname(lastname);
                modeloUsuario.setPhone(0); // Establece un valor adecuado para el teléfono
                modeloUsuario.setEmail(""); // Establece un valor adecuado para el email
                modeloUsuario.setUsername(user);
                modeloUsuario.setUserpassword(pass);
                // Crear el usuario
                long id_usuariocreado = consultaUsuario.createUsuario(modeloUsuario);
                //Crear pago
                modeloPago.setEstade("Pendiente");
                modeloPago.setTotal_monto(monto);
                modeloPago.setFecha(fecha_pago);
                long idPagocreadoLong = consultaPago.createPago(modeloPago);
                int idPagocreado = (int) idPagocreadoLong; // Conversión explícita de long a int

                // Crear DetallePago para "Pago50soles"
                Detalle_Pago detallePago1 = new Detalle_Pago();
                detallePago1.setId_pago(idPagocreado);
                detallePago1.setParte("Pago50soles");
                detallePago1.setMonto(50);
                detallePago1.setNumero_operacion(n_operacion);
                detallePago1.setFecha_pago(FechaAbono);
                detallePago1.setStatus("PAGADO");

                if (consultaDetallePago.createDetallePago(detallePago1)) {
                    System.out.println("Detalle creado");
                } else {
                    System.out.println("Error en crear detalle");
                }

                // Crear DetallePago para "Pago1raArmada"
                Detalle_Pago detallePago2 = new Detalle_Pago();
                detallePago2.setId_pago(idPagocreado);
                detallePago2.setParte("Pago1raArmada");
                detallePago2.setMonto(monto1armada);
                detallePago2.setNumero_operacion(0);
//                detallePago2.setFecha_pago(null);
                detallePago2.setStatus("PENDIENTE");

                if (consultaDetallePago.createDetallePago(detallePago2)) {
                    System.out.println("Detalle creado");
                } else {
                    System.out.println("Error en crear detalle");
                }

                // Crear DetallePago para "Pago2raArmada"
                Detalle_Pago detallePago3 = new Detalle_Pago();
                detallePago3.setId_pago(idPagocreado);
                detallePago3.setParte("Pago2raArmada");
                detallePago3.setMonto(monto2armada);
                detallePago3.setNumero_operacion(0);
//                detallePago3.setFecha_pago(null);
                detallePago3.setStatus("PENDIENTE");

                if (consultaDetallePago.createDetallePago(detallePago3)) {
                    System.out.println("Detalle creado");
                } else {
                    System.out.println("Error en crear detalle");
                }

                modelo.setID_Usuario(id_usuariocreado);
                modelo.setID_Local(datoscontrato.getID_Local());
                modelo.setStarttime((int) datoscontrato.getInicioHoraReserva());
                modelo.setEndtime((int) datoscontrato.getFinHoraReserva());
                modelo.setDates(datoscontrato.getFechaReserva());
                modelo.setId_pago(idPagocreado);
                modelo.setMount(monto);
                modelo.setId_contrato(id_contrato);
                modelo.setID_Personal(null);
                if (consulta.createReserva(modelo) != -1) {
                    JOptionPane.showMessageDialog(null, "Reserva generada con exitazo!!!!");
                    consulta.llenarTabla(vista.jtReservaAdmin);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear reserva");
                };

            } else {
                System.out.println("Nombre o apellido están vacíos o nulos.");
            }

        }

        if (e.getSource() == vista.btnAsignaOne) {

            //Obligatoriamente seleccionar columna
            if (vista.jtReservaAdmin.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }
            int fila = vista.jtReservaAdmin.getSelectedRow();
            System.out.println("La fila es: " + fila);

            // Obtener el valor de la tabla y convertirlo correctamente
            Object idValue = vista.jtReservaAdmin.getValueAt(fila, 0);
            long id_reserva;

            if (idValue instanceof Integer) {
                id_reserva = ((Integer) idValue).longValue();
            } else if (idValue instanceof Long) {
                id_reserva = (Long) idValue;
            } else {
                JOptionPane.showMessageDialog(null, "El valor de ID no es del tipo esperado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Continuar con la lógica de abrir la nueva ventana
            ListaEmpleadosVista frmListEmpleado = new ListaEmpleadosVista();
            AsignarEmpleadoAReservaControlador ctrl = new AsignarEmpleadoAReservaControlador(id_reserva, consulta, frmListEmpleado);
            ctrl.iniciar();
            frmListEmpleado.setVisible(true);
            vista.dispose();

        }
        
        if(e.getSource() == vista.btnAgregarReserva){
            long id_usuario_register = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del usuario que solicita reserva"));
            long id_contrato = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del contrato"));
            int n_operacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de operacion del derecho a contrato"));
            String fechaUsuario = JOptionPane.showInputDialog("Introduce la fecha (yyyy-mm-dd) del abono:");
            java.sql.Date FechaAbono;
            try {
                FechaAbono = java.sql.Date.valueOf(fechaUsuario);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "La fecha debe estar en el formato yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contrato datoscontrato = consultaContrato.readContrato(id_contrato);
            String firstname = datoscontrato.getNombres();
            String lastname = datoscontrato.getApellidos();
            int precioHora = consultaLocal.readLocal(datoscontrato.getID_Local()).getPrize();
            int cantidadhoras = (int) (datoscontrato.getInicioHoraReserva() - datoscontrato.getFinHoraReserva());
            double monto = precioHora * cantidadhoras;
            java.util.Date today = Calendar.getInstance().getTime();
            java.sql.Date fecha_pago = new java.sql.Date(today.getTime());
            
            double monto1armada = (monto / 2) - 50;
            double monto2armada = monto / 2;

            // Asegúrate de que firstname y lastname no sean null o vacíos
            if (firstname != null && !firstname.trim().isEmpty() && lastname != null && !lastname.trim().isEmpty()) {

                //Crear pago
                modeloPago.setEstade("Pendiente");
                modeloPago.setTotal_monto(monto);
                modeloPago.setFecha(fecha_pago);
                long idPagocreadoLong = consultaPago.createPago(modeloPago);
                int idPagocreado = (int) idPagocreadoLong; // Conversión explícita de long a int

                // Crear DetallePago para "Pago50soles"
                Detalle_Pago detallePago1 = new Detalle_Pago();
                detallePago1.setId_pago(idPagocreado);
                detallePago1.setParte("Pago50soles");
                detallePago1.setMonto(50);
                detallePago1.setNumero_operacion(n_operacion);
                detallePago1.setFecha_pago(FechaAbono);
                detallePago1.setStatus("PAGADO");

                if (consultaDetallePago.createDetallePago(detallePago1)) {
                    System.out.println("Detalle creado");
                } else {
                    System.out.println("Error en crear detalle");
                }

                // Crear DetallePago para "Pago1raArmada"
                Detalle_Pago detallePago2 = new Detalle_Pago();
                detallePago2.setId_pago(idPagocreado);
                detallePago2.setParte("Pago1raArmada");
                detallePago2.setMonto(monto1armada);
                detallePago2.setNumero_operacion(0);
//                detallePago2.setFecha_pago(null);
                detallePago2.setStatus("PENDIENTE");

                if (consultaDetallePago.createDetallePago(detallePago2)) {
                    System.out.println("Detalle creado");
                } else {
                    System.out.println("Error en crear detalle");
                }

                // Crear DetallePago para "Pago2raArmada"
                Detalle_Pago detallePago3 = new Detalle_Pago();
                detallePago3.setId_pago(idPagocreado);
                detallePago3.setParte("Pago2raArmada");
                detallePago3.setMonto(monto2armada);
                detallePago3.setNumero_operacion(0);
//                detallePago3.setFecha_pago(null);
                detallePago3.setStatus("PENDIENTE");

                if (consultaDetallePago.createDetallePago(detallePago3)) {
                    System.out.println("Detalle creado");
                } else {
                    System.out.println("Error en crear detalle");
                }

                modelo.setID_Usuario(id_usuario_register);
                modelo.setID_Local(datoscontrato.getID_Local());
                modelo.setStarttime((int) datoscontrato.getInicioHoraReserva());
                modelo.setEndtime((int) datoscontrato.getFinHoraReserva());
                modelo.setDates(datoscontrato.getFechaReserva());
                modelo.setId_pago(idPagocreado);
                modelo.setMount(monto);
                modelo.setId_contrato(id_contrato);
                modelo.setID_Personal(null);
                if (consulta.createReserva(modelo) != -1) {
                    JOptionPane.showMessageDialog(null, "Reserva generada con exitazo!!!!");
                    consulta.llenarTabla(vista.jtReservaAdmin);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear reserva");
                };

            } else {
                System.out.println("Nombre o apellido están vacíos o nulos.");
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
