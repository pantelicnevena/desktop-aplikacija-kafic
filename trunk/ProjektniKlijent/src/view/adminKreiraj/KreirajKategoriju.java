/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.adminKreiraj;

import domen.Distributer;
import domen.KategorijaArtikla;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;

/**
 *
 * @author Nevena
 */
public class KreirajKategoriju extends javax.swing.JFrame {

    /**
     * Creates new form KreirajKategoriju
     */
    public KreirajKategoriju() {
        initComponents();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        textID.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textNaziv = new javax.swing.JTextField();
        textID = new javax.swing.JTextField();
        buttonVratiID = new javax.swing.JButton();
        buttonOdustani = new javax.swing.JButton();
        buttonKreiraj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Kreiranje kategorije");

        jLabel2.setText("Kategorija ID:");

        jLabel3.setText("Naziv kategorije:");

        buttonVratiID.setText("Vrati ID");
        buttonVratiID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVratiIDActionPerformed(evt);
            }
        });

        buttonOdustani.setText("Odustani");
        buttonOdustani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOdustaniActionPerformed(evt);
            }
        });

        buttonKreiraj.setText("Kreiraj kategoriju");
        buttonKreiraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKreirajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(textNaziv))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(32, 32, 32)
                        .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonVratiID, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonKreiraj)
                        .addGap(18, 18, 18)
                        .addComponent(buttonOdustani)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonVratiID))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOdustani)
                    .addComponent(buttonKreiraj))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOdustaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOdustaniActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buttonOdustaniActionPerformed

    private void buttonVratiIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVratiIDActionPerformed
        try {
            TObjekat posalji = new TObjekat(null, "vratiKategorijaID");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            int artikalID = (int) odgovor.getObjekat();
            
            textID.setText(String.valueOf(artikalID));
        } catch (IOException ex) {
            Logger.getLogger(KreirajKategoriju.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KreirajKategoriju.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonVratiIDActionPerformed

    private void buttonKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKreirajActionPerformed
        if (validacija()){
            try {
                int id = Integer.valueOf(textID.getText());
                String naziv = textNaziv.getText();
                
                KategorijaArtikla kategorija = new KategorijaArtikla(id, naziv);
                TObjekat posalji = new TObjekat(kategorija, "sacuvajKategoriju");
                KomunikacijaKlijent.vratiObjekat().posalji(posalji);
                TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
                JOptionPane.showMessageDialog(null, "Sistem je uspešno sačuvao kategoriju.");
                int noviID = id + 1;
                textID.setText(String.valueOf(noviID));
            } catch (IOException ex) {
                Logger.getLogger(KreirajDistributera.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(KreirajDistributera.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Molimo Vas da generišete ID za kategoriju.");
            }
        }
    }//GEN-LAST:event_buttonKreirajActionPerformed

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
            java.util.logging.Logger.getLogger(KreirajKategoriju.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KreirajKategoriju.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KreirajKategoriju.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KreirajKategoriju.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KreirajKategoriju().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonKreiraj;
    private javax.swing.JButton buttonOdustani;
    private javax.swing.JButton buttonVratiID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField textID;
    private javax.swing.JTextField textNaziv;
    // End of variables declaration//GEN-END:variables

    public boolean  validacija(){
        if (textNaziv.equals("")) return false;
        return true;
    }
}
