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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class StavkaSO extends OpstaSO{

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
            StavkaPorudzbine stavka = (StavkaPorudzbine) domObj;
            String upit = "UPDATE StavkaPorudzbine SET Napravljeno = 1 WHERE RedniBrojStavke = "+stavka.getRedniBrojStavke();
            db.izmena((StavkaPorudzbine) domObj, upit);
        }catch(Exception ex){
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {
        List<DomenskiObjekat> stavke = new ArrayList<>();
        
        ResultSet rs =  db.vratiDomObj(dom);
        while(rs.next()){
            StavkaPorudzbine stavka = new StavkaPorudzbine();
            stavka.setRedniBrojStavke(rs.getInt("RedniBrojStavke"));
            stavka.setKolicina(rs.getInt("Kolicina"));
            stavka.setNapravljeno(rs.getInt("Napravljeno"));
                Artikal artikal = new Artikal();
                artikal.setArtikalID(rs.getInt("ArtikalID"));
                artikal.setNazivArtikla(rs.getString("NazivArtikla"));
                artikal.setAmbalaza(rs.getString("Ambalaza"));
                artikal.setRokTrajanja(rs.getDate("RokTrajanja"));
                artikal.setStanjeNaZalihama(rs.getDouble("StanjeNaZalihama"));
                artikal.setCena(rs.getDouble("Cena"));
                Porudzbina porudzbina = new Porudzbina();
                porudzbina.setPorudzbinaID(rs.getInt("PorudzbinaID"));
                porudzbina.setRazduzeno(rs.getBoolean("Razduzeno"));
                porudzbina.setDatumPorudzbine(rs.getDate("DatumPorudzbine"));
            stavka.setArtikal(artikal);
            stavka.setPorudzbina(porudzbina);
            stavke.add(stavka);
        }
        return stavke;
    }

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String where) throws Exception {
        List<DomenskiObjekat> stavke = new ArrayList<>();
        ResultSet rs = db.nadji(dom, kreirajUpit(dom, where));
        while(rs.next()){
            StavkaPorudzbine stavka = new StavkaPorudzbine();
            stavka.setRedniBrojStavke(rs.getInt("RedniBrojStavke"));
            stavka.setKolicina(rs.getInt("Kolicina"));
            stavka.setNapravljeno(rs.getInt("Napravljeno"));
                Porudzbina porudzbina = new Porudzbina();
                porudzbina.setPorudzbinaID(rs.getInt("PorudzbinaID"));
                porudzbina.setDatumPorudzbine(rs.getDate("DatumPorudzbine"));
                porudzbina.setRazduzeno(rs.getBoolean("Razduzeno"));
                Artikal artikal = new Artikal();
                artikal.setArtikalID(rs.getInt("ArtikalID"));
                artikal.setAmbalaza(rs.getString("Ambalaza"));
                artikal.setNazivArtikla(rs.getString("NazivArtikla"));
                artikal.setCena(rs.getDouble("Cena"));
                artikal.setRokTrajanja(rs.getDate("RokTrajanja"));
            stavka.setPorudzbina(porudzbina);
            stavka.setArtikal(artikal);
            stavke.add(stavka);
        }
        System.out.println(""+stavke);
        return stavke;
    }

    @Override
    protected void izbrisi(DomenskiObjekat dom) throws Exception {
        try{
            StavkaPorudzbine stavka = (StavkaPorudzbine) dom;
            String upit = kreirajUpitZaDelete(stavka);
            db.obrisi(upit);
        }catch (Exception ex){
            System.out.println("Došlo je do greške prilikom brisanja stavke.");
        }
    }
    
    public String kreirajUpit(DomenskiObjekat dom, String where){
        String upit = "SELECT * FROM Porudzbina INNER JOIN "+
                "(StavkaPorudzbine INNER JOIN Artikal "+
                "ON StavkaPorudzbine.ArtikalID = Artikal.ArtikalID) "+
                "ON Porudzbina.PorudzbinaID = StavkaPorudzbine.PorudzbinaID WHERE "+where;
        return upit;
    }
    
    public String kreirajUpitZaDelete (StavkaPorudzbine stavka) {
        return "DELETE FROM StavkaPorudzbine WHERE RedniBrojStavke=" + stavka.getRedniBrojStavke();
    }
    
}
