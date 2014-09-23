/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kontroler;

import domen.Artikal;
import domen.Ulaz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;
import view.UlazArtikala;
import view.model.ModelTableUlazArtikala;

/**
 *
 * @author Nevena
 */
public class KontrolerUlazArtikala {
    public void sacuvajUlaz(JTable jTable1){
        try {
            int brojRedova = jTable1.getModel().getRowCount();
            int brojKolona = jTable1.getModel().getColumnCount();
            List<Ulaz> listaUlaza = new ArrayList<>();
            List<Artikal> listaArtikala = new ArrayList<>();
            
            for (int i = 0; i < brojRedova; i++) {
                ModelTableUlazArtikala mtua = (ModelTableUlazArtikala)jTable1.getModel();
                Ulaz ulaz = mtua.vratiObjekat(i);
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
                String ispis = "Sistem je zapamtio izmenu količine artikala.\n";
                for (int i = 0; i<listaArtikala.size(); i++)
                    ispis += "Artikal: "+listaArtikala.get(i).getNazivArtikla()+
                            ", novo stanje na zalihama: "+listaArtikala.get(i).getStanjeNaZalihama()+
                            ", nova cena: "+listaArtikala.get(i).getCena()+" din.\n";
                JOptionPane.showMessageDialog(null, ispis);
                popuniTabelu(jTable1);
            }else{
                JOptionPane.showMessageDialog(null, "Sistem ne može da nađe artikle po zadatim vrednostima.");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(UlazArtikala.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UlazArtikala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void popuniTabelu(JTable jTable1) {
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
