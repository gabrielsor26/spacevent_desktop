/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.Contrato;
import com.spacevent.spacevent_desktop.Modelo.ContratoConsulta;
import com.spacevent.spacevent_desktop.Modelo.LocalConsulta;
import com.spacevent.spacevent_desktop.Modelo.Visita;
import com.spacevent.spacevent_desktop.Modelo.VisitaConsulta;
import com.spacevent.spacevent_desktop.Vista.VisitaAsesorVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class VisitaAsesorControlador implements MouseListener {

    private final int id_usuario;
    private final Visita modelo;
    private final VisitaAsesorVista vista;
    private final VisitaConsulta consulta;
    private final Contrato modeloContrato;
    private final ContratoConsulta consultaContrato;
    private final LocalConsulta consultaLocal;

    public VisitaAsesorControlador(int id_usuario, Visita modelo, VisitaAsesorVista vista, VisitaConsulta consulta, Contrato modeloContrato, ContratoConsulta consultaContrato, LocalConsulta consultaLocal) {
        this.id_usuario = id_usuario;
        this.modelo = modelo;
        this.vista = vista;
        this.consulta = consulta;
        this.modeloContrato = modeloContrato;
        this.consultaContrato = consultaContrato;
        this.consultaLocal = consultaLocal;
        //Botones
        this.vista.btnAtras.addMouseListener(this);
        this.vista.btnPendiente.addMouseListener(this);
        this.vista.btnCancelado.addMouseListener(this);
        this.vista.btnVisitaProgramada.addMouseListener(this);
        this.vista.btnVisitaRealizada.addMouseListener(this);
        this.vista.btnEnviarContrato.addMouseListener(this);
        this.vista.btnEliminar.addMouseListener(this);
        this.vista.btnEnviarContrato.addMouseListener(this);
    }

    public void iniciar() {
        vista.setTitle("Visita Asesor");
        vista.setLocationRelativeTo(null);
        consulta.llenarTablaPorAsesor(vista.jtVisitante, id_usuario);
    }

    public VisitaAsesorVista getfrm() {
        return vista;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnPendiente) {

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

            if (consulta.updateVisitaEstade("Pendiente", id_visita)) {

            } else {
                JOptionPane.showMessageDialog(null, "Error al cambiar de Estado");
            }
            consulta.llenarTablaPorAsesor(vista.jtVisitante, id_usuario);

        }

        if (e.getSource() == vista.btnCancelado) {

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

            if (consulta.updateVisitaEstade("Cancelado", id_visita)) {

            } else {
                JOptionPane.showMessageDialog(null, "Error al cambiar de Estado");
            }
            consulta.llenarTablaPorAsesor(vista.jtVisitante, id_usuario);

        }

        if (e.getSource() == vista.btnVisitaProgramada) {

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

            if (consulta.updateVisitaEstade("VisitaProgramada", id_visita)) {

            } else {
                JOptionPane.showMessageDialog(null, "Error al cambiar de Estado");
            }
            consulta.llenarTablaPorAsesor(vista.jtVisitante, id_usuario);

        }

        if (e.getSource() == vista.btnVisitaRealizada) {

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

            if (consulta.updateVisitaEstade("VisitaRealizada", id_visita)) {

            } else {
                JOptionPane.showMessageDialog(null, "Error al cambiar de Estado");
            }
            consulta.llenarTablaPorAsesor(vista.jtVisitante, id_usuario);

        }

        if (e.getSource() == vista.btnEliminar) {

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

            if (consulta.deleteVisita(id_visita)) {
                consulta.llenarTablaPorAsesor(vista.jtVisitante, id_usuario);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en eliminar registro");
            }

        }

        if (e.getSource() == vista.btnEnviarContrato) {
            // Validar selección de fila
            if (vista.jtVisitante.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea enviar mail.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int fila = vista.jtVisitante.getSelectedRow();
            System.out.println("La fila es: " + fila);

            // Obtener el valor de la columna y convertirlo correctamente
            Object idValue = vista.jtVisitante.getValueAt(fila, 4);
            long IDLocal;
            if (idValue instanceof Integer) {
                IDLocal = ((Integer) idValue).longValue();
            } else if (idValue instanceof Long) {
                IDLocal = (Long) idValue;
            } else {
                JOptionPane.showMessageDialog(null, "El valor de ID no es del tipo esperado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtiene la fecha actual
            // Obtiene la fecha actual y la convierte a java.sql.Date
            java.util.Date today = Calendar.getInstance().getTime();
            java.sql.Date FechaContrato = new java.sql.Date(today.getTime());

            // Entrada del usuario con validación
            String Nombres = JOptionPane.showInputDialog("Ingrese los nombres");
            if (Nombres == null || Nombres.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los nombres no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String Apellidos = JOptionPane.showInputDialog("Ingrese los apellidos");
            if (Apellidos == null || Apellidos.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los apellidos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int DNI;
            try {
                DNI = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DNI"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DNI debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            long InicioReserva;
            try {
                InicioReserva = Long.parseLong(JOptionPane.showInputDialog("Ingrese la hora de inicio (en formato 24h)"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La hora de inicio debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            long FinReserva;
            try {
                FinReserva = Long.parseLong(JOptionPane.showInputDialog("Ingrese la hora de fin (en formato 24h)"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La hora de fin debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String fechaUsuario = JOptionPane.showInputDialog("Introduce la fecha (yyyy-mm-dd):");
            java.sql.Date FechaReserva;
            try {
                FechaReserva = java.sql.Date.valueOf(fechaUsuario);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "La fecha debe estar en el formato yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String Direccion = JOptionPane.showInputDialog("Ingrese la dirección del cliente");
            if (Direccion == null || Direccion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La dirección no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String Email = JOptionPane.showInputDialog("Ingrese el email el cual desea enviar el contrato.");
            if (Email == null || Nombres.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "EL email no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Configurar el modeloContrato
            modeloContrato.setNombres(Nombres);
            modeloContrato.setApellidos(Apellidos);
            modeloContrato.setDNI(DNI);
            modeloContrato.setInicioHoraReserva(InicioReserva);
            modeloContrato.setFinHoraReserva(FinReserva);
            modeloContrato.setFechaReserva(FechaReserva);
            modeloContrato.setDireccion(Direccion);
            modeloContrato.setFecha_Contrato(FechaContrato);
            modeloContrato.setID_Local(IDLocal);

            try {
                long id_contrato = consultaContrato.createContrato(modeloContrato);

                // Leer los datos del contrato
                String name = consultaContrato.readContrato(id_contrato).getNombres();
                String lastname = consultaContrato.readContrato(id_contrato).getApellidos();
                String dni = String.valueOf(consultaContrato.readContrato(id_contrato).getDNI());
                String direccioncliente = consultaContrato.readContrato(id_contrato).getDireccion();
                String hI = String.valueOf(consultaContrato.readContrato(id_contrato).getInicioHoraReserva());
                String hF = String.valueOf(consultaContrato.readContrato(id_contrato).getFinHoraReserva());
                String day = consultaContrato.readContrato(id_contrato).getFechaReserva().toString();
                String ubicacion = consultaLocal.readLocal(IDLocal).getDirection();

                int precioHora = consultaLocal.readLocal(IDLocal).getPrize();
                int cantidadhoras = (int) (FinReserva - InicioReserva);
                double monto = precioHora * cantidadhoras;

                consulta.enviarmail(
                        Email,
                        name,
                        lastname,
                        dni,
                        direccioncliente,
                        hI,
                        hF,
                        day,
                        ubicacion,
                        monto,
                        id_contrato);

                JOptionPane.showMessageDialog(null, "Operación realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
