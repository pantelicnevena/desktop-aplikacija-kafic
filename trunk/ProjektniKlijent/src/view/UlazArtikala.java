/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import domen.Artikal;
import domen.Ulaz;
import domen.Zaposleni;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;
import view.model.ModelTableUlazArtikala;

/**
 *
 * @author Nevena
 */
public class UlazArtikala extends javax.swing.JFrame {

    /**
     * Creates new form UlazArtikala
     */
    public UlazArtikala() {
        initComponents();
        popuniTabelu();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonDodaj = new javax.swing.JButton();
        buttonUkloni = new javax.swing.JButton();
        buttonPonisti = new javax.swing.JButton();
        buttonSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Ulaz artikala");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        buttonDodaj.setText("Dodaj artikal");
        buttonDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDodajActionPerformed(evt);
            }
        });

        buttonUkloni.setText("Ukloni artikal");
        buttonUkloni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUkloniActionPerformed(evt);
            }
        });

        buttonPonisti.setText("Poništi unos");
        buttonPonisti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPonistiActionPerformed(evt);
            }
        });

        buttonSacuvaj.setText("Sačuvaj unos");
        buttonSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonDodaj)
                            .addComponent(buttonUkloni))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonSacuvaj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonPonisti)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonDodaj)
                        .addGap(18, 18, 18)
                        .addComponent(buttonUkloni)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonPonisti)
                    .addComponent(buttonSacuvaj))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDodajActionPerformed
        ModelTableUlazArtikala mtu = (ModelTableUlazArtikala) jTable1.getModel();
        Ulaz u = new Ulaz();
        Artikal a = new Artikal();
        u.setArtikal(a);
        mtu.dodajRed(u);
    }//GEN-LAST:event_buttonDodajActionPerformed

    private void buttonUkloniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUkloniActionPerformed
        int rb = jTable1.getSelectedRow();
        if (rb == -1) {
            JOptionPane.showMessageDialog(this, "Selektuj red!");
        } else {
            ModelTableUlazArtikala mtu = (ModelTableUlazArtikala) jTable1.getModel();
            mtu.obrisiRed(rb);
        }
    }//GEN-LAST:event_buttonUkloniActionPerformed

    private void buttonPonistiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPonistiActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buttonPonistiActionPerformed

    private void buttonSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSacuvajActionPerformed
        try {
            int brojRedova = jTable1.getModel().getRowCount();
            int brojKolona = jTable1.getModel().getColumnCount();
            List<Ulaz> listaUlaza = new ArrayList<>();
            List<Artikal> listaArtikala = new ArrayList<>();
            
            for (int i = 0; i < brojRedova; i++) {
                Ulaz ulaz = new Ulaz();
                ulaz.setArtikal((Artikal) jTable1.getModel().getValueAt(i, 0));
                ulaz.setKolicina((int) jTable1.getModel().getValueAt(i, 1));
                ulaz.setCena((int) jTable1.getModel().getValueAt(i, 2));
                listaUlaza.add(ulaz);
            }
            
            for (int i = 0; i < listaUlaza.size(); i++) {
                Artikal artikal = new Artikal();
                artikal.setArtikalID(listaUlaza.get(i).getArtikal().getArtikalID());
                artikal.setNazivArtikla(listaUlaza.get(i).getArtikal().getNazivArtikla());
                artikal.setAmbalaza(listaUlaza.get(i).getArtikal().getAmbalaza());
                artikal.setRokTrajanja(listaUlaza.get(i).getArtikal().getRokTrajanja());
                double stanje = listaUlaza.get(i).getArtikal().getStanjeNaZalihama() + (double) listaUlaza.get(i).getKolicina();
                artikal.setStanjeNaZalihama(stanje);
                double cena = Double.valueOf(listaUlaza.get(i).getCena());
                artikal.setCena(cena);
                artikal.setDistributer(listaUlaza.get(i).getArtikal().getDistributer());
                artikal.setKategorija(listaUlaza.get(i).getArtikal().getKategorija());
                listaArtikala.add(artikal);
            }
            
            System.out.println(""+listaArtikala);
            TObjekat posalji = new TObjekat(listaArtikala, "ulazArtikala");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            
            if (odgovor.getPoruka().equals("ok")){
                String ispis = "Ulaz artikala je uspešno sačuvan.\n";
                for (int i = 0; i<listaArtikala.size(); i++)
                    ispis += "Artikal: "+listaArtikala.get(i).getNazivArtikla()+
                            ", novo stanje na zalihama: "+listaArtikala.get(i).getStanjeNaZalihama()+
                            ", nova cena: "+listaArtikala.get(i).getCena()+" din.\n";
                JOptionPane.showMessageDialog(null, ispis);
                ModelTableUlazArtikala mtu = new ModelTableUlazArtikala();
                jTable1.setModel(mtu);
            }else{
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(UlazArtikala.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UlazArtikala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonSacuvajActionPerformed

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
            java.util.logging.Logger.getLogger(UlazArtikala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UlazArtikala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UlazArtikala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UlazArtikala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UlazArtikala().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDodaj;
    private javax.swing.JButton buttonPonisti;
    private javax.swing.JButton buttonSacuvaj;
    private javax.swing.JButton buttonUkloni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public void popuniTabelu() {
        try {
            ModelTableUlazArtikala mtu = new ModelTableUlazArtikala();
            jTable1.setModel(mtu);
            
            TObjekat posalji = new TObjekat(null, "listaArtikala");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            
            List<Artikal> artikli = (List<Artikal>) odgovor.getObjekat();
            JComboBox comboArtikli = new JComboBox();
            for (Artikal a : artikli){
                comboArtikli.addItem(a);
            }
            
            TableColumn tcArtikal = jTable1.getColumnModel().getColumn(0);
            tcArtikal.setCellEditor(new DefaultCellEditor(comboArtikli));
        } catch (Exception ex) {
            Logger.getLogger(UlazArtikala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}