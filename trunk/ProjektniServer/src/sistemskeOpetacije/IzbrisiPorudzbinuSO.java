/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.DomenskiObjekat;
import domen.Porudzbina;
import domen.Zaposleni;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class IzbrisiPorudzbinuSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {}

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            Zaposleni zaposleni = (Zaposleni) domObj;
            String upit = kreirajUpitZaDelete(zaposleni);
            db.obrisi(upit);
        }catch (Exception ex){
            
        }
    }
    
    public String kreirajUpitZaDelete (Zaposleni zaposleni) {
        return "DELETE FROM Porudzbina WHERE ZaposleniID = " + zaposleni.getZaposleniID();
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {return null;}

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception {return null;}
    
    
    
}
