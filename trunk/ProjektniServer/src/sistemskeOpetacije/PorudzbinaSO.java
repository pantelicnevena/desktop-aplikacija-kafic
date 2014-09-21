/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.DomenskiObjekat;
import domen.Porudzbina;
import domen.Razduzenje;
import domen.Zaposleni;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class PorudzbinaSO extends OpstaSO{
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
    protected void izmeniObjekat(DomenskiObjekat domObj) throws Exception {
        try{
            Porudzbina porudzbina = (Porudzbina) domObj;
            String upit = "UPDATE Porudzbina SET Razduzeno = TRUE WHERE PorudzbinaID = "+porudzbina.getPorudzbinaID();
            db.izmena((Porudzbina) domObj, upit);
        }catch(Exception ex){
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {
        List<DomenskiObjekat> porudzbine = new ArrayList<>();
        
        ResultSet rs =  db.vratiDomObj(dom);
        while(rs.next()){
            Porudzbina porudzbina = new Porudzbina();
            porudzbina.setPorudzbinaID(rs.getInt("PorudzbinaID"));
            porudzbina.setDatumPorudzbine(rs.getDate("DatumPorudzbine"));
            porudzbina.setRazduzeno(rs.getBoolean("Razduzeno"));
            Zaposleni zaposleni = new Zaposleni();
            Razduzenje razduzenje = new Razduzenje();
            porudzbina.setZaposleni(zaposleni);
            porudzbina.setRazduzenje(razduzenje);
                porudzbina.getRazduzenje().setRazduzenjeID(rs.getInt("RazduzenjeID"));
                porudzbina.getRazduzenje().setUkupnaVrednost(rs.getDouble("UkupnaVrednost"));
                porudzbina.getZaposleni().setZaposleniID(rs.getInt("ZaposleniID"));
                porudzbina.getZaposleni().setIme(rs.getString("Ime"));
                porudzbina.getZaposleni().setPrezime(rs.getString("Prezime"));
                porudzbina.getZaposleni().setKorisnickoIme("KorisnickoIme");
                porudzbina.getZaposleni().setKorisnickaSifra("KorisnickaSifra");
            porudzbine.add(porudzbina);
        }
        return porudzbine;
    }

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String where) throws Exception {
        List<DomenskiObjekat> porudzbine = new ArrayList<>();
        ResultSet rs =  db.nadji(dom, kreirajUpit(dom, where));
        while(rs.next()){
            Porudzbina porudzbina = new Porudzbina();
            porudzbina.setPorudzbinaID(rs.getInt("PorudzbinaID"));
            porudzbina.setDatumPorudzbine(rs.getDate("DatumPorudzbine"));
            porudzbina.setRazduzeno(rs.getBoolean("Razduzeno"));
            Zaposleni zaposleni = new Zaposleni();
            Razduzenje razduzenje = new Razduzenje();
            porudzbina.setZaposleni(zaposleni);
            porudzbina.setRazduzenje(razduzenje);
                porudzbina.getRazduzenje().setRazduzenjeID(rs.getInt("RazduzenjeID"));
                porudzbina.getRazduzenje().setUkupnaVrednost(rs.getDouble("UkupnaVrednost"));
                porudzbina.getZaposleni().setZaposleniID(rs.getInt("ZaposleniID"));
                porudzbina.getZaposleni().setIme(rs.getString("Ime"));
                porudzbina.getZaposleni().setPrezime(rs.getString("Prezime"));
                porudzbina.getZaposleni().setKorisnickoIme("KorisnickoIme");
                porudzbina.getZaposleni().setKorisnickaSifra("KorisnickaSifra");
            porudzbine.add(porudzbina);
        }
        return porudzbine;
    }

    @Override
    protected void izbrisi(DomenskiObjekat dom) throws Exception {
        try{
            Porudzbina porudzbina = (Porudzbina) dom;
            String upit = kreirajUpitZaDelete(porudzbina);
            db.obrisi(upit);
        }catch (Exception ex){
            
        }
    }
    
    public String kreirajUpit(DomenskiObjekat dom, String where){
        Porudzbina porudzbina = (Porudzbina) dom;
        String upit = "SELECT * FROM Porudzbina INNER JOIN "+
                "(Razduzenje INNER JOIN Zaposleni ON "+
                "Razduzenje.ZaposleniID = Zaposleni.ZaposleniID) ON "+
                "Porudzbina.ZaposleniID = Zaposleni.ZaposleniID WHERE "+where;
        return upit;
    }
    
    public String kreirajUpitZaDelete (Porudzbina porudzbina) {
        return "DELETE FROM Porudzbina WHERE PorudzbinaID=" + porudzbina.getPorudzbinaID();
    }

}
