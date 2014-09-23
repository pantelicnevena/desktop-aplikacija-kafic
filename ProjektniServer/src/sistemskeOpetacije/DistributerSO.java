/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.Distributer;
import domen.DomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class DistributerSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            db.sacuvaj(domObj);
        }catch(Exception ex){
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {
        List<DomenskiObjekat> distributeri = new ArrayList<>();
        
        ResultSet rs =  db.vratiDomObj(dom);
        while(rs.next()){
            Distributer distributer = new Distributer();
            distributer.setDistributerID(rs.getInt("DistributerID"));
            distributer.setNazivDistributera(rs.getString("NazivDistributera"));
            distributeri.add(distributer);
        }
        return distributeri;
    }

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String where) throws Exception {
        List<DomenskiObjekat> distributeri = new ArrayList<>();
        ResultSet rs = db.nadji(dom, kreirajUpit(dom, where));
        while(rs.next()){
            Distributer distributer = new Distributer();
            distributer.setDistributerID(rs.getInt("DistributerID"));
            distributer.setNazivDistributera(rs.getString("NazivDistributera"));
            distributeri.add(distributer);
        }
        return distributeri;
    }
    
    public String kreirajUpit (DomenskiObjekat dom, String where){
        Distributer distributer = (Distributer) dom;
        String upit = "SELECT * From Distributer WHERE "+where;
        return upit;
    }
    
}
