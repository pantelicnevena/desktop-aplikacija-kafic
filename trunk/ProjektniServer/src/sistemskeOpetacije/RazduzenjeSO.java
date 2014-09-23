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
public class RazduzenjeSO extends OpstaSO {

    @Override
    protected void proveriPreduslov() {
    }

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try {
            db.sacuvaj(domObj);
        } catch (Exception ex) {
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {
        List<DomenskiObjekat> razduzenja = new ArrayList<>();

        ResultSet rs = db.vratiDomObj(dom);
        while (rs.next()) {
            Razduzenje razduzenje = new Razduzenje();
            razduzenje.setRazduzenjeID(rs.getInt("RazduzenjeID"));
            razduzenje.setUkupnaVrednost(rs.getDouble("UkupnaVrednost"));
            Porudzbina porudzbina = new Porudzbina();
            porudzbina.setPorudzbinaID(rs.getInt("PorudzbinaID"));
            porudzbina.setDatumPorudzbine(rs.getDate("DatumPorudzbine"));
            porudzbina.setRazduzeno(rs.getBoolean("Razduzeno"));
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setIme(rs.getString("Ime"));
            zaposleni.setPrezime(rs.getString("Prezime"));
            zaposleni.setKorisnickoIme(rs.getString("KorisnickoIme"));
            zaposleni.setKorisnickaSifra(rs.getString("KorisnickaSifra"));
            razduzenje.setPorudzbina(porudzbina);
            razduzenje.setZaposleni(zaposleni);
            razduzenja.add(razduzenje);
        }
        return razduzenja;
    }

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String where) throws Exception {
        List<DomenskiObjekat> razduzenja = new ArrayList<>();
        ResultSet rs = db.nadji(dom, kreirajUpit(dom, where));
        while (rs.next()) {
            Razduzenje razduzenje = new Razduzenje();
            razduzenje.setRazduzenjeID(rs.getInt("RazduzenjeID"));
            razduzenje.setUkupnaVrednost(rs.getDouble("UkupnaVrednost"));
            Porudzbina porudzbina = new Porudzbina();
            porudzbina.setPorudzbinaID(rs.getInt("PorudzbinaID"));
            porudzbina.setDatumPorudzbine(rs.getDate("DatumPorudzbine"));
            porudzbina.setRazduzeno(rs.getBoolean("Razduzeno"));
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setIme(rs.getString("Ime"));
            zaposleni.setPrezime(rs.getString("Prezime"));
            zaposleni.setKorisnickoIme(rs.getString("KorisnickoIme"));
            zaposleni.setKorisnickaSifra(rs.getString("KorisnickaSifra"));
            razduzenje.setPorudzbina(porudzbina);
            razduzenje.setZaposleni(zaposleni);
            razduzenja.add(razduzenje);
        }
        return razduzenja;
    }
    
    

    public String kreirajUpit(DomenskiObjekat dom, String where) {
        Razduzenje razduzenje = (Razduzenje) dom;
        String upit = "SELECT * From Razduzenje WHERE " + where;
        return upit;
    }
}
