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
public class ContratoConsulta extends Conexion{
    
    public long createContrato(Contrato contrato) {
        String sql = "INSERT INTO contrato (Nombres, Apellidos, DNI, InicioHoraReserva, FinHoraReserva, FechaReserva, Direccion, Fecha_Contrato, ID_Local) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new Conexion().getConexion(); 
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, contrato.getNombres());
            pstmt.setString(2, contrato.getApellidos());
            pstmt.setInt(3, contrato.getDNI());
            pstmt.setLong(4, contrato.getInicioHoraReserva());
            pstmt.setLong(5, contrato.getFinHoraReserva());
            pstmt.setDate(6, new java.sql.Date(contrato.getFechaReserva().getTime()));
            pstmt.setString(7, contrato.getDireccion());
            pstmt.setDate(8, new java.sql.Date(contrato.getFecha_Contrato().getTime()));
            pstmt.setLong(9, contrato.getID_Local());

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

    public Contrato readContrato(long id) {
        String sql = "SELECT * FROM contrato WHERE id_contrato = ?";
        Contrato contrato = null;
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                contrato = new Contrato(
                        rs.getLong("id_contrato"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getInt("DNI"),
                        rs.getLong("InicioHoraReserva"),
                        rs.getLong("FinHoraReserva"),
                        rs.getDate("FechaReserva"),
                        rs.getString("Direccion"),
                        rs.getDate("Fecha_Contrato"),
                        rs.getLong("ID_Local"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrato;
    }

    public boolean updateContrato(Contrato contrato) {
        String sql = "UPDATE contrato SET Nombres = ?, Apellidos = ?, DNI = ?, InicioHoraReserva = ?, FinHoraReserva = ?, FechaReserva = ?, Direccion = ?, Fecha_Contrato = ?, ID_Local = ? WHERE id_contrato = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contrato.getNombres());
            pstmt.setString(2, contrato.getApellidos());
            pstmt.setInt(3, contrato.getDNI());
            pstmt.setLong(4, contrato.getInicioHoraReserva());
            pstmt.setLong(5, contrato.getFinHoraReserva());
            pstmt.setDate(6, new java.sql.Date(contrato.getFechaReserva().getTime()));
            pstmt.setString(7, contrato.getDireccion());
            pstmt.setDate(8, new java.sql.Date(contrato.getFecha_Contrato().getTime()));
            pstmt.setLong(9, contrato.getID_Local());
            pstmt.setLong(10, contrato.getId_contrato());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteContrato(long id) {
        String sql = "DELETE FROM contrato WHERE id_contrato = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Contrato> getAllContratos() {
        String sql = "SELECT * FROM contrato";
        List<Contrato> contratos = new ArrayList<>();
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Contrato contrato = new Contrato(
                        rs.getLong("id_contrato"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getInt("DNI"),
                        rs.getLong("InicioHoraReserva"),
                        rs.getLong("FinHoraReserva"),
                        rs.getDate("FechaReserva"),
                        rs.getString("Direccion"),
                        rs.getDate("Fecha_Contrato"),
                        rs.getLong("ID_Local"));
                contratos.add(contrato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }

    public void llenarTabla(javax.swing.JTable table) {
        List<Contrato> contratos = getAllContratos();
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID Contrato",
            "Nombres",
            "Apellidos",
            "DNI",
            "Inicio Reserva",
            "Fin Reserva",
            "Fecha Reserva",
            "Dirección",
            "Fecha Contrato",
            "ID Local"});
        table.setModel(modelotabla);

        int altoFila = 40;
        int[] anchos = {80, 100, 100, 60, 80, 80, 100, 150, 100, 80};
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

        for (Contrato contrato : contratos) {
            modelotabla.addRow(new Object[]{
                contrato.getId_contrato(),
                contrato.getNombres(),
                contrato.getApellidos(),
                contrato.getDNI(),
                contrato.getInicioHoraReserva(),
                contrato.getFinHoraReserva(),
                contrato.getFechaReserva(),
                contrato.getDireccion(),
                contrato.getFecha_Contrato(),
                contrato.getID_Local()});
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
