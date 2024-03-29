/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapheditor;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * Die Editor-GUI
 * @author Pardella
 */
public class GraphFrame extends javax.swing.JFrame {
    
    //Kümmert sich um die Zeichen-Aktion und die Verwaltung in der DS Graph
    private final GraphManager gm;
    JFileChooser fileChooser;

    /**
     * Creates new form GraphFrame
     */
    public GraphFrame() {
        initComponents();
        jBG_DrawMode.add(jR_Knoten);
        jBG_DrawMode.add(jR_Kanten);
        jBG_DrawMode.add(jR_Edit);
        gm = new GraphManager(this);//Der GraphManager bekommt diese Objekt, um auf GUI-Elemente zugreifen zu können.
        jPanel2.addMouseListener(gm);   //Der GraphManager implementiert das INterface MouseListener und wird bei der Zeichenfläche als solcher registiert.
                                        //Somit werden Reaktionen auf alle möglichen Mousaktionen auf dem Panel in den passenden Methoden des gm implementiert
        fileChooser = new JFileChooser();
    }

    public JPanel getjTabbedPane1() {
        return jPanel2;
    }

    public JTextArea getjTextArea1() {
        return jTextArea1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBG_DrawMode = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jBut_out = new javax.swing.JButton();
        jBut_clear = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jR_Knoten = new javax.swing.JRadioButton();
        jR_Kanten = new javax.swing.JRadioButton();
        jR_Edit = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMI_save = new javax.swing.JMenuItem();
        jMI_open = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMI_complete = new javax.swing.JMenuItem();
        jMI_generate = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMI_dijkstra = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMI_help = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Graph Editor LK Par 2022");
        setFocusable(false);

        jPanel1.setFocusable(false);

        jScrollPane1.setFocusable(false);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        jLabel1.setText("Graph-Ausgabe:");
        jLabel1.setFocusable(false);

        jBut_out.setText("Graph ausgeben");
        jBut_out.setFocusable(false);
        jBut_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBut_outActionPerformed(evt);
            }
        });

        jBut_clear.setText("Neuer Graph");
        jBut_clear.setFocusable(false);
        jBut_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBut_clearActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 0, 13)); // NOI18N
        jLabel3.setText("Zeichenmodus: ");
        jLabel3.setFocusable(false);

        jR_Knoten.setText("Knoten");
        jR_Knoten.setFocusable(false);
        jR_Knoten.setSelected(true);
        jR_Knoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_KnotenActionPerformed(evt);
            }
        });

        jR_Kanten.setText("Kanten");
        jR_Kanten.setFocusable(false);
        jR_Kanten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_KantenActionPerformed(evt);
            }
        });

        jR_Edit.setText("Edit");
        jR_Edit.setFocusable(false);
        jR_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_EditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBut_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBut_out, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jR_Knoten)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jR_Kanten)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jR_Edit))
                                    .addComponent(jLabel3))
                                .addGap(0, 258, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBut_clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBut_out)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jR_Knoten)
                            .addComponent(jR_Kanten)
                            .addComponent(jR_Edit))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jPanel2.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        jLabel2.setText("Graph-Zeichenfeld:");
        jLabel2.setFocusable(false);

        jMenuBar1.setFocusable(false);

        jMenu1.setText("File");
        jMenu1.setFocusable(false);

        jMI_save.setText("Speichern");
        jMI_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_saveActionPerformed(evt);
            }
        });
        jMenu1.add(jMI_save);

        jMI_open.setText("Laden");
        jMI_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_openActionPerformed(evt);
            }
        });
        jMenu1.add(jMI_open);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setFocusable(false);

        jMI_complete.setText("Vervollständigen");
        jMI_complete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_completeActionPerformed(evt);
            }
        });
        jMenu2.add(jMI_complete);

        jMI_generate.setText("Vollständig generieren");
        jMI_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_generateActionPerformed(evt);
            }
        });
        jMenu2.add(jMI_generate);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Algorithms");
        jMenu3.setFocusable(false);

        jMI_dijkstra.setText("Dijkstra");
        jMI_dijkstra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_dijkstraActionPerformed(evt);
            }
        });
        jMenu3.add(jMI_dijkstra);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Help");
        jMenu4.setFocusable(false);

        jMI_help.setText("Help");
        jMI_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_helpActionPerformed(evt);
            }
        });
        jMenu4.add(jMI_help);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMI_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_saveActionPerformed
        if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            gm.exportGraph(fileChooser.getSelectedFile());
    }//GEN-LAST:event_jMI_saveActionPerformed

    private void jMI_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_openActionPerformed
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            gm.importGraph(fileChooser.getSelectedFile());
    }//GEN-LAST:event_jMI_openActionPerformed

    private void jR_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_EditActionPerformed
        gm.setMode(GraphManager.DrawMode.Edit);
    }//GEN-LAST:event_jR_EditActionPerformed

    private void jR_KantenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_KantenActionPerformed
        gm.setMode(GraphManager.DrawMode.Kanten);
    }//GEN-LAST:event_jR_KantenActionPerformed

    private void jR_KnotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_KnotenActionPerformed
        gm.setMode(GraphManager.DrawMode.Knoten);
    }//GEN-LAST:event_jR_KnotenActionPerformed

    //Die interessanten Aspekte der Ereignisbehandlung für die Buttons werden von den entsprechenden Methoden des gm erledigt...
    
    private void jBut_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBut_clearActionPerformed
        // TODO add your handling code here:
        gm.newG();
    }//GEN-LAST:event_jBut_clearActionPerformed

    private void jBut_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBut_outActionPerformed
        jTextArea1.setText("Der Graph ist " + (gm.complete() ? "" : "nicht ") + "vollständig.\n");
        jTextArea1.append("Der Graph ist " + (gm.eulerianPath() ? "" : "nicht ") + "euler'sch.\n");
        jTextArea1.append(gm.outVertices());
        jTextArea1.append(gm.outEdges());
    }//GEN-LAST:event_jBut_outActionPerformed

    private void jMI_completeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_completeActionPerformed
        gm.completeGraph();
    }//GEN-LAST:event_jMI_completeActionPerformed

    private void jMI_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_generateActionPerformed
        String input = (String) JOptionPane.showInputDialog(this, "Gib Anzahl an Knoten ein!", "Vollständigen Graph generieren", JOptionPane.PLAIN_MESSAGE, null, null, null);
        try {
            gm.generateGraph(Integer.parseInt(input));
        } catch(NumberFormatException e) {
            System.out.println("Anzahl an Knoten ist keine Zahl: '" + input + "'");
        }
    }//GEN-LAST:event_jMI_generateActionPerformed

    private void jMI_dijkstraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_dijkstraActionPerformed
        gm.setMode(GraphManager.DrawMode.Dijkstra);
    }//GEN-LAST:event_jMI_dijkstraActionPerformed

    private void jMI_helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_helpActionPerformed
        JOptionPane.showMessageDialog(this, "Knoten:\n- Knoten hinzufügen: Linksklick in freien Raum\n\nKanten:\n- Kante hinzufügen: Linksklick auf Startpunkt, dann Linksklick auf Endpunkt\n\nEdit:\n- Knoten bewegen: Linksklick auf bestehenden Knoten, dann Linksklick auf neue Position\n- Knoten löschen: Rechtsklick auf Knoten\n- Kante löschen: Linksklick auf einen Endpunkt, dann Rechtsklick auf anderen Endpunkt", "Hilfe", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMI_helpActionPerformed

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
            java.util.logging.Logger.getLogger(GraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GraphFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup jBG_DrawMode;
    private javax.swing.JButton jBut_clear;
    private javax.swing.JButton jBut_out;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMI_complete;
    private javax.swing.JMenuItem jMI_dijkstra;
    private javax.swing.JMenuItem jMI_generate;
    private javax.swing.JMenuItem jMI_help;
    private javax.swing.JMenuItem jMI_open;
    private javax.swing.JMenuItem jMI_save;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jR_Edit;
    private javax.swing.JRadioButton jR_Kanten;
    private javax.swing.JRadioButton jR_Knoten;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
