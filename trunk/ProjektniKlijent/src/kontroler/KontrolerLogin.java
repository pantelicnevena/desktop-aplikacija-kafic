/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kontroler;

import domen.Provera;
import domen.Zaposleni;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;
import view.Admin;
import view.Dobrodosli;
import view.Login;

/**
 *
 * @author Nevena
 */
public class KontrolerLogin {
    public void prijaviZaposlenog(JTextField textKorisnickoIme, JTextField textSifra, JFrame login){
        String ime = textKorisnickoIme.getText();
        String sifra = textSifra.getText();
        
        if (validacija(textKorisnickoIme, textSifra)) {
            try {
                Provera provera = new Provera(ime, sifra);
                TObjekat posalji = new TObjekat(provera, "provera");
                KomunikacijaKlijent.vratiObjekat().posalji(posalji);
                TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
                List<Zaposleni> zaposleneOsobe = (List<Zaposleni>)odgovor.getObjekat();
                if (zaposleneOsobe.size() == 0) JOptionPane.showMessageDialog(login, "Sistem ne može da nađe zaposlenog na osnovu unetih vrednosti.");
                else{
                    JOptionPane.showMessageDialog(login, "Zaposleni je uspešno prijavljen.");
                    login.setVisible(false);
                    Zaposleni zaposleni = zaposleneOsobe.get(0);
                    Admin admin = new Admin();
                    Dobrodosli dobrodosli = new Dobrodosli(zaposleni);
                    if (zaposleni.getKorisnickoIme().equals("Administrator")) 
                        admin.setVisible(true);
                    else dobrodosli.setVisible(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean validacija(JTextField textKorisnickoIme, JTextField textSifra){
        String ime = textKorisnickoIme.getText();
        String sifra = textSifra.getText();
        
        if (!ime.equals("") && !sifra.equals("")) return true;
        else return false;
    }
}
