/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kontroler;

import domen.Porudzbina;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import komunikacija.KomunikacijaKlijent;
import transfer.TObjekat;
import view.konobar.NenaplacenePorudzbine;
import view.model.ModelTablePorudzbine;

/**
 *
 * @author Nevena
 */
public class KontrolerNenaplacenePorudzbine {
    
    public void naplati(JTable jTable1, JFrame nenaplacene){
        int brojRedova = jTable1.getModel().getRowCount();
        int rb = jTable1.getSelectedRow();
        if (rb == -1) {
            JOptionPane.showMessageDialog(nenaplacene, "Selektuj red!");
        } else {
            try {
                ModelTablePorudzbine mtp = (ModelTablePorudzbine) jTable1.getModel();
                Porudzbina porudzbina = mtp.vratiObjekat(rb);

                TObjekat posalji = new TObjekat(porudzbina, "naplacenaPorudzbina");
                KomunikacijaKlijent.vratiObjekat().posalji(posalji);
                TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();

                if (odgovor.getPoruka().equals("ok")) {
                    JOptionPane.showMessageDialog(nenaplacene, "Sačuvano.");
                    mtp.obrisiRed(rb);
                } else {
                    JOptionPane.showMessageDialog(nenaplacene, "Neuspešna naplata porudžbine.");
                }
            } catch (IOException ex) {
                Logger.getLogger(NenaplacenePorudzbine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NenaplacenePorudzbine.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public void popuniTabelu(JTable jTable1) {
        try {
            TObjekat posalji = new TObjekat(null, "nenaplacenePorudzbine");
            KomunikacijaKlijent.vratiObjekat().posalji(posalji);
            TObjekat odgovor = KomunikacijaKlijent.vratiObjekat().procitaj();
            List<Porudzbina> nenaplacene = (List<Porudzbina>) odgovor.getObjekat();

            for (int i = 0; i < nenaplacene.size(); i++) {
                Porudzbina p = (Porudzbina) nenaplacene.get(i);
                for (int j = i + 1; j < nenaplacene.size(); j++) {
                    if (nenaplacene.get(j).getPorudzbinaID() == p.getPorudzbinaID()) {
                        nenaplacene.remove(nenaplacene.get(i));
                    }
                }
            }

            ModelTablePorudzbine mtp = new ModelTablePorudzbine(nenaplacene);
            jTable1.setModel(mtp);
        } catch (IOException ex) {
            Logger.getLogger(NenaplacenePorudzbine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NenaplacenePorudzbine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
