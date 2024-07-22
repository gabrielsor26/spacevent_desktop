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

public class UsuarioConsulta extends Conexion {

    public long createUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (firstname, lastname, phone, email, username, userpassword) VALUES (?, ?, ?, ?, ?, ?)";
        long generatedKey = -1; // Valor predeterminado en caso de error

        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, usuario.getFirstname());
            pstmt.setString(2, usuario.getLastname());
            pstmt.setInt(3, usuario.getPhone());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getUsername());
            pstmt.setString(6, usuario.getUserpassword());

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

    public Usuario readUsuario(long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("phone"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("userpassword"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean updateUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET firstname = ?, lastname = ?, phone = ?, email = ?, username = ?, userpassword = ? WHERE id = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getFirstname());
            pstmt.setString(2, usuario.getLastname());
            pstmt.setInt(3, usuario.getPhone());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getUsername());
            pstmt.setString(6, usuario.getUserpassword());
            pstmt.setLong(7, usuario.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUsuario(long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> getAllUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("phone"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("userpassword"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void llenarTabla(javax.swing.JTable table) {
        List<Usuario> usuarios = getAllUsuarios();
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID",
            "Firstname",
            "Lastname",
            "Phone",
            "Email",
            "Username",
            "Userpassword"});
        table.setModel(modelotabla);

        int altoFila = 40;
        int[] anchos = {50, 50, 50, 50, 50, 50, 50};
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

        for (Usuario usuario : usuarios) {
            modelotabla.addRow(new Object[]{
                usuario.getId(),
                usuario.getFirstname(),
                usuario.getLastname(),
                usuario.getPhone(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getUserpassword()});
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
}
