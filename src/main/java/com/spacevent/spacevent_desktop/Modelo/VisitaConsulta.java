package com.spacevent.spacevent_desktop.Modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class VisitaConsulta extends Conexion {

    public boolean createVisita(Visita visita) {
        String sql = "INSERT INTO visita (Username, Phone, Email, ID_Local, Dates, Hours, Estade, ID_Personal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, visita.getUsername());
            pstmt.setInt(2, visita.getPhone());
            pstmt.setString(3, visita.getEmail());
            pstmt.setLong(4, visita.getID_Local());
            pstmt.setNull(5, java.sql.Types.DATE);
            pstmt.setNull(6, java.sql.Types.NULL);
            pstmt.setString(7, visita.getEstade());
            pstmt.setNull(8, java.sql.Types.NULL);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Visita readVisita(long id) {
        String sql = "SELECT * FROM visita WHERE ID = ?";
        Visita visita = null;
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                visita = new Visita(
                        rs.getLong("ID"),
                        rs.getString("Username"),
                        rs.getInt("Phone"),
                        rs.getString("Email"),
                        rs.getLong("ID_Local"),
                        rs.getDate("Dates"),
                        rs.getLong("Hours"),
                        rs.getString("Estade"),
                        rs.getLong("ID_Personal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visita;
    }

    public boolean updateVisita(Visita visita) {
        String sql = "UPDATE visita SET Username = ?, Phone = ?, Email = ?, ID_Local = ?, Dates = ?, Hours = ?, Estade = ?, ID_Personal = ? WHERE ID = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, visita.getUsername());
            pstmt.setInt(2, visita.getPhone());
            pstmt.setString(3, visita.getEmail());
            pstmt.setLong(4, visita.getID_Local());
            pstmt.setDate(5, visita.getDates());
            pstmt.setLong(6, visita.getHours());
            pstmt.setString(7, visita.getEstade());
            pstmt.setLong(8, visita.getID_Personal());
            pstmt.setLong(9, visita.getID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVisitaEstade(String newEstade, long idVisita) {
        String sql = "UPDATE visita SET Estade = ? WHERE ID = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newEstade);
            pstmt.setLong(2, idVisita);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVisita(long id) {
        String sql = "DELETE FROM visita WHERE ID = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public List<Visita> getAllVisitas() {
//        String sql = "SELECT * FROM visita";
//        List<Visita> visitas = new ArrayList<>();
//        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                Visita visita = new Visita(
//                        rs.getLong("ID"),
//                        rs.getString("Username"),
//                        rs.getInt("Phone"),
//                        rs.getString("Email"),
//                        rs.getLong("ID_Local"),
//                        rs.getDate("Dates"),
//                        rs.getLong("Hours"),
//                        rs.getString("Estade"),
//                        rs.getLong("ID_Personal"));
//                visitas.add(visita);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return visitas;
//    }
    public List<Visita> getAllVisitas() {
        String sql = "SELECT * FROM visita";
        List<Visita> visitas = new ArrayList<>();
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Visita visita = new Visita(
                        rs.getLong("ID"),
                        rs.getString("Username"),
                        rs.getInt("Phone"),
                        rs.getString("Email"),
                        rs.getLong("ID_Local"),
                        rs.getDate("Dates"),
                        rs.getLong("Hours"),
                        rs.getString("Estade"),
                        rs.getLong("ID_Personal"));
                visitas.add(visita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visitas;
    }

    public void asignarPersonalEquitativamente() {
        List<Visita> visitas = getAllVisitas();
        List<Personal> personales = new PersonalConsulta().getAllPersonales();

        // Contar las asignaciones actuales de cada personal
        Map<Long, Integer> personalAsignaciones = new HashMap<>();
        for (Personal personal : personales) {
            personalAsignaciones.put(personal.getId(), 0);
        }

        // Contar las asignaciones actuales
        for (Visita visita : visitas) {
            if (visita.getID_Personal() != null && visita.getID_Personal() != 0) {
                personalAsignaciones.put(visita.getID_Personal(), personalAsignaciones.get(visita.getID_Personal()) + 1);
            }
        }

        // Obtener la lista de visitas que necesitan un ID_Personal
        List<Visita> visitasSinPersonal = visitas.stream()
                .filter(v -> v.getID_Personal() == null || v.getID_Personal() == 0)
                .collect(Collectors.toList());

        // Ordenar el personal por el número de asignaciones actuales (de menor a mayor)
        List<Map.Entry<Long, Integer>> personalList = new ArrayList<>(personalAsignaciones.entrySet());
        personalList.sort(Map.Entry.comparingByValue());

        // Asignar el personal equitativamente a las visitas que necesitan ID_Personal
        int personalCount = personalList.size();
        int index = 0;

        for (Visita visita : visitasSinPersonal) {
            Map.Entry<Long, Integer> personalAsignado = personalList.get(index % personalCount);
            visita.setID_Personal(personalAsignado.getKey());
            personalAsignaciones.put(personalAsignado.getKey(), personalAsignaciones.get(personalAsignado.getKey()) + 1);
            index++;
            actualizarTablaVisita(visita);

            // Reordenar la lista de personal por número de asignaciones después de cada asignación
            personalList.sort(Map.Entry.comparingByValue());
        }
    }

    private void actualizarTablaVisita(Visita visita) {
        String sql = "UPDATE visita SET ID_Personal = ? WHERE ID = ?";
        try (Connection conn = new Conexion().getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, visita.getID_Personal());
            pstmt.setLong(2, visita.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void llenarTabla(javax.swing.JTable table) {
//        List<Visita> visitas = getAllVisitas();
//        DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
//            "ID",
//            "Username",
//            "Phone",
//            "Email",
//            "ID_Local",
//            "Dates",
//            "Hours",
//            "Estade",
//            "ID_Personal"});
//        table.setModel(modelotabla);
//
//        int altoFila = 40;
//        int[] anchos = {50, 100, 80, 150, 80, 100, 60, 100, 80};
//        table.setRowHeight(altoFila);
//
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
//        }
//
//        // Centrar Texto
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//        }
//
//        for (Visita visita : visitas) {
//            modelotabla.addRow(new Object[]{
//                visita.getID(),
//                visita.getUsername(),
//                visita.getPhone(),
//                visita.getEmail(),
//                visita.getID_Local(),
//                visita.getDates(),
//                visita.getHours(),
//                visita.getEstade(),
//                visita.getID_Personal()});
//        }
//
//        // Visualizar Columnas
//        int[] columnasSeleccionadas = {0}; // Columnas seleccionadas por el usuario que no se verán
//
//        // Luego, aplicamos las operaciones a las columnas seleccionadas
//        TableColumnModel columnModel = table.getColumnModel();
//
//        for (int columnIndex : columnasSeleccionadas) {
//            TableColumn idColumn = columnModel.getColumn(columnIndex);
//            idColumn.setMinWidth(0);
//            idColumn.setMaxWidth(0);
//            idColumn.setPreferredWidth(0);
//            idColumn.setResizable(false);
//        }
//
//        // Personalización adicional de la tabla
//        table.getTableHeader().setVisible(false);
//        table.setBorder(BorderFactory.createEmptyBorder());
//        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
//    }
    public void llenarTabla(javax.swing.JTable table) {
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;

        try {
            DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
                "ID",
                "Username",
                "Phone",
                "Email",
                "ID_Local",
                "Dates",
                "Hours",
                "Estade",
                "ID_Personal"});

            table.setModel(modelotabla);
            Conexion conn = new Conexion();
            java.sql.Connection con = conn.getConexion();

            String sql = "SELECT "
                    + "v.ID,"
                    + " v.Username,"
                    + " v.Phone,"
                    + " v.Email,"
                    + " v.ID_Local,"
                    + " v.Dates,"
                    + " v.Hours,"
                    + " v.Estade,"
                    + " p.Username as PersonalName "
                    + "FROM visita v "
                    + "JOIN personal p "
                    + "ON v.ID_Personal = p.ID;";
            ps1 = con.prepareStatement(sql);
            rs1 = ps1.executeQuery();

            ResultSetMetaData rsMd1 = (ResultSetMetaData) rs1.getMetaData();
            int cantidadColumnas = rsMd1.getColumnCount();

            int altoFila = 40;
            int[] anchos = {50, 100, 80, 150, 80, 100, 60, 100, 80};
            table.setRowHeight(altoFila);

            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            //Centrar Texto
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs1.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs1.getObject(i + 1);
                    System.out.print(rs1.getObject(i + 1) + " ");
                }

                System.out.println();
                modelotabla.addRow(filas);

            }

            //Visualizar Columnas
            int[] columnasSeleccionadas = {0}; // Columnas seleccionadas por el usuario que no se veran

            // Luego, aplicamos las operaciones a las columnas seleccionadas
            TableColumnModel columnModel = table.getColumnModel();

            for (int columnIndex : columnasSeleccionadas) {
                TableColumn idColumn = columnModel.getColumn(columnIndex);
                idColumn.setMinWidth(0);
                idColumn.setMaxWidth(0);
                idColumn.setPreferredWidth(0);
                idColumn.setResizable(false);
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }

        table.getTableHeader().setVisible(true);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
    }

    public void llenarTablaPorAsesor(javax.swing.JTable table, int idAsesor) {
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;

        try {
            DefaultTableModel modelotabla = new DefaultTableModel(new Object[][]{}, new Object[]{
                "ID",
                "Username",
                "Phone",
                "Email",
                "ID_Local",
                "Dates",
                "Hours",
                "Estade",
                "PersonalName"});

            table.setModel(modelotabla);
            Conexion conn = new Conexion();
            java.sql.Connection con = conn.getConexion();

            String sql = "SELECT "
                    + "v.ID, "
                    + "v.Username, "
                    + "v.Phone, "
                    + "v.Email, "
                    + "v.ID_Local, "
                    + "v.Dates, "
                    + "v.Hours, "
                    + "v.Estade, "
                    + "p.Username as PersonalName "
                    + "FROM visita v "
                    + "JOIN personal p ON v.ID_Personal = p.ID "
                    + "WHERE v.ID_Personal = ?;";
            ps1 = con.prepareStatement(sql);
            ps1.setInt(1, idAsesor);
            rs1 = ps1.executeQuery();

            ResultSetMetaData rsMd1 = rs1.getMetaData();
            int cantidadColumnas = rsMd1.getColumnCount();

            int altoFila = 40;
            int[] anchos = {50, 100, 80, 150, 80, 100, 60, 100, 80};
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

            while (rs1.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs1.getObject(i + 1);
                }
                modelotabla.addRow(filas);
            }

            // Visualizar columnas
            int[] columnasSeleccionadas = {0, 5, 6, 8}; // Columnas seleccionadas por el usuario que no se verán

            // Aplicar operaciones a las columnas seleccionadas
            TableColumnModel columnModel = table.getColumnModel();

            for (int columnIndex : columnasSeleccionadas) {
                TableColumn idColumn = columnModel.getColumn(columnIndex);
                idColumn.setMinWidth(0);
                idColumn.setMaxWidth(0);
                idColumn.setPreferredWidth(0);
                idColumn.setResizable(false);
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }

        table.getTableHeader().setVisible(true);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
    }

    public void llenarComboBoxPersonales(JComboBox<Personal> comboBox) {
        List<Personal> personales = new PersonalConsulta().getAllPersonales();
        comboBox.removeAllItems(); // Limpiar el comboBox antes de llenarlo
        for (Personal personal : personales) {
            comboBox.addItem(personal);
        }
    }

    public boolean asignarPersonalAVisita(Long id, Personal personal) {
        String sql = "UPDATE visita SET ID_Personal = ? WHERE ID = ?";
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
    
     
    
    public void enviarmail(String email, String nombresCliente, String apellidosCliente, String dniCliente, String direccionCliente,
            String horaInicioReserva, String horaFinReserva, String diaReserva, String ubicacion, double monto, long id_contrato) {
        String remitente = "gabrielsortestersor@gmail.com";
        String clave = "qdfc diak skwx gceu";
        String destino = email;
        String asunto = "TEST";
        String cuerpo = "Buenas, acaba de registrarse un ingreso\n\nCon los siguientes datos";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);

        Session session = Session.getDefaultInstance(props);
        MimeMessage mensaje = new MimeMessage(session);

        try {
            // Generar PDF
            String pdfFilePath = generarContratoPDF(nombresCliente, apellidosCliente, dniCliente, direccionCliente,
                    horaInicioReserva, horaFinReserva, diaReserva, ubicacion, monto, id_contrato);

            // Configurar el correo
            mensaje.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);

            // Adjuntar el PDF
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            DataSource source = new FileDataSource(pdfFilePath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("contrato.pdf");
            multipart.addBodyPart(messageBodyPart);
            mensaje.setContent(multipart);

            // Enviar el correo
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();

            JOptionPane.showMessageDialog(null, "Correo Enviado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generarContratoPDF(String nombresCliente, String apellidosCliente, String dniCliente, String direccionCliente,
            String horaInicioReserva, String horaFinReserva, String diaReserva, String ubicacion, double monto, long id_contrato) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.newLineAtOffset(50, 750);
        contentStream.showText("Número de contrato: " + id_contrato);
        contentStream.newLineAtOffset(0, -40);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.showText("Contrato de Reserva de Local");
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(0, -40);
        contentStream.showText("Entre las partes:");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("1. Cliente: " + nombresCliente + " " + apellidosCliente);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("   DNI: " + dniCliente);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("   Dirección: " + direccionCliente);
        contentStream.newLineAtOffset(0, -15);
        contentStream.newLine();
        contentStream.showText("2. Contratista: Gabriel Eduardo Vilchez Chota");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("   DNI: 75605745");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("   Dirección: Calle los Naranjos 248");
        contentStream.newLine();
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Se acuerda lo siguiente:");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("El contratista se compromete a reservar el local para el cliente el día " + diaReserva);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("ubicado en " + ubicacion);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("desde las " + horaInicioReserva + " hasta las " + horaFinReserva + ".");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("El contratista se compromete a pagar el monto de " + monto + " por la reserva.");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("El cliente acepta cumplir con las normas establecidas para el uso del local.");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("En caso de cancelación, el cliente debe notificar al contratista con al menos 48 horas de antelación.");

        contentStream.endText();

        // Añadir la línea de firma
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 200);
        contentStream.showText("_______________________________");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Firma cliente");

        contentStream.endText();

        contentStream.close();

        String filePath = "contrato.pdf";
        document.save(filePath);
        document.close();

        return filePath;
    }

}
