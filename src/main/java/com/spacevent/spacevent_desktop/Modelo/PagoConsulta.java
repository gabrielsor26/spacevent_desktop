/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Modelo;

import java.sql.Connection;
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

/**
 *
 * @author Usuario
 */
public class PagoConsulta extends Conexion {

    public long createPago(Pago pago) {
        String sql = "INSERT INTO pago (Estade, total_monto, fecha) VALUES (?, ?, ?)";
        long generatedKey = -1; // Valor predeterminado en caso de error

        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, pago.getEstade());
            pstmt.setDouble(2, pago.getTotal_monto());
            pstmt.setDate(3, new java.sql.Date(pago.getFecha().getTime()));

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

    public Pago readPago(int id) {
        String sql = "SELECT * FROM pago WHERE ID = ?";
        Pago pago = null;
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pago = new Pago(
                        rs.getInt("ID"),
                        rs.getString("Estade"),
                        rs.getDouble("total_monto"),
                        rs.getDate("fecha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pago;
    }

    public boolean updatePago(Pago pago) {
        String sql = "UPDATE pago SET Estade = ?, total_monto = ?, fecha = ? WHERE ID = ?";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pago.getEstade());
            pstmt.setDouble(2, pago.getTotal_monto());
            pstmt.setDate(3, new java.sql.Date(pago.getFecha().getTime()));
            pstmt.setInt(4, pago.getID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePago(int id) {
        String sql = "DELETE FROM pago WHERE ID = ?";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pago> getAllPagos() {
        String sql = "SELECT * FROM pago";
        List<Pago> pagos = new ArrayList<>();
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Pago pago = new Pago(
                        rs.getInt("ID"),
                        rs.getString("Estade"),
                        rs.getDouble("total_monto"),
                        rs.getDate("fecha"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagos;
    }

    public void llenarTabla(javax.swing.JTable table) {
        List<Pago> pagos = getAllPagos();
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID",
            "Estade",
            "Total Monto",
            "Fecha"
        });

        table.setModel(modelotabla);

        int altoFila = 40;
        int[] anchos = {50, 100, 100, 100};
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

        for (Pago pago : pagos) {
            modelotabla.addRow(new Object[]{
                pago.getID(),
                pago.getEstade(),
                pago.getTotal_monto(),
                pago.getFecha()
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

}
