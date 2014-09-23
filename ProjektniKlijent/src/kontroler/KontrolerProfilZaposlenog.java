/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kontroler;

import domen.Zaposleni;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;
import view.Dobrodosli;
import view.konobar.ProfilZaposlenog;

/**
 *
 * @author Nevena
 */
public class KontrolerProfilZaposlenog {
    
    public void sacuvaj(JTextField textID, JTextField textIme, JTextField textPrezime,
            JTextField textKorisnickoIme, JTextField textKorisnickaSifra, 
            Zaposleni zaposleni, JFrame profil){
        int id = Integer.valueOf(textID.getText());
        String ime = textIme.getText();
        String prezime = textPrezime.getText();
        String korisnickoIme = textKorisnickoIme.getText();
        String korisnickaSifra = textKorisnickaSifra.getText();
        
        if (validacija(textID, textIme, textPrezime, textKorisnickoIme, textKorisnickaSifra)){
            try {
                Zaposleni izmenjenZaposleni = new Zaposleni(id, ime, prezime, korisnickoIme, korisnickaSifra);
                TObjekat posalji = new TObjekat(izmenjenZaposleni, "izmenaProfila");
                KomunikacijaKlijent.vratiObjekat().posalji(posalji);
                TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
                
                if (odgovor.getPoruka().equals("ok")) {
                    zaposleni = izmenjenZaposleni;
                    Dobrodosli dobrodosli = new Dobrodosli(zaposleni);
                    dobrodosli.postaviZaposlenog(zaposleni);
                    String ispis = "Podaci su uspešno izmenjeni.\n\nIme: "+zaposleni.getIme()+
                            "\nPrezime: "+zaposleni.getPrezime()+
                            "\nKorisničko ime: "+zaposleni.getKorisnickoIme()+
                            "\nKorisnička šifra: "+zaposleni.getKorisnickaSifra();
                    JOptionPane.showMessageDialog(profil, ispis);
                    profil.setVisible(false);
                    dobrodosli.setVisible(true);
                    dobrodosli.konobar();
                }
                else JOptionPane.showMessageDialog(profil, "Došlo je do greške prilikom izmene podataka.");
            } catch (IOException ex) {
                Logger.getLogger(ProfilZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProfilZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom izmene podataka.");
    }
    
    public void popuniPodatke(JTextField textID, JTextField textIme, JTextField textPrezime,
            JTextField textKorisnickoIme, JTextField textKorisnickaSifra, Zaposleni zaposleni){
        textID.setText(String.valueOf(zaposleni.getZaposleniID()));
        textIme.setText(zaposleni.getIme());
        textPrezime.setText(zaposleni.getPrezime());
        textKorisnickoIme.setText(zaposleni.getKorisnickoIme());
        textKorisnickaSifra.setText(zaposleni.getKorisnickaSifra());
    }
    
    public boolean validacija(JTextField textID, JTextField textIme, JTextField textPrezime,
            JTextField textKorisnickoIme, JTextField textKorisnickaSifra){
        if (textID.getText().equals("") || textIme.getText().equals("") ||
                textPrezime.getText().equals("") || textKorisnickoIme.getText().equals("") ||
                textKorisnickaSifra.getText().equals("")) return false;
        else return true; 
    }
}
