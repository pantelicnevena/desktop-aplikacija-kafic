/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.Artikal;
import domen.DomenskiObjekat;
import domen.Porudzbina;
import domen.StavkaPorudzbine;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class IzmeniStavkeSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {}

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            Porudzbina porudzbina = (Porudzbina) domObj;
            String upit = kreirajUpitZaDelete(porudzbina);
            db.obrisi(upit);
        }catch (Exception ex){
            
        }
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String kreirajUpitZaDelete (Porudzbina porudzbina) {
        return "DELETE FROM StavkaPorudzbine WHERE PorudzbinaID = " + porudzbina.getPorudzbinaID();
    }
    
}
