/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Controlador;

import com.spacevent.spacevent_desktop.Modelo.DevolucionConsulta;
import com.spacevent.spacevent_desktop.Vista.DevolucionAdmin;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class DevolucionControlador implements MouseListener{
    
    private final DevolucionConsulta consulta;
    private final DevolucionAdmin vista;

    public DevolucionControlador(DevolucionConsulta consulta, DevolucionAdmin vista) {
        this.consulta = consulta;
        this.vista = vista;
        //Botones
        this.vista.btnAbonar.addMouseListener(this);
    }
    
    public void iniciar(){
        vista.setTitle("Devolucion vista");
        vista.setLocationRelativeTo(null);
        consulta.llenarTabla(vista.jtDevolucion);
    }
    
    public DevolucionAdmin getFrm(){
        return vista;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource()==vista.btnAbonar){
            if (vista.jtDevolucion.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el usuario que desea modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del manejador de eventos si no se selecciona una fila
            }

            // Obtén la fila seleccionada en la tabla
            int fila = vista.jtDevolucion.getSelectedRow();

// Asegúrate de que una fila esté seleccionada
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

// Obtén el valor de la columna 0 en la fila seleccionada
            Object idPagoObj = vista.jtDevolucion.getValueAt(fila, 0);
            int id_detalle_pago = 0;

            try {
                // Verifica el tipo de objeto y conviértelo a int
                if (idPagoObj instanceof Number) {
                    // Si el objeto es una instancia de Number (Long, Integer, etc.), haz un casting a int
                    id_detalle_pago = ((Number) idPagoObj).intValue();
                } else if (idPagoObj instanceof String) {
                    // Si el objeto es una cadena, intenta convertirlo a int
                    id_detalle_pago = Integer.parseInt((String) idPagoObj);
                } else {
                    throw new ClassCastException("El tipo de dato de la columna ID Pago no es válido.");
                }
            } catch (NumberFormatException | ClassCastException ex) {
                // Maneja errores de formato y de tipo de dato
                JOptionPane.showMessageDialog(null, "Error al obtener el ID del detalle: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String n_operacionStr;
            int n_operacion = 0;
            boolean validOperacion = false;
            while (!validOperacion) {
                n_operacionStr = JOptionPane.showInputDialog("Ingrese el número de operación del derecho a contrato (8 dígitos numéricos):");
                if (n_operacionStr != null && n_operacionStr.matches("\\d{8}")) {
                    n_operacion = Integer.parseInt(n_operacionStr);
                    validOperacion = true;
                } else {
                    JOptionPane.showMessageDialog(null, "El número de operación debe tener exactamente 8 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            String fechaUsuario = JOptionPane.showInputDialog("Introduce la fecha (yyyy-mm-dd) del abono:");
            java.sql.Date FechaAbono;
            try {
                FechaAbono = java.sql.Date.valueOf(fechaUsuario);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "La fecha debe estar en el formato yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(consulta.actualizarEstadoAbonado(n_operacion, n_operacion, FechaAbono)){
                JOptionPane.showMessageDialog(null, "Devolucion realizada");
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar abono");
            };
            
            
            
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
