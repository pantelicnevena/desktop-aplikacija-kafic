/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.DomenskiObjekat;
import domen.Porudzbina;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class IzmeniPorudzbinuSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {
        
    }

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            Porudzbina porudzbina = (Porudzbina) domObj;
            String razduzeno;
            if (porudzbina.isRazduzeno()) razduzeno = "TRUE";
            else razduzeno = "FALSE";
            String upit = "UPDATE Porudzbina SET DatumPorudzbine = '"+porudzbina.getDatumPorudzbine()+
                    "', Razduzeno = "+razduzeno+" WHERE PorudzbinaID = "+porudzbina.getPorudzbinaID();
            db.izmena((Porudzbina) domObj, upit);
        }catch(Exception ex){
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {return null;}

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception {return null;}

}
