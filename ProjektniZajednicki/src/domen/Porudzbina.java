/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domen;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Nevena
 */
public class Porudzbina extends DomenskiObjekat implements Serializable {
    private int PorudzbinaID;
    private Date DatumPorudzbine;
    private boolean Razduzeno;
    private Zaposleni zaposleni;
    private Razduzenje razduzenje;

    public Porudzbina() {
    }

    public Porudzbina(int PorudzbinaID, Date DatumPorudzbine, boolean Razduzeno, Zaposleni zaposleni, Razduzenje razduzenje) {
        this.PorudzbinaID = PorudzbinaID;
        this.DatumPorudzbine = DatumPorudzbine;
        this.Razduzeno = Razduzeno;
        this.zaposleni = zaposleni;
        this.razduzenje = razduzenje;
    }

    public int getPorudzbinaID() {
        return PorudzbinaID;
    }

    public void setPorudzbinaID(int PorudzbinaID) {
        this.PorudzbinaID = PorudzbinaID;
    }

    public Date getDatumPorudzbine() {
        return DatumPorudzbine;
    }

    public void setDatumPorudzbine(Date DatumPorudzbine) {
        this.DatumPorudzbine = DatumPorudzbine;
    }

    public boolean isRazduzeno() {
        return Razduzeno;
    }

    public void setRazduzeno(boolean Razduzeno) {
        this.Razduzeno = Razduzeno;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Razduzenje getRazduzenje() {
        return razduzenje;
    }

    public void setRazduzenje(Razduzenje razduzenje) {
        this.razduzenje = razduzenje;
    }

    @Override
    public String toString() {
        return "Porudzbina{" + "PorudzbinaID=" + PorudzbinaID + ", DatumPorudzbine=" + DatumPorudzbine + ", Razduzeno=" + Razduzeno + ", zaposleni=" + zaposleni + ", razduzenje=" + razduzenje + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.PorudzbinaID;
        hash = 67 * hash + Objects.hashCode(this.DatumPorudzbine);
        hash = 67 * hash + (this.Razduzeno ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.zaposleni);
        hash = 67 * hash + Objects.hashCode(this.razduzenje);
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
        final Porudzbina other = (Porudzbina) obj;
        if (this.PorudzbinaID != other.PorudzbinaID) {
            return false;
        }
        if (!Objects.equals(this.DatumPorudzbine, other.DatumPorudzbine)) {
            return false;
        }
        if (this.Razduzeno != other.Razduzeno) {
            return false;
        }
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        if (!Objects.equals(this.razduzenje, other.razduzenje)) {
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
        return "Porudzbina";
    }
    
    @Override
    public String vratiVrednostiZaInsert() {
        return "'"+PorudzbinaID+"','"+DatumPorudzbine+"','"+Razduzeno+"','"+zaposleni.getZaposleniID()+"','"+"','"+razduzenje.getRazduzenjeID()+"'";
    }
    
    @Override
    public String vratiNazivTabeleZaJoin() {
        return "Porudzbina INNER JOIN (Razduzenje INNER JOIN Zaposleni ON Razduzenje.ZaposleniID = Zaposleni.ZaposleniID) ON Porudzbina.ZaposleniID = Zaposleni.ZaposleniID";
    }
    
    
}
