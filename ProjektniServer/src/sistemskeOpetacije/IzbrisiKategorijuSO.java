/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.DomenskiObjekat;
import domen.KategorijaArtikla;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class IzbrisiKategorijuSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {
        
    }

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            KategorijaArtikla kategorija = (KategorijaArtikla) domObj;
            String upit = kreirajUpitZaDelete(kategorija);
            db.obrisi(upit);
        }catch (Exception ex){
            
        }
    }
    
    public String kreirajUpitZaDelete (KategorijaArtikla kategorija) {
        return "DELETE FROM KategorijaArtikla WHERE KategorijaID=" + kategorija.getKategorijaID();
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {return null;}

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception {return null;}


}
