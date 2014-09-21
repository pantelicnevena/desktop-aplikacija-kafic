/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.Artikal;
import domen.Distributer;
import domen.DomenskiObjekat;
import domen.KategorijaArtikla;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class ArtikalSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {
        
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
    protected void izmeniObjekat(DomenskiObjekat domObj) throws Exception {
        try{
            db.ulazArtikala((Artikal) domObj);
        }catch(Exception ex){
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {
        List<DomenskiObjekat> artikli = new ArrayList<>();
        
        ResultSet rs =  db.vratiDomObj(dom);
        while(rs.next()){
            Artikal artikal = new Artikal();
            artikal.setArtikalID(rs.getInt("ArtikalID"));
            artikal.setNazivArtikla(rs.getString("NazivArtikla"));
            artikal.setAmbalaza(rs.getString("Ambalaza"));
            artikal.setRokTrajanja(rs.getDate("RokTrajanja"));
            artikal.setStanjeNaZalihama(rs.getDouble("StanjeNaZalihama"));
            artikal.setCena(rs.getDouble("Cena"));
                Distributer distributer = new Distributer();
                distributer.setDistributerID(rs.getInt("DistributerID"));
                distributer.setNazivDistributera(rs.getString("NazivDistributera"));
                KategorijaArtikla kategorija = new KategorijaArtikla();
                kategorija.setKategorijaID(rs.getInt("KategorijaID"));
                kategorija.setNazivKategorije(rs.getString("NazivKategorije"));
            artikal.setDistributer(distributer);
            artikal.setKategorija(kategorija);
            artikli.add(artikal);
        }
        return artikli;
    }

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String where) throws Exception {
        List<DomenskiObjekat> artikli = new ArrayList<>();
        ResultSet rs = db.nadji(dom, kreirajUpit(dom, where));
        while(rs.next()){
            Artikal artikal = new Artikal();
            artikal.setArtikalID(rs.getInt("ArtikalID"));
            artikal.setNazivArtikla(rs.getString("NazivArtikla"));
            artikal.setAmbalaza(rs.getString("Ambalaza"));
            artikal.setRokTrajanja(rs.getDate("RokTrajanja"));
            artikal.setStanjeNaZalihama(rs.getDouble("StanjeNaZalihama"));
            artikal.setCena(rs.getDouble("Cena"));
                Distributer distributer = new Distributer();
                distributer.setDistributerID(rs.getInt("DistributerID"));
                distributer.setNazivDistributera(rs.getString("NazivDistributera"));
                KategorijaArtikla kategorija = new KategorijaArtikla();
                kategorija.setKategorijaID(rs.getInt("KategorijaID"));
                kategorija.setNazivKategorije(rs.getString("NazivKategorije"));
            artikal.setDistributer(distributer);
            artikal.setKategorija(kategorija);
            artikli.add(artikal);
        }
        return artikli;
    }

    @Override
    protected void izbrisi(DomenskiObjekat dom) throws Exception {
        try{
            Artikal artikal = (Artikal) dom;
            String upit = kreirajUpitZaDelete(artikal);
            db.obrisi(upit);
        }catch (Exception ex){
            
        }
    }
    
    public String kreirajUpit(DomenskiObjekat dom, String where){
        Artikal artikal = (Artikal) dom;
        String upit = "SELECT * From Artikal WHERE "+where;
        return upit;
    }
    
    public String kreirajUpitZaDelete (Artikal artikal) {
        return "DELETE FROM Artikal WHERE ArtikalID=" + artikal.getArtikalID();
    }

    
}
