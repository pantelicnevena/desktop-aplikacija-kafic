/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kontroler;

import domen.Artikal;
import domen.KategorijaArtikla;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;
import view.adminKreiraj.KreirajArtikal;
import view.adminPregled.PregledArtikala;
import view.model.ModelTableArtikli;

/**
 *
 * @author Nevena
 */
public class KontrolerPregledArtikala {
    
    public void obrisi(JTable jTable1, JFrame pregled){
        int brojRedova = jTable1.getModel().getRowCount();
        int rb = jTable1.getSelectedRow();
        if (rb == -1) {
            JOptionPane.showMessageDialog(pregled, "Selektuj red!");
        } else {
            try {
                ModelTableArtikli mta = (ModelTableArtikli) jTable1.getModel();
                Artikal artikal = mta.vratiObjekat(rb);
                
                TObjekat posalji = new TObjekat(artikal, "obrisiArtikal");
                KomunikacijaKlijent.vratiObjekat().posalji(posalji);
                TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
                if (odgovor.getPoruka().equals("ok")) JOptionPane.showMessageDialog(null, "Sistem je obrisao artikal.");
                else JOptionPane.showMessageDialog(null, "Sistem ne može da obriše artikal");
                mta.obrisiRed(rb);
            } catch (IOException ex) {
                Logger.getLogger(PregledArtikala.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PregledArtikala.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void pronadji(JComboBox comboKategorija, JTable jTable1){
        try {
            KategorijaArtikla kategorija = (KategorijaArtikla) comboKategorija.getSelectedItem();
            TObjekat posalji = new TObjekat(null, "listaArtikala");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            if (odgovor.getPoruka().equals("ok")) JOptionPane.showMessageDialog(null, "Sistem je izvršio pretragu artikala.");
            else JOptionPane.showMessageDialog(null, "Sistem ne može da nađe artikle po zadatim vrednostima");
            List<Artikal> artikli = (List<Artikal>) odgovor.getObjekat();
            List<Artikal> pronadjeniArtikli = new ArrayList<>();

            for (int i = 0; i < artikli.size(); i++) {
                if (artikli.get(i).getKategorija().equals(kategorija)) {
                    pronadjeniArtikli.add(artikli.get(i));
                }
            }

            ModelTableArtikli mta = new ModelTableArtikli(pronadjeniArtikli);
            jTable1.setModel(mta);

        } catch (IOException ex) {
            Logger.getLogger(PregledArtikala.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PregledArtikala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void izmeni(JTable jTable1, JFrame pregled){
        int brojRedova = jTable1.getModel().getRowCount();
        int rb = jTable1.getSelectedRow();
        if (rb == -1) {
            JOptionPane.showMessageDialog(pregled, "Selektuj red!");
        } else {
            ModelTableArtikli mta = (ModelTableArtikli) jTable1.getModel();
            Artikal artikal = mta.vratiObjekat(rb);
            
            KreirajArtikal ka = new KreirajArtikal(artikal);
            ka.setVisible(true);
        }
    }
    
    public void popuniCombo(JComboBox comboKategorija) {
        try {
            TObjekat posalji = new TObjekat(null, "listaKategorija");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            List<KategorijaArtikla> kategorije = (List<KategorijaArtikla>) odgovor.getObjekat();

            comboKategorija.setModel(new DefaultComboBoxModel(kategorije.toArray()));
        } catch (IOException ex) {
            Logger.getLogger(PregledArtikala.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PregledArtikala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void popuniTabelu(JTable jTable1) {
        try {
            TObjekat posalji = new TObjekat(null, "listaArtikala");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();

            List<Artikal> artikli = (List<Artikal>) odgovor.getObjekat();
            ModelTableArtikli mta = new ModelTableArtikli(artikli);
            jTable1.setModel(mta);
        } catch (IOException ex) {
            Logger.getLogger(PregledArtikala.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PregledArtikala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
