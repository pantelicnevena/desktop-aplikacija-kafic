/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.DomenskiObjekat;
import domen.Zaposleni;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class IzbrisiZaposlenogSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            Zaposleni zaposleni = (Zaposleni) domObj;
            String upitZ = kreirajUpitZaDeleteZaposlenog(zaposleni);
            db.obrisi(upitZ);
        }catch (Exception ex){
            
        }
    }
    
    public String kreirajUpitZaDeleteZaposlenog (Zaposleni zaposleni) {
        return "DELETE FROM Zaposleni WHERE ZaposleniID=" + zaposleni.getZaposleniID();
    }
    
    
    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {return null;}

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception {return null;}
    
    
    
}
