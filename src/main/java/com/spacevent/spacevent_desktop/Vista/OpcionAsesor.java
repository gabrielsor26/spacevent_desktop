/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.spacevent.spacevent_desktop.Vista;

import com.spacevent.spacevent_desktop.Controlador.LoginControlador;
import com.spacevent.spacevent_desktop.Controlador.ReservaAsesorControlador;
import com.spacevent.spacevent_desktop.Controlador.VisitaAsesorControlador;
import com.spacevent.spacevent_desktop.Modelo.Contrato;
import com.spacevent.spacevent_desktop.Modelo.ContratoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Detalle_Pago;
import com.spacevent.spacevent_desktop.Modelo.Detalle_PagoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Devolucion;
import com.spacevent.spacevent_desktop.Modelo.DevolucionConsulta;
import com.spacevent.spacevent_desktop.Modelo.LocalConsulta;
import com.spacevent.spacevent_desktop.Modelo.Pago;
import com.spacevent.spacevent_desktop.Modelo.PagoConsulta;
import com.spacevent.spacevent_desktop.Modelo.Personal;
import com.spacevent.spacevent_desktop.Modelo.PersonalConsulta;
import com.spacevent.spacevent_desktop.Modelo.Reserva;
import com.spacevent.spacevent_desktop.Modelo.ReservaConsulta;
import com.spacevent.spacevent_desktop.Modelo.Usuario;
import com.spacevent.spacevent_desktop.Modelo.UsuarioConsulta;
import com.spacevent.spacevent_desktop.Modelo.Visita;
import com.spacevent.spacevent_desktop.Modelo.VisitaConsulta;

/**
 *
 * @author Usuario
 */
public class OpcionAsesor extends javax.swing.JFrame {

    public OpcionAsesor() {
        initComponents();
        this.setTitle("Opciones Asesor");
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnReservaAsignada = new javax.swing.JButton();
        btnVisitasAsignadas = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReservaAsignada.setBackground(new java.awt.Color(153, 153, 255));
        btnReservaAsignada.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        btnReservaAsignada.setForeground(new java.awt.Color(255, 255, 255));
        btnReservaAsignada.setText("Reservas Asignadas");
        btnReservaAsignada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservaAsignadaActionPerformed(evt);
            }
        });
        jPanel1.add(btnReservaAsignada, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 70));

        btnVisitasAsignadas.setBackground(new java.awt.Color(153, 153, 255));
        btnVisitasAsignadas.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        btnVisitasAsignadas.setForeground(new java.awt.Color(255, 255, 255));
        btnVisitasAsignadas.setText("Visitas Asignadas");
        btnVisitasAsignadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisitasAsignadasActionPerformed(evt);
            }
        });
        jPanel1.add(btnVisitasAsignadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 250, 70));

        btnAtras.setBackground(new java.awt.Color(102, 204, 255));
        btnAtras.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("<- Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        jPanel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservaAsignadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaAsignadaActionPerformed
        this.dispose();
        ReservaAsesorControlador ctrl = new ReservaAsesorControlador(
                LoginControlador.id_usuario,
                new ReservaAsesorVista(),
                new Reserva(),
                new ReservaConsulta(),
                new ContratoConsulta(),
                new Usuario(),
                new UsuarioConsulta(),
                new LocalConsulta(),
                new Pago(),
                new PagoConsulta(),
                new Detalle_Pago(),
                new Detalle_PagoConsulta(),
                new Devolucion(),
                new DevolucionConsulta()
        );
        ctrl.iniciar();
        ctrl.getfrm().setVisible(true);
    }//GEN-LAST:event_btnReservaAsignadaActionPerformed

    private void btnVisitasAsignadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisitasAsignadasActionPerformed
        this.dispose();
        VisitaAsesorControlador ctrl = new VisitaAsesorControlador((int) LoginControlador.id_usuario, new Visita(), new VisitaAsesorVista(), new VisitaConsulta(), new Contrato(), new ContratoConsulta(), new LocalConsulta());
        ctrl.iniciar();
        ctrl.getfrm().setVisible(true);

    }//GEN-LAST:event_btnVisitasAsignadasActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
        LoginControlador ctrl = new LoginControlador(new LoginVista(), new Personal(), new PersonalConsulta());
        ctrl.iniciar();
        ctrl.getfrm().setVisible(true);
    }//GEN-LAST:event_btnAtrasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OpcionAsesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpcionAsesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpcionAsesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpcionAsesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OpcionAsesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAtras;
    public javax.swing.JButton btnReservaAsignada;
    public javax.swing.JButton btnVisitasAsignadas;
    public javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
