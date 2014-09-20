/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domen;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Nevena
 */
public class Razduzenje extends DomenskiObjekat implements Serializable {
    private int RazduzenjeID;
    private double UkupnaVrednost;
    private Zaposleni zaposleni;
    private Porudzbina porudzbina;

    public Razduzenje() {
    }

    public Razduzenje(int RazduzenjeID, double UkupnaVrednost, Zaposleni zaposleni, Porudzbina porudzbina) {
        this.RazduzenjeID = RazduzenjeID;
        this.UkupnaVrednost = UkupnaVrednost;
        this.zaposleni = zaposleni;
        this.porudzbina = porudzbina;
    }

    public int getRazduzenjeID() {
        return RazduzenjeID;
    }

    public void setRazduzenjeID(int RazduzenjeID) {
        this.RazduzenjeID = RazduzenjeID;
    }

    public double getUkupnaVrednost() {
        return UkupnaVrednost;
    }

    public void setUkupnaVrednost(double UkupnaVrednost) {
        this.UkupnaVrednost = UkupnaVrednost;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    @Override
    public String toString() {
        return "Razduzenje{" + "RazduzenjeID=" + RazduzenjeID + ", UkupnaVrednost=" + UkupnaVrednost + ", zaposleni=" + zaposleni + ", porudzbina=" + porudzbina + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.RazduzenjeID;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.UkupnaVrednost) ^ (Double.doubleToLongBits(this.UkupnaVrednost) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.zaposleni);
        hash = 29 * hash + Objects.hashCode(this.porudzbina);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Razduzenje other = (Razduzenje) obj;
        if (this.RazduzenjeID != other.RazduzenjeID) {
            return false;
        }
        if (Double.doubleToLongBits(this.UkupnaVrednost) != Double.doubleToLongBits(other.UkupnaVrednost)) {
            return false;
        }
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        if (!Objects.equals(this.porudzbina, other.porudzbina)) {
            return false;
        }
        return true;
    }

    @Override
    public DomenskiObjekat vratiDomenskiObjekat() {
        return null;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "RazduzenjeID";
    }
    
    @Override
    public String vratiVrednostiZaInsert() {
        return "'"+RazduzenjeID+"','"+UkupnaVrednost+"','"+zaposleni.getZaposleniID()+"','"+"','"+porudzbina.getPorudzbinaID()+"'";
    }
    
    @Override
    public String vratiNazivTabeleZaJoin() {
        return "Razduzenje INNER JOIN "+
                "(Porudzbina INNER JOIN Zaposleni ON "+
                "Porudzbina.ZaposleniID = Zaposleni.ZaposleniID) "+
                "ON Porudzbina.PorudzbinaID = Razduzenje.PorudzbinaID";
    }
    
    
}
