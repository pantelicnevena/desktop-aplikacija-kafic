/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.DomenskiObjekat;
import domen.Provera;
import domen.Zaposleni;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class ZaposleniSO extends OpstaSO{
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
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {
        List<DomenskiObjekat> zaposleneOsobe = new ArrayList<>();
        
        ResultSet rs =  db.vratiDomObj(dom);
        while(rs.next()){
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setZaposleniID(rs.getInt("ZaposleniID"));
            zaposleni.setIme(rs.getString("Ime"));
            zaposleni.setPrezime(rs.getString("Prezime"));
            zaposleni.setKorisnickoIme(rs.getString("KorisnickoIme"));
            zaposleni.setKorisnickaSifra(rs.getString("KorisnickaSifra"));
            zaposleneOsobe.add(zaposleni);
        }
        return zaposleneOsobe;
    }

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String where) throws Exception {
        List<DomenskiObjekat> zaposleneOsobe = new ArrayList<>();
        ResultSet rs = db.nadji(dom, kreirajUpit(dom, where));
        while(rs.next()){
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setZaposleniID(rs.getInt("ZaposleniID"));
            zaposleni.setIme(rs.getString("Ime"));
            zaposleni.setPrezime(rs.getString("Prezime"));
            zaposleni.setKorisnickoIme(rs.getString("KorisnickoIme"));
            zaposleni.setKorisnickaSifra(rs.getString("KorisnickaSifra"));
            zaposleneOsobe.add(zaposleni);
        }
        return zaposleneOsobe;
    }

    public String kreirajUpit (DomenskiObjekat dom, String where){
        Provera provera = (Provera) dom;
        String upit = "SELECT * From Zaposleni WHERE "+where;
        return upit;
    }
    
    

    
}
