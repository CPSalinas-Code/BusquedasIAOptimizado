/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import grafo.Grafo;
import grafo.Nodo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.Util;

/**
 *
 * @author Xavier
 */
public class App extends javax.swing.JFrame {

    private boolean isVariasBusquedas;

    public App() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButtonGrupo = new javax.swing.ButtonGroup();
        aplicacion1 = new main.Aplicacion();
        jPanel1 = new javax.swing.JPanel();
        rbGrafoControlado = new javax.swing.JRadioButton();
        rbGrafoAleatorio = new javax.swing.JRadioButton();
        rbLeerGrafoDeFichero = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroTotalDeNodos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaximoHijosPorNodo = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNodoOrigen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNodoDestino = new javax.swing.JTextField();
        rbVariasBusquedas = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtOrigenes = new javax.swing.JTextField();
        txtDestinos = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnEjecutar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COMPRACIÓN DE ALGORITMOS DE BÚSQUEDA");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));

        rbGrafoControlado.setText("Grafo controlado");

        rbGrafoAleatorio.setText("Grafo aleatorio");
        rbGrafoAleatorio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbGrafoAleatorioItemStateChanged(evt);
            }
        });
        rbGrafoAleatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbGrafoAleatorioActionPerformed(evt);
            }
        });

        rbLeerGrafoDeFichero.setText("Leer grafo de fichero");
        rbLeerGrafoDeFichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLeerGrafoDeFicheroActionPerformed(evt);
            }
        });

        jLabel2.setText("Número total de nodos:");

        jLabel3.setText("Máximo de hijos por nodo:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("*Para grafos aleatorios");
        jLabel4.setToolTipText("");

        jLabel5.setText("Seleccione una opción:");

        jLabel1.setText("Nodo origen:");

        jLabel6.setText("Nodo destino:");

        rbVariasBusquedas.setText("Varias búsquedas (grafo controlado)");
        rbVariasBusquedas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbVariasBusquedasItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 102, 255));
        jLabel7.setText("*Para varias búsquedas, origenes y destinos separados por una coma");
        jLabel7.setToolTipText("");

        txtOrigenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrigenesActionPerformed(evt);
            }
        });

        jLabel8.setText("Nodos origen:");

        jLabel9.setText("Nodos destino:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroTotalDeNodos, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaximoHijosPorNodo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNodoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNodoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbGrafoControlado)
                                    .addComponent(rbGrafoAleatorio))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbLeerGrafoDeFichero)
                                    .addComponent(rbVariasBusquedas)))
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDestinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrigenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDestinos, txtMaximoHijosPorNodo, txtNodoDestino, txtNodoOrigen, txtNumeroTotalDeNodos, txtOrigenes});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbGrafoControlado)
                    .addComponent(rbLeerGrafoDeFichero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbGrafoAleatorio)
                    .addComponent(rbVariasBusquedas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumeroTotalDeNodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtMaximoHijosPorNodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNodoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNodoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtOrigenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDestinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEjecutar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbLeerGrafoDeFicheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLeerGrafoDeFicheroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbLeerGrafoDeFicheroActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        radioButtonGrupo.add(rbGrafoControlado);
        radioButtonGrupo.add(rbGrafoAleatorio);
        radioButtonGrupo.add(rbLeerGrafoDeFichero);
        radioButtonGrupo.add(rbVariasBusquedas);

        rbGrafoControlado.setSelected(true);

        txtNumeroTotalDeNodos.setEditable(false);
        txtMaximoHijosPorNodo.setEditable(false);
        txtOrigenes.setEditable(false);
        txtDestinos.setEditable(false);

        txtNodoOrigen.setText("A");
        txtNodoDestino.setText("I");

        isVariasBusquedas = false;
    }//GEN-LAST:event_formWindowOpened

    private void rbGrafoAleatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGrafoAleatorioActionPerformed

    }//GEN-LAST:event_rbGrafoAleatorioActionPerformed

    private void rbGrafoAleatorioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbGrafoAleatorioItemStateChanged
        if (rbGrafoAleatorio.isSelected()) {
            txtMaximoHijosPorNodo.setEditable(true);
            txtNumeroTotalDeNodos.setEditable(true);
            txtMaximoHijosPorNodo.setText("3");
            txtNumeroTotalDeNodos.setText("1000");
            txtNumeroTotalDeNodos.requestFocusInWindow();
        } else {
            txtMaximoHijosPorNodo.setText("");
            txtNumeroTotalDeNodos.setText("");
            txtNumeroTotalDeNodos.setEditable(false);
            txtMaximoHijosPorNodo.setEditable(false);
        }
    }//GEN-LAST:event_rbGrafoAleatorioItemStateChanged

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        try {
            Grafo grafo = new Grafo();
            if (rbGrafoControlado.isSelected()) {
                grafo = Aplicacion.getGrafoControlado();
            } else if (rbLeerGrafoDeFichero.isSelected()) {
                grafo = Aplicacion.getGrafoLeidoDeFichero();
            } else if (rbGrafoAleatorio.isSelected()) {
                int totalNodos, maxHijosPorNodo;
                try {
                    totalNodos = Integer.parseInt(txtNumeroTotalDeNodos.getText());
                    maxHijosPorNodo = Integer.parseInt(txtMaximoHijosPorNodo.getText());
                    grafo = Aplicacion.getGrafoAleatorio(totalNodos, maxHijosPorNodo);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Asegúrese de que el número total de nodos y el máximo de hijos por nodo sean enteros.");
                }
            } else if (rbVariasBusquedas.isSelected()) {
                grafo = Aplicacion.getGrafoControlado();
            }

            if (!isVariasBusquedas) {
                ejecutarBusquedas(grafo);
            } else {
                ejecutarVariasBusquedas(grafo);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }


    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void txtOrigenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrigenesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrigenesActionPerformed

    private void rbVariasBusquedasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbVariasBusquedasItemStateChanged
        if (rbVariasBusquedas.isSelected()) {
            isVariasBusquedas = true;
            txtOrigenes.setEditable(true);
            txtDestinos.setEditable(true);
            txtMaximoHijosPorNodo.setEditable(false);
            txtNumeroTotalDeNodos.setEditable(false);
            txtNodoOrigen.setEditable(false);
            txtNodoDestino.setEditable(false);
            btnEjecutar.setEnabled(true);

            txtOrigenes.requestFocusInWindow();
            txtNodoOrigen.setText("");
            txtNodoDestino.setText("");
            txtMaximoHijosPorNodo.setText("");
            txtNumeroTotalDeNodos.setText("");
            txtOrigenes.setText("A,A");
            txtDestinos.setText("I,G");
        } else {
            isVariasBusquedas = false;
            txtOrigenes.setEditable(false);
            txtDestinos.setEditable(false);
            txtNodoOrigen.setEditable(true);
            txtNodoDestino.setEditable(true);
            btnEjecutar.setEnabled(true);
            txtNodoOrigen.setText("A");
            txtNodoDestino.setText("B");
            txtOrigenes.setText("");
            txtDestinos.setText("");
        }
    }//GEN-LAST:event_rbVariasBusquedasItemStateChanged

    private void ejecutarBusquedas(Grafo grafo) throws Exception, StackOverflowError {
        Nodo origen, destino;
        if (txtNodoOrigen.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar el nodo origen.");
            return;
        }
        if (txtNodoDestino.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar el nodo destino.");
            return;
        }

        origen = grafo.getNodo(txtNodoOrigen.getText().trim());
        destino = grafo.getNodo(txtNodoDestino.getText().trim());

        Aplicacion.ejecutarTodasLasBusquedas(grafo, origen, destino);
    }

    private void ejecutarVariasBusquedas(Grafo grafo) throws Exception, StackOverflowError {
        if (txtOrigenes.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar los nodos origen.");
            return;
        }
        if (txtDestinos.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar los nodos destinos.");
            return;
        }

        String[] origenes = txtOrigenes.getText().trim().split(",");
        String[] destinos = txtDestinos.getText().trim().split(",");

        if (origenes.length != destinos.length) {
            JOptionPane.showMessageDialog(rootPane, "El número de nodos origen debe ser igual al de nodos destino.");
            return;
        }

        Nodo[][] busquedas = new Nodo[origenes.length][2];
        for (int i = 0; i < origenes.length; i++) {
            busquedas[i][0] = grafo.getNodo(origenes[i]);
            busquedas[i][1] = grafo.getNodo(destinos[i]);
        }

        Aplicacion.ejecutarVariasBusquedas(grafo, busquedas);
    }

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.Aplicacion aplicacion1;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.ButtonGroup radioButtonGrupo;
    private javax.swing.JRadioButton rbGrafoAleatorio;
    private javax.swing.JRadioButton rbGrafoControlado;
    private javax.swing.JRadioButton rbLeerGrafoDeFichero;
    private javax.swing.JRadioButton rbVariasBusquedas;
    private javax.swing.JTextField txtDestinos;
    private javax.swing.JTextField txtMaximoHijosPorNodo;
    private javax.swing.JTextField txtNodoDestino;
    private javax.swing.JTextField txtNodoOrigen;
    private javax.swing.JTextField txtNumeroTotalDeNodos;
    private javax.swing.JTextField txtOrigenes;
    // End of variables declaration//GEN-END:variables
}
