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
public class Detalle_PagoConsulta extends Conexion {

    public boolean createDetallePago(Detalle_Pago detallePago) {
        String sql = "INSERT INTO detalle_pago (id_pago, parte, monto, numero_operacion, fecha_pago, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, detallePago.getId_pago());
            pstmt.setString(2, detallePago.getParte());
            pstmt.setDouble(3, detallePago.getMonto());
            pstmt.setInt(4, detallePago.getNumero_operacion());

            if (detallePago.getFecha_pago() != null) {
                pstmt.setDate(5, new java.sql.Date(detallePago.getFecha_pago().getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }

            pstmt.setString(6, detallePago.getStatus());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Detalle_Pago readDetallePago(int id_detalle) {
        String sql = "SELECT * FROM detalle_pago WHERE id_detalle = ?";
        Detalle_Pago detallePago = null;
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_detalle);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                detallePago = new Detalle_Pago(
                        rs.getInt("id_detalle"),
                        rs.getInt("id_pago"),
                        rs.getString("parte"),
                        rs.getDouble("monto"),
                        rs.getInt("numero_operacion"),
                        rs.getDate("fecha_pago"),
                        rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallePago;
    }

    public boolean updateDetallePago(Detalle_Pago detallePago) {
        String sql = "UPDATE detalle_pago SET id_pago = ?, parte = ?, monto = ?, numero_operacion = ?, fecha_pago = ?, status = ? WHERE id_detalle = ?";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, detallePago.getId_pago());
            pstmt.setString(2, detallePago.getParte());
            pstmt.setDouble(3, detallePago.getMonto());
            pstmt.setInt(4, detallePago.getNumero_operacion());

            if (detallePago.getFecha_pago() != null) {
                pstmt.setDate(5, new java.sql.Date(detallePago.getFecha_pago().getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }

            pstmt.setString(6, detallePago.getStatus());
            pstmt.setInt(7, detallePago.getId_detalle());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean pagar(int id_detalle, int numero_operacion, java.util.Date fecha_abono) {
        String sql = "UPDATE detalle_pago SET numero_operacion = ?, fecha_pago = ?, status = 'PAGADO' WHERE id_detalle = ?";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numero_operacion);

            if (fecha_abono != null) {
                pstmt.setDate(2, new java.sql.Date(fecha_abono.getTime()));
            } else {
                pstmt.setNull(2, java.sql.Types.DATE);
            }

            pstmt.setInt(3, id_detalle);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDetallePago(int id_detalle) {
        String sql = "DELETE FROM detalle_pago WHERE id_detalle = ?";
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_detalle);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Detalle_Pago> getAllDetallePagos() {
        String sql = "SELECT * FROM detalle_pago";
        List<Detalle_Pago> detallePagos = new ArrayList<>();
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Detalle_Pago detallePago = new Detalle_Pago(
                        rs.getInt("id_detalle"),
                        rs.getInt("id_pago"),
                        rs.getString("parte"),
                        rs.getDouble("monto"),
                        rs.getInt("numero_operacion"),
                        rs.getDate("fecha_pago"),
                        rs.getString("status"));
                detallePagos.add(detallePago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallePagos;
    }

    public void llenarTabla(javax.swing.JTable table) {
        List<Detalle_Pago> detallePagos = getAllDetallePagos();
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID Detalle",
            "ID Pago",
            "Parte",
            "Monto",
            "Número Operación",
            "Fecha Pago",
            "Status"
        });

        table.setModel(modelotabla);

        int altoFila = 40;
        int[] anchos = {50, 50, 100, 100, 100, 100, 100};
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

        for (Detalle_Pago detallePago : detallePagos) {
            modelotabla.addRow(new Object[]{
                detallePago.getId_detalle(),
                detallePago.getId_pago(),
                detallePago.getParte(),
                detallePago.getMonto(),
                detallePago.getNumero_operacion(),
                detallePago.getFecha_pago(),
                detallePago.getStatus()
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

    public void llenarTablaPorIdPago(javax.swing.JTable table, int idPagoFiltro) {
        String sql = "SELECT * FROM detalle_pago WHERE id_pago = ?";
        List<Detalle_Pago> detallePagos = new ArrayList<>();
        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPagoFiltro);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Detalle_Pago detallePago = new Detalle_Pago(
                            rs.getInt("id_detalle"),
                            rs.getInt("id_pago"),
                            rs.getString("parte"),
                            rs.getDouble("monto"),
                            rs.getInt("numero_operacion"),
                            rs.getDate("fecha_pago"),
                            rs.getString("status"));
                    detallePagos.add(detallePago);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Configurar el modelo de la tabla
        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
            "ID Detalle",
            "ID Pago",
            "Parte",
            "Monto",
            "Número Operación",
            "Fecha Pago",
            "Status"
        });

        table.setModel(modelotabla);

        int altoFila = 60;
        int[] anchos = {50, 50, 100, 100, 100, 100, 100};
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

        // Añadir filas al modelo de la tabla
        for (Detalle_Pago detallePago : detallePagos) {
            modelotabla.addRow(new Object[]{
                detallePago.getId_detalle(),
                detallePago.getId_pago(),
                detallePago.getParte(),
                detallePago.getMonto(),
                detallePago.getNumero_operacion(),
                detallePago.getFecha_pago(),
                detallePago.getStatus()
            });
        }

        // Visualizar Columnas
        int[] columnasSeleccionadas = {}; // Columnas seleccionadas por el usuario que no se verán

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

    public int verificarEstadoPagos(int idPago) {
        String sql = "SELECT parte, status FROM detalle_pago WHERE id_pago = ?";
        int estado = 0;

        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPago);
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean pago50soles = false;
                boolean pago1raArmada = false;
                boolean pago2raArmada = false;

                while (rs.next()) {
                    String parte = rs.getString("parte");
                    String status = rs.getString("status");

                    if ("PAGADO".equals(status)) {
                        switch (parte) {
                            case "Pago50soles":
                                pago50soles = true;
                                break;
                            case "Pago1raArmada":
                                pago1raArmada = true;
                                break;
                            case "Pago2raArmada":
                                pago2raArmada = true;
                                break;
                        }
                    }
                }

                if (pago50soles && pago1raArmada && pago2raArmada) {
                    estado = 3; // Todos pagados
                } else if (pago50soles && pago1raArmada) {
                    estado = 2; // Dos pagados
                } else if (pago50soles) {
                    estado = 1; // Solo uno pagado
                } else {
                    estado = 0; // Ninguno pagado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estado;
    }

}
