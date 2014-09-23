/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.DomenskiObjekat;
import domen.StavkaPorudzbine;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class NapravljenaStavkaSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {
        
    }

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            StavkaPorudzbine stavka = (StavkaPorudzbine) domObj;
            String upit = "UPDATE StavkaPorudzbine SET Napravljeno = 1 WHERE RedniBrojStavke = "+stavka.getRedniBrojStavke();
            db.izmena((StavkaPorudzbine) domObj, upit);
        }catch(Exception ex){
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {return null;}

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception {return null;}
    
}
