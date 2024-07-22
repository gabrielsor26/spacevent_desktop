/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacevent.spacevent_desktop.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class LocalConsulta extends Conexion {

    // Método para crear un nuevo Local y retornar el ID generado
    public long createLocal(Local local) {
        String sql = "INSERT INTO locales (Descrip, Direction, Prize, Imageroute) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, local.getDescrip());
            pstmt.setString(2, local.getDirection());
            pstmt.setInt(3, local.getPrize());
            pstmt.setString(4, local.getImageroute());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1); // Obtiene la clave generada
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Devuelve -1 si la inserción falla o no se obtiene la clave
    }

// Método para leer un Local por ID
    public Local readLocal(long id) {
        String sql = "SELECT * FROM locales WHERE ID = ?";
        Local local = null;
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                local = new Local(
                        rs.getLong("ID"),
                        rs.getString("Descrip"),
                        rs.getString("Direction"),
                        rs.getInt("Prize"),
                        rs.getString("Imageroute"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return local;
    }

// Método para actualizar un Local
    public boolean updateLocal(Local local) {
        String sql = "UPDATE locales SET Descrip = ?, Direction = ?, Prize = ?, Imageroute = ? WHERE ID = ?";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, local.getDescrip());
            pstmt.setString(2, local.getDirection());
            pstmt.setInt(3, local.getPrize());
            pstmt.setString(4, local.getImageroute());
            pstmt.setLong(5, local.getID());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

// Método para eliminar un Local por ID
    public boolean deleteLocal(long id) {
        String sql = "DELETE FROM locales WHERE ID = ?";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

// Método para obtener todos los Locales
    public List<Local> getAllLocales() {
        String sql = "SELECT * FROM locales";
        List<Local> locales = new ArrayList<>();
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Local local = new Local(
                        rs.getLong("ID"),
                        rs.getString("Descrip"),
                        rs.getString("Direction"),
                        rs.getInt("Prize"),
                        rs.getString("Imageroute"));
                locales.add(local);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locales;
    }

// Método para llenar una JTable con datos de Locales
    public void llenarTabla(javax.swing.JTable table) {
        List<Local> locales = getAllLocales();
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID",
            "Descripción",
            "Dirección",
            "Premio",
            "Ruta Imagen"
        });

        table.setModel(modelotabla);

        int altoFila = 40;
        int[] anchos = {50, 150, 150, 100, 150};
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

        for (Local local : locales) {
            modelotabla.addRow(new Object[]{
                local.getID(),
                local.getDescrip(),
                local.getDirection(),
                local.getPrize(),
                local.getImageroute()
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
