/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class DevolucionConsulta extends Conexion {

    public long createDevolucion(Devolucion devolucion) {
        String sql = "INSERT INTO devolucion (ID_Reserva, Estade, montoDevolver, numeroOperacion, fechaAbono) VALUES (?, ?, ?, ?, ?)";
        long generatedKey = -1; // Valor predeterminado en caso de error

        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setLong(1, devolucion.getID_Reserva());
            pstmt.setString(2, devolucion.getEstade());
            pstmt.setDouble(3, devolucion.getMontoDevolver());

            // Manejar el caso en que numeroOperacion puede ser nulo
            if (devolucion.getNumeroOperacion() != null) {
                pstmt.setInt(4, devolucion.getNumeroOperacion());
            } else {
                pstmt.setNull(4, java.sql.Types.INTEGER);
            }

            // Manejar el caso en que fechaAbono puede ser nulo
            if (devolucion.getFechaAbono() != null) {
                pstmt.setDate(5, new java.sql.Date(devolucion.getFechaAbono().getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }

            // Ejecutar la inserción
            pstmt.executeUpdate();

            // Obtener la clave primaria generada
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedKey = rs.getLong(1); // Obtener la clave primaria generada
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedKey; // Devolver la clave primaria generada o -1 en caso de error
    }

    // Método para leer una devolución por ID
    public Devolucion readDevolucion(long id) {
        String sql = "SELECT * FROM devolucion WHERE ID = ?";
        Devolucion devolucion = null;
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                devolucion = new Devolucion(
                        rs.getLong("ID"),
                        rs.getLong("ID_Reserva"),
                        rs.getString("Estade"),
                        rs.getDouble("montoDevolver"),
                        rs.getInt("numeroOperacion"),
                        rs.getDate("fechaAbono"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devolucion;
    }

    // Método para actualizar una devolución
    public boolean updateDevolucion(Devolucion devolucion) {
        String sql = "UPDATE devolucion SET ID_Reserva = ?, Estade = ?, montoDevolver = ?, numeroOperacion = ?, fechaAbono = ? WHERE ID = ?";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, devolucion.getID_Reserva());
            pstmt.setString(2, devolucion.getEstade());
            pstmt.setDouble(3, devolucion.getMontoDevolver());
            pstmt.setInt(4, devolucion.getNumeroOperacion());
            pstmt.setDate(5, new java.sql.Date(devolucion.getFechaAbono().getTime()));
            pstmt.setLong(6, devolucion.getID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar una devolución
    public boolean deleteDevolucion(long id) {
        String sql = "DELETE FROM devolucion WHERE ID = ?";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las devoluciones
    public List<Devolucion> getAllDevoluciones() {
        String sql = "SELECT * FROM devolucion";
        List<Devolucion> devoluciones = new ArrayList<>();
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Devolucion devolucion = new Devolucion(
                        rs.getLong("ID"),
                        rs.getLong("ID_Reserva"),
                        rs.getString("Estade"),
                        rs.getDouble("montoDevolver"),
                        rs.getInt("numeroOperacion"),
                        rs.getDate("fechaAbono"));
                devoluciones.add(devolucion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devoluciones;
    }

    // Método para llenar la tabla con devoluciones
    public void llenarTabla(javax.swing.JTable table) {
        List<Devolucion> devoluciones = getAllDevoluciones();
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID",
            "ID Reserva",
            "Estade",
            "Monto Devolver",
            "Número Operación",
            "Fecha Abono"
        });

        table.setModel(modelotabla);

        int altoFila = 40;
        int[] anchos = {50, 100, 100, 100, 100, 100};
        table.setRowHeight(altoFila);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        // Centrar Texto
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        for (Devolucion devolucion : devoluciones) {
            modelotabla.addRow(new Object[]{
                devolucion.getID(),
                devolucion.getID_Reserva(),
                devolucion.getEstade(),
                devolucion.getMontoDevolver(),
                devolucion.getNumeroOperacion(),
                devolucion.getFechaAbono()
            });
        }

        // Visualizar Columnas
        int[] columnasSeleccionadas = {0}; // Columnas seleccionadas por el usuario que no se verán

        // Luego, aplicamos las operaciones a las columnas seleccionadas
        TableColumnModel columnModel = table.getColumnModel();

        for (int columnIndex : columnasSeleccionadas) {
            TableColumn idColumn = columnModel.getColumn(columnIndex);
            idColumn.setMinWidth(0);
            idColumn.setMaxWidth(0);
            idColumn.setPreferredWidth(0);
            idColumn.setResizable(false);
        }

        table.getTableHeader().setVisible(true);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
    }
    
    public boolean actualizarEstadoAbonado(long idDevolucion, int nOperacion, Date fechaAbono) {
    String sql = "UPDATE devolucion SET Estade = ?, numeroOperacion = ?, fechaAbono = ? WHERE ID = ?";
    try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "ABONADO");
        pstmt.setInt(2, nOperacion);
        
        // Manejar el caso en que fechaAbono puede ser nulo
        if (fechaAbono != null) {
            pstmt.setDate(3, new java.sql.Date(fechaAbono.getTime()));
        } else {
            pstmt.setNull(3, java.sql.Types.DATE);
        }
        
        pstmt.setLong(4, idDevolucion);
        int rowsAffected = pstmt.executeUpdate();
        
        // Verificar si se actualizó al menos una fila
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
