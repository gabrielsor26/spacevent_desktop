/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.Contrato;
import com.spacevent.spacevent_desktop.Modelo.ContratoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Detalle_Pago;
import com.spacevent.spacevent_desktop.Modelo.Detalle_PagoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Devolucion;
import com.spacevent.spacevent_desktop.Modelo.DevolucionConsulta;
import com.spacevent.spacevent_desktop.Modelo.LocalConsulta;
import com.spacevent.spacevent_desktop.Modelo.Pago;
import com.spacevent.spacevent_desktop.Modelo.PagoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Reserva;
import com.spacevent.spacevent_desktop.Modelo.ReservaConsulta;
import com.spacevent.spacevent_desktop.Modelo.Usuario;
import com.spacevent.spacevent_desktop.Modelo.UsuarioConsulta;
import com.spacevent.spacevent_desktop.Vista.PagoReservaVista;
import com.spacevent.spacevent_desktop.Vista.ReservaAsesorVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class ReservaAsesorControlador implements MouseListener {

    private final long id_usuario;
    private final ReservaAsesorVista vista;
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
    private final Devolucion modeloDevolucion;
    private final DevolucionConsulta consultaDevolucion;

    public ReservaAsesorControlador(long id_usuario, ReservaAsesorVista vista, Reserva modelo, ReservaConsulta consulta, ContratoConsulta consultaContrato, Usuario modeloUsuario, UsuarioConsulta consultaUsuario, LocalConsulta consultaLocal, Pago modeloPago, PagoConsulta consultaPago, Detalle_Pago modeloDetallePago, Detalle_PagoConsulta consultaDetallePago, Devolucion modeloDevolucion, DevolucionConsulta consultaDevolucion) {
        this.id_usuario = id_usuario;
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
        this.modeloDevolucion = modeloDevolucion;
        this.consultaDevolucion = consultaDevolucion;
        //Botones
        this.vista.btnCrear.addMouseListener(this);
        this.vista.btnAgregarReserva.addMouseListener(this);
        this.vista.btnPagar.addMouseListener(this);
        this.vista.btnDevolucion.addMouseListener(this);
    }

    public void iniciar() {
        vista.setTitle("Reserva Asesor");
        vista.setLocationRelativeTo(null);
        consulta.filtrarPorIDPersonal(vista.jtReserva, id_usuario);

    }

    public ReservaAsesorVista getfrm() {
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
                modelo.setID_Personal(id_usuario);
                if (consulta.createReserva(modelo) != -1) {
                    JOptionPane.showMessageDialog(null, "Reserva generada con exitazo!!!!");
                    consulta.filtrarPorIDPersonal(vista.jtReserva, id_usuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear reserva");
                };

            } else {
                System.out.println("Nombre o apellido están vacíos o nulos.");
            }

        }

        if (e.getSource() == vista.btnAgregarReserva) {

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
                modelo.setID_Personal(id_usuario);
                if (consulta.createReserva(modelo) != -1) {
                    JOptionPane.showMessageDialog(null, "Reserva generada con exitazo!!!!");
                    consulta.filtrarPorIDPersonal(vista.jtReserva, id_usuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear reserva");
                };

            } else {
                System.out.println("Nombre o apellido están vacíos o nulos.");
            }
        }

        if (e.getSource() == vista.btnPagar) {

            if (vista.jtReserva.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }

            int fila = vista.jtReserva.getSelectedRow();
            Long id_pagoLong = (Long) vista.jtReserva.getValueAt(fila, 6);
            int id_pago = id_pagoLong.intValue();

            vista.dispose();

            PagoControlador ctrl = new PagoControlador(id_pago, new PagoReservaVista(), consultaDetallePago);
            ctrl.iniciar();
            ctrl.getFrm().setVisible(true);

        }

        if (e.getSource() == vista.btnDevolucion) {

            if (vista.jtReserva.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }

            int fila = vista.jtReserva.getSelectedRow();
            Long id_pagoLong = (Long) vista.jtReserva.getValueAt(fila, 6);
            int id_pago = id_pagoLong.intValue();
            Long id_reserva = (Long) vista.jtReserva.getValueAt(fila, 0);
            double monto = consultaPago.readPago(id_pago).getTotal_monto();
            int caso = consultaDetallePago.verificarEstadoPagos(id_pago);

            double montoDevolver;
            String mensaje;
            if (caso == 1) {
                JOptionPane.showMessageDialog(null, "No se realizará ninguna devolución.", "Información", JOptionPane.INFORMATION_MESSAGE);
                consulta.deleteReserva(id_reserva);
                consulta.filtrarPorIDPersonal(vista.jtReserva, id_usuario);
                return; // Salir del manejador de eventos si no se debe crear una devolución
            } else if (caso == 2) {
                montoDevolver = monto / 4 - 25;
                mensaje = "Monto a devolver: " + montoDevolver;
            } else if (caso == 3) {
                montoDevolver = monto / 2 - 25;
                mensaje = "Monto a devolver: " + montoDevolver;
            } else {
                throw new IllegalArgumentException("Valor de caso inesperado: " + caso);
            }

            modeloDevolucion.setID_Reserva(id_reserva);
            modeloDevolucion.setEstade("PENDIENTE");
            modeloDevolucion.setMontoDevolver(montoDevolver);

// Guardar la devolución en la base de datos
            long devolucionId = consultaDevolucion.createDevolucion(modeloDevolucion);

// Verificar si la devolución se ha creado correctamente
            if (devolucionId == -1) {
                JOptionPane.showMessageDialog(null, "Error al crear la devolución.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Devolución creada con éxito. ID: " + devolucionId + "\n" + mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
