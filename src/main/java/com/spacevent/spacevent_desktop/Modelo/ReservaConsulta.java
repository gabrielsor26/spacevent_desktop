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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Usuario
 */
public class ReservaConsulta extends Conexion {

    public long createReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (ID_Usuario, ID_Local, starttime, endtime, dates, id_pago, mount, id_contrato, ID_Personal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        long generatedKey = -1; // Valor predeterminado en caso de error

        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros del PreparedStatement
            pstmt.setLong(1, reserva.getID_Usuario());
            pstmt.setLong(2, reserva.getID_Local());
            pstmt.setInt(3, reserva.getStarttime());
            pstmt.setInt(4, reserva.getEndtime());
            pstmt.setDate(5, new java.sql.Date(reserva.getDates().getTime()));
            pstmt.setLong(6, reserva.getId_pago());
            pstmt.setDouble(7, reserva.getMount());
            pstmt.setLong(8, reserva.getId_contrato());

            // Manejar ID_Personal nulo
            if (reserva.getID_Personal() != null) {
                pstmt.setLong(9, reserva.getID_Personal());
            } else {
                pstmt.setNull(9, Types.INTEGER);
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

    public Reserva readReserva(long id) {
        String sql = "SELECT * FROM reserva WHERE ID = ?";
        Reserva reserva = null;
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                reserva = new Reserva(
                        rs.getLong("ID"),
                        rs.getLong("ID_Usuario"),
                        rs.getLong("ID_Local"),
                        rs.getInt("starttime"),
                        rs.getInt("endtime"),
                        rs.getDate("dates"),
                        rs.getLong("id_pago"),
                        rs.getDouble("mount"),
                        rs.getLong("id_contrato"),
                        rs.getLong("ID_Personal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

    public boolean updateReserva(Reserva reserva) {
        String sql = "UPDATE reserva SET ID_Usuario = ?, ID_Local = ?, starttime = ?, endtime = ?, dates = ?, id_pago = ?, mount = ?, id_contrato = ?, ID_Personal = ? WHERE ID = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, reserva.getID_Usuario());
            pstmt.setLong(2, reserva.getID_Local());
            pstmt.setInt(3, reserva.getStarttime());
            pstmt.setInt(4, reserva.getEndtime());
            pstmt.setDate(5, new java.sql.Date(reserva.getDates().getTime()));
            pstmt.setLong(6, reserva.getId_pago());
            pstmt.setDouble(7, reserva.getMount());
            pstmt.setLong(8, reserva.getId_contrato());
            pstmt.setLong(9, reserva.getID_Personal());
            pstmt.setLong(10, reserva.getID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteReserva(long id) {
        String sql = "DELETE FROM reserva WHERE ID = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Reserva> getAllReservas() {
        String sql = "SELECT * FROM reserva";
        List<Reserva> reservas = new ArrayList<>();
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getLong("ID"),
                        rs.getLong("ID_Usuario"),
                        rs.getLong("ID_Local"),
                        rs.getInt("starttime"),
                        rs.getInt("endtime"),
                        rs.getDate("dates"),
                        rs.getLong("id_pago"),
                        rs.getDouble("mount"),
                        rs.getLong("id_contrato"),
                        rs.getLong("ID_Personal"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public void llenarComboBoxPersonales(JComboBox<Personal> comboBox) {
        List<Personal> personales = new PersonalConsulta().getAllPersonales();
        comboBox.removeAllItems(); // Limpiar el comboBox antes de llenarlo
        for (Personal personal : personales) {
            comboBox.addItem(personal);
        }
    }

    public boolean asignarPersonalAReserva(Long id, Personal personal) {
        String sql = "UPDATE reserva SET ID_Personal = ? WHERE ID = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, personal.getId());
            pstmt.setLong(2, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void llenarTabla(javax.swing.JTable table) {
        List<Reserva> reservas = getAllReservas();
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID",
            "ID_Usuario",
            "ID_Local",
            "Start Time",
            "End Time",
            "Dates",
            "ID Pago",
            "Mount",
            "ID Contrato",
            "ID Personal"});
        table.setModel(modelotabla);

        int altoFila = 40;
        int[] anchos = {50, 50, 50, 50, 50, 100, 50, 50, 50, 50};
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

        for (Reserva reserva : reservas) {
            modelotabla.addRow(new Object[]{
                reserva.getID(),
                reserva.getID_Usuario(),
                reserva.getID_Local(),
                reserva.getStarttime(),
                reserva.getEndtime(),
                reserva.getDates(),
                reserva.getId_pago(),
                reserva.getMount(),
                reserva.getId_contrato(),
                reserva.getID_Personal()});
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

        // Personalización adicional de la tabla
        table.getTableHeader().setVisible(false);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
    }

    public void filtrarPorIDPersonal(javax.swing.JTable table, long id_usuario) {
        // Obtén todas las reservas
        List<Reserva> reservas = getAllReservas();

        // Filtra las reservas según el ID_Personal
        List<Reserva> reservasFiltradas = reservas.stream()
                .filter(reserva -> reserva.getID_Personal() == id_usuario)
                .collect(Collectors.toList());

        // Configura el modelo de la tabla
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID",
            "ID_Usuario",
            "ID_Local",
            "Start Time",
            "End Time",
            "Dates",
            "ID Pago",
            "Mount",
            "ID Contrato",
            "ID Personal"});
        table.setModel(modelotabla);

        int altoFila = 40;
        int[] anchos = {50, 50, 50, 50, 50, 100, 50, 50, 50, 50};
        table.setRowHeight(altoFila);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        // Centrar texto
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Agrega las reservas filtradas al modelo de la tabla
        for (Reserva reserva : reservasFiltradas) {
            modelotabla.addRow(new Object[]{
                reserva.getID(),
                reserva.getID_Usuario(),
                reserva.getID_Local(),
                reserva.getStarttime(),
                reserva.getEndtime(),
                reserva.getDates(),
                reserva.getId_pago(),
                reserva.getMount(),
                reserva.getId_contrato(),
                reserva.getID_Personal()});
        }

        // Visualizar columnas
        int[] columnasSeleccionadas = {0,9}; // Columnas seleccionadas por el usuario que no se verán

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
