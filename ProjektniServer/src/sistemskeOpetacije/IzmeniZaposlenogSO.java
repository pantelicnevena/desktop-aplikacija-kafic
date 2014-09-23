/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import domen.DomenskiObjekat;
import domen.Zaposleni;
import java.util.List;

/**
 *
 * @author Nevena
 */
public class IzmeniZaposlenogSO extends OpstaSO{

    @Override
    protected void proveriPreduslov() {
        
    }

    @Override
    protected void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception {
        try{
            Zaposleni zaposleni = (Zaposleni) domObj;
            String upit = kreirajUpitZaIzmenu(zaposleni);
            db.izmena((Zaposleni) domObj, upit);
        }catch(Exception ex){
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }
    }
    
    public String kreirajUpitZaIzmenu(Zaposleni z){
        return "UPDATE Zaposleni SET Ime = '"+z.getIme()+
                    "', Prezime = '"+z.getPrezime()+"', KorisnickoIme = '"+z.getKorisnickoIme()+
                    "', KorisnickaSifra = '"+z.getKorisnickaSifra()+"' WHERE ZaposleniID = "+z.getZaposleniID();
    }

    @Override
    protected List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception {return null;}

    @Override
    protected List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception {return null;}
    
    
    
}
