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

public class PersonalConsulta extends Conexion {

    public boolean createPersonal(Personal personal) {
        String sql = "INSERT INTO personal (username, userpassword, rol) VALUES (?, ?, ?)";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, personal.getUsername());
            pstmt.setString(2, personal.getUserpassword());
            pstmt.setString(3, personal.getRol());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Personal readPersonal(long id) {
        String sql = "SELECT * FROM personal WHERE id = ?";
        Personal personal = null;
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                personal = new Personal(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("userpassword"),
                        rs.getString("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personal;
    }

    public boolean updatePersonal(Personal personal) {
        String sql = "UPDATE personal SET username = ?, userpassword = ?, rol = ? WHERE id = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, personal.getUsername());
            pstmt.setString(2, personal.getUserpassword());
            pstmt.setString(3, personal.getRol());
            pstmt.setLong(4, personal.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePersonal(long id) {
        String sql = "DELETE FROM personal WHERE id = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Personal> getAllPersonales() {
        String sql = "SELECT * FROM personal";
        List<Personal> personales = new ArrayList<>();
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Personal personal = new Personal(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("userpassword"),
                        rs.getString("rol"));
                personales.add(personal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personales;
    }

    public void llenarTabla(javax.swing.JTable table) {
        List<Personal> personales = getAllPersonales();
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID",
            "Username",
            "Userpassword",
            "Rol"});
        table.setModel(modelotabla);

        int altoFila = 40;
        int[] anchos = {50, 50, 50, 50};
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

        for (Personal personal : personales) {
            modelotabla.addRow(new Object[]{
                personal.getId(),
                personal.getUsername(),
                personal.getUserpassword(),
                personal.getRol()});
        }

        //Visualizar Columnas
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

        // Personalización adicional de la tabla
        table.getTableHeader().setVisible(false);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
    }

    public boolean iniciarSesion(String user, String pass) {
        String sql = "SELECT ID FROM personal WHERE Username = ? AND Userpassword = ?;";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Integer id_logueado = rs.getInt("ID");
                return id_logueado != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer obtenerID(String user, String pass) {
        String sql = "SELECT ID FROM personal WHERE Username = ? AND Userpassword = ?;";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
