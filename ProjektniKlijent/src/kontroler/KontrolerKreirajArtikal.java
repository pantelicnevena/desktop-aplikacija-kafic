/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Artikal;
import domen.Distributer;
import domen.KategorijaArtikla;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;
import view.adminKreiraj.KreirajArtikal;

/**
 *
 * @author Nevena
 */
public class KontrolerKreirajArtikal {

    public void kreiraj(JTextField textID, JTextField textNaziv, JTextField textAmbalaza,
            JTextField textRokTrajanja, JTextField textStanjeNaZalihama, JTextField textCena,
            JComboBox comboDistributer, JComboBox comboKategorija, Artikal artikal) {
        try {
            Artikal a = pokupiPodatke(textID, textNaziv, textAmbalaza, textRokTrajanja,
                    textStanjeNaZalihama, textCena, comboDistributer, comboKategorija, artikal);
            if (validacija(textID, textNaziv, textAmbalaza, textRokTrajanja, textStanjeNaZalihama, textCena)) {
                TObjekat posalji = new TObjekat(a, "sacuvajArtikal");
                KomunikacijaKlijent.vratiObjekat().posalji(posalji);
                TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
                JOptionPane.showMessageDialog(null, "Sistem je uspešno kreirao artikal.");
            }
            else{
                JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom kreiranja artikla. Proverite podatke.");
            }
        } catch (IOException ex) {
            Logger.getLogger(KreirajArtikal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KreirajArtikal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void izmeni(JTextField textID, JTextField textNaziv, JTextField textAmbalaza,
            JTextField textRokTrajanja, JTextField textStanjeNaZalihama, JTextField textCena,
            JComboBox comboDistributer, JComboBox comboKategorija, Artikal artikal) {
        try {
            Artikal a = popuniPoljaZaIzmenu(textID, textNaziv, textAmbalaza, textRokTrajanja,
                    textStanjeNaZalihama, textCena, comboDistributer, comboKategorija, artikal);
            TObjekat posalji = new TObjekat(a, "izmeniArtikal");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            if (odgovor.getPoruka().equals("ok")) {
                JOptionPane.showMessageDialog(null, "Sistem je zapamtio podatke o artiklu. ");
            } else {
                JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti artikal.");
            }
        } catch (IOException ex) {
            Logger.getLogger(KreirajArtikal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KreirajArtikal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Artikal pokupiPodatke(JTextField textID, JTextField textNaziv, JTextField textAmbalaza,
            JTextField textRokTrajanja, JTextField textStanjeNaZalihama, JTextField textCena,
            JComboBox comboDistributer, JComboBox comboKategorija, Artikal artikal) {
        try {
            int artikalID = Integer.valueOf(textID.getText());
            String nazivArtikla = textNaziv.getText();
            String ambalaza = textAmbalaza.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date datumUtil = sdf.parse(textRokTrajanja.getText());
            java.sql.Date rokTrajanja = new java.sql.Date(datumUtil.getTime());
            double stanje = Double.valueOf(textStanjeNaZalihama.getText());
            double cena = Double.valueOf(textCena.getText());
            Distributer distributer = (Distributer) comboDistributer.getSelectedItem();
            int distributerID = distributer.getDistributerID();
            KategorijaArtikla kategorija = (KategorijaArtikla) comboKategorija.getSelectedItem();
            int kategorijaID = kategorija.getKategorijaID();
            artikal = new Artikal(artikalID, nazivArtikla, ambalaza, rokTrajanja, stanje, cena, distributer, kategorija);
            System.out.println("Artikal: " + artikal);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Unesite datum u formatu dd.MM.yyyy");
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Niste uneli vrednost u pravom formatu. Molimo Vas da unesete broj u poljima gde je to potrebno.");
        }
        return artikal;
    }

    public void popuniCombo(JComboBox comboKategorija, JComboBox comboDistributer) {
        try {
            TObjekat posalji = new TObjekat(null, "listaKategorija");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            List<KategorijaArtikla> kategorije = (List<KategorijaArtikla>) odgovor.getObjekat();
            comboKategorija.setModel(new DefaultComboBoxModel(kategorije.toArray()));

            TObjekat posalji2 = new TObjekat(null, "listaDistributera");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji2);
            TObjekat odgovor2 = KomunikacijaKlijent.vratiObjekat().procitaj();
            List<Distributer> distributeri = (List<Distributer>) odgovor2.getObjekat();
            comboDistributer.setModel(new DefaultComboBoxModel(distributeri.toArray()));
        } catch (IOException ex) {
            Logger.getLogger(KreirajArtikal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KreirajArtikal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void vratiID(JTextField textID) {
        try {
            TObjekat posalji = new TObjekat(null, "vratiArtikalID");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            int artikalID = (int) odgovor.getObjekat();

            textID.setText(String.valueOf(artikalID));
        } catch (IOException ex) {
            Logger.getLogger(KreirajArtikal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KreirajArtikal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Artikal popuniPoljaZaIzmenu(JTextField textID, JTextField textNaziv, JTextField textAmbalaza,
            JTextField textRokTrajanja, JTextField textStanjeNaZalihama, JTextField textCena,
            JComboBox comboDistributer, JComboBox comboKategorija, Artikal artikal) {
        try {
            int artikalID = Integer.valueOf(textID.getText());
            String nazivArtikla = textNaziv.getText();
            String ambalaza = textAmbalaza.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datumUtil = sdf.parse(textRokTrajanja.getText());
            java.sql.Date rokTrajanja = new java.sql.Date(datumUtil.getTime());
            double stanje = Double.valueOf(textStanjeNaZalihama.getText());
            double cena = Double.valueOf(textCena.getText());
            Distributer distributer = (Distributer) comboDistributer.getSelectedItem();
            int distributerID = distributer.getDistributerID();
            KategorijaArtikla kategorija = (KategorijaArtikla) comboKategorija.getSelectedItem();
            int kategorijaID = kategorija.getKategorijaID();
            artikal = new Artikal(artikalID, nazivArtikla, ambalaza, rokTrajanja, stanje, cena, distributer, kategorija);
        } catch (ParseException ex) {
            Logger.getLogger(KreirajArtikal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Niste uneli vrednost u pravom formatu. Molimo Vas da unesete broj u poljima gde je to potrebno.");
        }
        return artikal;
    }

    public void popuniPolja(JTextField textID, JTextField textNaziv, JTextField textAmbalaza,
            JTextField textRokTrajanja, JTextField textStanjeNaZalihama, JTextField textCena,
            JComboBox comboDistributer, JComboBox comboKategorija, Artikal a) {
        textID.setText(String.valueOf(a.getArtikalID()));
        textNaziv.setText(a.getNazivArtikla());
        textAmbalaza.setText(a.getAmbalaza());
        textRokTrajanja.setText(String.valueOf(a.getRokTrajanja()));
        textCena.setText(String.valueOf(a.getCena()));
        textStanjeNaZalihama.setText(String.valueOf(a.getStanjeNaZalihama()));
        comboDistributer.setSelectedItem(a.getDistributer());
        comboKategorija.setSelectedItem(a.getKategorija());
    }

    public boolean validacija(JTextField textID, JTextField textNaziv, JTextField textAmbalaza,
            JTextField textRokTrajanja, JTextField textStanjeNaZalihama, JTextField textCena) {
        if (textNaziv.getText().equals("")) {
            return false;
        }
        if (textAmbalaza.getText().equals("")) {
            return false;
        }
        if (textRokTrajanja.getText().equals("")) {
            return false;
        }
        if (textStanjeNaZalihama.getText().equals("")) {
            return false;
        }
        if (textCena.getText().equals("")) {
            return false;
        }
        return true;
    }
}
