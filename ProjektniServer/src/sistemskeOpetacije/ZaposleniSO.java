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

    @Override
    protected void izbrisi(DomenskiObjekat dom) throws Exception {
         try{
            Zaposleni zaposleni = (Zaposleni) dom;
            String upit = kreirajUpitZaDelete(zaposleni);
            db.obrisi(upit);
        }catch (Exception ex){
            
        }
    }
    
    public String kreirajUpit (DomenskiObjekat dom, String where){
        Provera provera = (Provera) dom;
        String upit = "SELECT * From Zaposleni WHERE "+where;
        /*int brojac = 0;
        if (zaposleni.getZaposleniID()!=0){
            upit = upit+"(ZaposleniID = "+zaposleni.getZaposleniID()+" ";
            brojac++;
        }
        if(!zaposleni.getIme().equals("")){
            if(brojac>0){
                upit = upit+" AND Ime = '"+zaposleni.getIme()+"'";
                
            }else{
                upit = upit+"(Ime='"+zaposleni.getIme()+"'";
            }
            brojac++;
        }
        if(!zaposleni.getPrezime().equals("")){
            if(brojac>0){
                upit = upit+" AND Prezime = '"+zaposleni.getPrezime()+"'";
            }else{
                upit = upit+"(Prezime = '"+zaposleni.getPrezime()+"'";
            }
            brojac++; 
        }
        if(!zaposleni.getKorisnickoIme().equals("")){
            if(brojac>0){
                upit = upit+" AND KorisnickoIme = '"+zaposleni.getKorisnickoIme()+"'";
            }else{
                upit = upit+"(KorisnickoIme = '"+zaposleni.getKorisnickoIme()+"'";
            }
            brojac++; 
        }
        if(!zaposleni.getKorisnickaSifra().equals("")){
            if(brojac>0){
                upit = upit+" AND KorisnickaSifra = '"+zaposleni.getKorisnickaSifra()+"'";
            }else{
                upit = upit+"(Prezime = '"+zaposleni.getKorisnickaSifra()+"'";
            }
            brojac++; 
        }
        upit=upit+")";*/
        return upit;
    }
    
    public String kreirajUpitZaDelete (Zaposleni zaposleni) {
        return "DELETE FROM Zaposleni WHERE ZaposleniID=" + zaposleni.getZaposleniID();
    }
}
