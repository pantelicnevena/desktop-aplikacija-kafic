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
public class IzmeniArtikalSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {}

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            Artikal artikal = (Artikal) domObj;
            String upit = kreirajUpitZaIzmenu(artikal);
            db.izmena((Artikal) domObj, upit);
        }catch(Exception ex){
            throw new Exception("Operacija cuvanja nije izvrsena!");
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
    
    public String kreirajUpitZaIzmenu(Artikal a){
        return "UPDATE Artikal SET NazivArtikla = '"+a.getNazivArtikla()+
                    "', Ambalaza = '"+a.getAmbalaza()+"', RokTrajanja = '"+a.getRokTrajanja()+
                    "', StanjeNaZalihama = '"+a.getStanjeNaZalihama()+
                    "', Cena = '"+a.getCena()+
                    "', KategorijaID = "+a.getKategorija().getKategorijaID()+
                    ", DistributerID = "+a.getDistributer().getDistributerID()+
                    " WHERE ArtikalID = "+a.getArtikalID();
    }
}
