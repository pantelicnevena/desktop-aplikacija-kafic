/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.Artikal;
import domen.DomenskiObjekat;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class IzbrisiArtikalSO extends OpstaSO{    

    @Override
    protected void proveriPreduslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            Artikal artikal = (Artikal) domObj;
            String upit = kreirajUpitZaDelete(artikal);
            db.obrisi(upit);
        }catch (Exception ex){
            
        }
    }
    
    public String kreirajUpitZaDelete (Artikal artikal) {
        return "DELETE FROM Artikal WHERE ArtikalID=" + artikal.getArtikalID();
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {return null;}

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception {return null;}

    
    
}
