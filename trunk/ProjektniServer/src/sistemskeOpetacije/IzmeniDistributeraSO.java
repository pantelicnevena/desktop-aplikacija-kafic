/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.Distributer;
import domen.DomenskiObjekat;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class IzmeniDistributeraSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {
        
    }

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            Distributer distributer = (Distributer) domObj;
            String upit = "UPDATE Distributer SET NazivDistributera = "+
                    distributer.getNazivDistributera()+" WHERE DistributerID = "+
                    distributer.getDistributerID();
            db.izmena((Distributer) domObj, upit);
        }catch(Exception ex){
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {return null;}

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception {return null;}
   
}
