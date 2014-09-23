/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kontroler;

import domen.StavkaPorudzbine;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;
import view.Dobrodosli;
import view.model.ModelTableSanker;

/**
 *
 * @author Nevena
 */
public class KontrolerDobrodosli {
    public void popuniTabelu(JTable jTable1){
        
        try {
            TObjekat posalji = new TObjekat(null, "nenapravljeneStavke");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            List<StavkaPorudzbine> stavke = (List<StavkaPorudzbine>)odgovor.getObjekat();
            
            ModelTableSanker mts = new ModelTableSanker(stavke);
            jTable1.setModel(mts);
        } catch (IOException ex) {
            Logger.getLogger(Dobrodosli.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dobrodosli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void napravljenaStavka(JTable jTable1, JFrame dobrodosli){
        int brojRedova = jTable1.getModel().getRowCount();
        int rb = jTable1.getSelectedRow();
        if (rb == -1) {
            JOptionPane.showMessageDialog(null, "Selektuj red!");
        } else {
            try {
                ModelTableSanker mts = (ModelTableSanker) jTable1.getModel();
                StavkaPorudzbine stavka = mts.vratiObjekat(rb);
                
                TObjekat posalji = new TObjekat(stavka, "napravljenaStavka");
                KomunikacijaKlijent.vratiObjekat().posalji(posalji);
                TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
                if (odgovor.getPoruka().equals("ok")) {
                    JOptionPane.showMessageDialog(null, "Sačuvano.");
                    mts.obrisiRed(rb);
                }
                else JOptionPane.showMessageDialog(null, "Neuspešna izmena stavke.");
                
            } catch (IOException ex) {
                Logger.getLogger(Dobrodosli.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Dobrodosli.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
