/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminKreiraj;

import domen.Zaposleni;
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
public class KreirajZaposlenog extends javax.swing.JFrame {

    /**
     * Creates new form KreirajZaposlenog
     */
    public KreirajZaposlenog() {
        initComponents();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textKorisnickaSifra = new javax.swing.JTextField();
        textKorisnickoIme = new javax.swing.JTextField();
        textPrezime = new javax.swing.JTextField();
        textIme = new javax.swing.JTextField();
        buttonOdustani = new javax.swing.JButton();
        buttonKreiraj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Kreiranje profila zaposlenog");

        jLabel2.setText("Ime:");

        jLabel3.setText("Prezime:");

        jLabel4.setText("Korisničko ime:");

        jLabel5.setText("Korisnička šifra:");

        buttonOdustani.setText("Odustani");
        buttonOdustani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOdustaniActionPerformed(evt);
            }
        });

        buttonKreiraj.setText("Kreiraj profil zaposlenog");
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
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(textKorisnickaSifra))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonKreiraj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonOdustani))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 125, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textKorisnickoIme)
                            .addComponent(textPrezime, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textIme, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                    .addComponent(textIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textKorisnickoIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textKorisnickaSifra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
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

    private void buttonKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKreirajActionPerformed
        try {
            TObjekat posalji = new TObjekat(null, "vratiZaposleniID");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            int zaposleniID = (int) odgovor.getObjekat();

            String ime = textIme.getText();
            String prezime = textPrezime.getText();
            String korisnickoIme = textKorisnickoIme.getText();
            String korisnickaSifra = textKorisnickaSifra.getText();
            
            if (validacija()){
                Zaposleni zaposleni = new Zaposleni(zaposleniID, ime, prezime, korisnickoIme, korisnickaSifra);
                TObjekat posalji2 = new TObjekat(zaposleni, "sacuvajZaposlenog");
                KomunikacijaKlijent.vratiObjekat().posalji(posalji2);
                TObjekat odgovor2 = KomunikacijaKlijent.vratiObjekat().procitaj();
                JOptionPane.showMessageDialog(this, "Sistem je uspešno sačuvao zaposlenog.");
            }else JOptionPane.showMessageDialog(this, "Došlo je do greške prilikom čuvanja zaposlenog.");
        } catch (IOException ex) {
            Logger.getLogger(KreirajZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KreirajZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(KreirajZaposlenog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KreirajZaposlenog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KreirajZaposlenog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KreirajZaposlenog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KreirajZaposlenog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonKreiraj;
    private javax.swing.JButton buttonOdustani;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField textIme;
    private javax.swing.JTextField textKorisnickaSifra;
    private javax.swing.JTextField textKorisnickoIme;
    private javax.swing.JTextField textPrezime;
    // End of variables declaration//GEN-END:variables

    public boolean  validacija(){
        if (textIme.getText().equals("")) return false;
        if (textPrezime.getText().equals("")) return false;
        if (textKorisnickoIme.getText().equals("")) return false;
        if (textKorisnickaSifra.getText().equals("")) return false;
        return true;
    }
}
