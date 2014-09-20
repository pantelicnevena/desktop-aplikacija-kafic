/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.DomenskiObjekat;
import domen.KategorijaArtikla;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class KategorijaSO extends OpstaSO{

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
        List<DomenskiObjekat> kategorije = new ArrayList<>();
        
        ResultSet rs =  db.vratiDomObj(dom);
        while(rs.next()){
            KategorijaArtikla kategorija = new KategorijaArtikla();
            kategorija.setKategorijaID(rs.getInt("KategorijaID"));
            kategorija.setNazivKategorije(rs.getString("NazivKategorije"));
            kategorije.add(kategorija);
        }
        return kategorije;
    }

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String where) throws Exception {
        List<DomenskiObjekat> kategorije = new ArrayList<>();
        ResultSet rs =  db.nadji(dom, kreirajUpit(dom, where));
        while(rs.next()){
            KategorijaArtikla kategorija = new KategorijaArtikla();
            kategorija.setKategorijaID(rs.getInt("KategorijaID"));
            kategorija.setNazivKategorije(rs.getString("NazivKategorije"));
            kategorije.add(kategorija);
        }
        return kategorije;
    }

    @Override
    protected void izbrisi(DomenskiObjekat dom) throws Exception {
        try{
            KategorijaArtikla kategorija = (KategorijaArtikla) dom;
            String upit = kreirajUpitZaDelete(kategorija);
            db.obrisi(upit);
        }catch (Exception ex){
            
        }
    }
    
    public String kreirajUpit (DomenskiObjekat dom, String where){
        KategorijaArtikla kategorija = (KategorijaArtikla) dom;
        String upit = "SELECT * From KategorijaArtikla WHERE "+where;
        return upit;
    }
    
    public String kreirajUpitZaDelete (KategorijaArtikla kategorija) {
        return "DELETE FROM KategorijaArtikla WHERE KategorijaID=" + kategorija.getKategorijaID();
    }
    
}
