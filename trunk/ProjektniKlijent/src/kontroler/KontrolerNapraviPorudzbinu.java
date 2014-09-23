/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kontroler;

import domen.Artikal;
import domen.NovaPorudzbina;
import domen.Porudzbina;
import domen.Razduzenje;
import domen.StavkaPorudzbine;
import domen.Zaposleni;
import java.awt.Checkbox;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;
import view.konobar.NapraviPorudzbinu;
import view.model.ModelTableNapraviPorudzbinu;

/**
 *
 * @author Nevena
 */
public class KontrolerNapraviPorudzbinu {
    
    public void sacuvaj(JTable jTable1, Zaposleni zaposleni, JTextField textBroj,
            JTextField textDatum, JCheckBox checkBox1){
        try {
            int brojRedova = jTable1.getModel().getRowCount();
            TObjekat posalji = new TObjekat(zaposleni, "vratiRazduzenjeID");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            int razduzenjeID = (int) odgovor.getObjekat();
            //**RAZDUZENJE
            int porudzbinaID = Integer.valueOf(textBroj.getText());
            Porudzbina rp = new Porudzbina();
            rp.setPorudzbinaID(porudzbinaID);
            Razduzenje r = new Razduzenje(razduzenjeID, 0, zaposleni, rp);
            
            //** PORUDZBINA
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date datumUtil = sdf.parse(textDatum.getText());
            java.sql.Date datumPorudzbine = new java.sql.Date(datumUtil.getTime());
            Boolean razduzeno = false;
            if (checkBox1.isSelected()) razduzeno = true;
            Porudzbina p = new Porudzbina(porudzbinaID, datumPorudzbine, razduzeno, zaposleni, r);
            
            //** STAVKE
            List<StavkaPorudzbine> stavke = new ArrayList<>();
            int ukupnaVrednost = 0;
            
            TObjekat posalji3 = new TObjekat(null, "vratiStavkaID");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji3);
            TObjekat odgovor3 = KomunikacijaKlijent.vratiObjekat().procitaj();
            int redniBroj = (int) odgovor3.getObjekat();
            
            for (int i = 0; i<brojRedova; i++){
                ModelTableNapraviPorudzbinu mtnp = (ModelTableNapraviPorudzbinu) jTable1.getModel();
                NovaPorudzbina np = mtnp.vratiObjekat(i);
                if (np.getArtikal() != null && np.getKolicina() != 0){
                ukupnaVrednost += np.getCena();
                Artikal artikal = np.getArtikal();
                StavkaPorudzbine sp = new StavkaPorudzbine(redniBroj, np.getKolicina(), 0, artikal, p);
                redniBroj++;
                stavke.add(sp);}
                else{
                    JOptionPane.showMessageDialog(null, "Sistem nije uspeo da sačuva podatke o porudžbini. Morate popuniti sve podatke.");
                }
            }
            
            if (stavke.size() == 0){ 
                JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom čuvanja podataka. Proverite podatke i pokušajte ponovo.");
            }else{
                for(int i = 0; i<stavke.size(); i++) stavke.get(i).getPorudzbina().getRazduzenje().setUkupnaVrednost(ukupnaVrednost);
                System.out.println("Stavke: "+stavke);

                TObjekat posalji2 = new TObjekat(stavke, "napraviPorudzbinu");
                KomunikacijaKlijent.vratiObjekat().posalji(posalji2);
                TObjekat odgovor2 = KomunikacijaKlijent.vratiObjekat().procitaj();
                System.out.println(""+odgovor2);
                if (odgovor2.getPoruka().equals("ok")) {
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio porudžbinu");
                    int id = Integer.valueOf(textBroj.getText());
                    int noviID = id + 1;
                    textBroj.setText(String.valueOf(noviID));
                }
                else JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti novu porudžbinu");
            }
        } catch (ParseException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void izmeni(JTable jTable1, Zaposleni zaposleni, JTextField textBroj,
            JTextField textDatum, JCheckBox checkBox1){
        try {
            int id = Integer.valueOf(textBroj.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datumUtil = sdf.parse(textDatum.getText());
            java.sql.Date datum = new java.sql.Date(datumUtil.getTime());
            boolean razduzeno;
            if (checkBox1.isSelected()) razduzeno = true;
            else razduzeno = false;
            Porudzbina p = new Porudzbina();
            p.setPorudzbinaID(id);
            Razduzenje r = new Razduzenje(id, 0, zaposleni, p);
            Porudzbina porudzbina = new Porudzbina(id, datum, razduzeno, zaposleni, r);
            
            TObjekat posalji = new TObjekat(porudzbina, "izmeniPorudzbinu");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            List<StavkaPorudzbine> stavke = new ArrayList<>();
            
            TObjekat posalji3 = new TObjekat(null, "vratiStavkaID");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji3);
            TObjekat odgovor3 = KomunikacijaKlijent.vratiObjekat().procitaj();
            int redniBroj = (int) odgovor3.getObjekat();
            
            int brojRedova = jTable1.getModel().getRowCount();
            int ukupnaVrednost = 0;
            
            for (int i = 0; i<brojRedova; i++){
                ModelTableNapraviPorudzbinu mtnp = (ModelTableNapraviPorudzbinu) jTable1.getModel();
                NovaPorudzbina np = mtnp.vratiObjekat(i);
                ukupnaVrednost += np.getCena();
                Artikal artikal = np.getArtikal();
                StavkaPorudzbine sp = new StavkaPorudzbine(redniBroj, np.getKolicina(), 0, artikal, porudzbina);
                redniBroj++;
                stavke.add(sp);
            }
            System.out.println(""+stavke);
            for(int i = 0; i<stavke.size(); i++) 
                stavke.get(i).getPorudzbina().getRazduzenje().setUkupnaVrednost(ukupnaVrednost);
            
            TObjekat posalji2 = new TObjekat(stavke, "napraviStavke");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji2);
            TObjekat odgovor2 = KomunikacijaKlijent.vratiObjekat().procitaj();
            
        } catch (ParseException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void izmeniPorudzbinu(Porudzbina p, JTable jTable1, Zaposleni zaposleni, 
            JTextField textBroj, JTextField textDatum, JCheckBox checkBox1){
        try {
            String id = String.valueOf(p.getPorudzbinaID());
            textBroj.setText(id);
            textDatum.setText(String.valueOf(p.getDatumPorudzbine()));
            if (p.isRazduzeno()) checkBox1.setSelected(true);
            else checkBox1.setSelected(false);
            
            TObjekat posalji = new TObjekat(p, "stavkePorudzbine");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            List<StavkaPorudzbine> stavke = (List<StavkaPorudzbine>) odgovor.getObjekat();
            List<NovaPorudzbina> np = new ArrayList<>();
            
            for (int i = 0; i<stavke.size(); i++){
                NovaPorudzbina novaPorudzbina = new NovaPorudzbina();
                novaPorudzbina.setArtikal(stavke.get(i).getArtikal());
                novaPorudzbina.setKolicina(stavke.get(i).getKolicina());
                int kolicina = novaPorudzbina.getKolicina();
                int cena = (int) novaPorudzbina.getArtikal().getCena();
                int ukupnaCena = kolicina*cena;
                novaPorudzbina.setCena(ukupnaCena);
                np.add(novaPorudzbina);
            }
            
            popuniTabeluIzmene(jTable1, zaposleni, np);
            
        } catch (IOException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void popuniTabelu(JTable jTable1, Zaposleni zaposleni){
        try {
            ModelTableNapraviPorudzbinu mtnp = new ModelTableNapraviPorudzbinu();
            jTable1.setModel(mtnp);
            
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
        } catch (IOException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void popuniTabeluIzmene(JTable jTable1, Zaposleni zaposleni, List<NovaPorudzbina> np){
        try {
            ModelTableNapraviPorudzbinu mtnp = new ModelTableNapraviPorudzbinu(np);
            jTable1.setModel(mtnp);
            
            TObjekat posalji = new TObjekat(zaposleni, "listaArtikala");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            List<Artikal> artikli = (List<Artikal>) odgovor.getObjekat();
            
            JComboBox comboArtikli = new JComboBox();
            for (Artikal a : artikli){
                comboArtikli.addItem(a);
            }
            
            TableColumn tcArtikal = jTable1.getColumnModel().getColumn(0);
            tcArtikal.setCellEditor(new DefaultCellEditor(comboArtikli));
        } catch (IOException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void vratiID(JTextField textBroj, Zaposleni zaposleni){
        try {
            TObjekat posalji = new TObjekat(zaposleni, "vratiPorudzbinaID");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            int id = (int) odgovor.getObjekat();
            textBroj.setText(String.valueOf(id));
        } catch (IOException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NapraviPorudzbinu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validacija(){
        return true;
    }
}
