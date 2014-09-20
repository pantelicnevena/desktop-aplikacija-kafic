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
public class StavkaPorudzbine extends DomenskiObjekat implements Serializable {
    private int RedniBrojStavke;
    private int Kolicina;
    private boolean Napravljeno;
    private Artikal artikal;
    private Porudzbina porudzbina;

    public StavkaPorudzbine() {
    }

    public StavkaPorudzbine(int RedniBrojStavke, int Kolicina, boolean Napravljeno, Artikal artikal, Porudzbina porudzbina) {
        this.RedniBrojStavke = RedniBrojStavke;
        this.Kolicina = Kolicina;
        this.Napravljeno = Napravljeno;
        this.artikal = artikal;
        this.porudzbina = porudzbina;
    }

    public int getRedniBrojStavke() {
        return RedniBrojStavke;
    }

    public void setRedniBrojStavke(int RedniBrojStavke) {
        this.RedniBrojStavke = RedniBrojStavke;
    }

    public int getKolicina() {
        return Kolicina;
    }

    public void setKolicina(int Kolicina) {
        this.Kolicina = Kolicina;
    }

    public boolean isNapravljeno() {
        return Napravljeno;
    }

    public void setNapravljeno(boolean Napravljeno) {
        this.Napravljeno = Napravljeno;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    @Override
    public String toString() {
        return "StavkaPorudzbine{" + "RedniBrojStavke=" + RedniBrojStavke + ", Kolicina=" + Kolicina + ", Napravljeno=" + Napravljeno + ", artikal=" + artikal + ", porudzbina=" + porudzbina + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.RedniBrojStavke;
        hash = 79 * hash + this.Kolicina;
        hash = 79 * hash + (this.Napravljeno ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.artikal);
        hash = 79 * hash + Objects.hashCode(this.porudzbina);
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
        final StavkaPorudzbine other = (StavkaPorudzbine) obj;
        if (this.RedniBrojStavke != other.RedniBrojStavke) {
            return false;
        }
        if (this.Kolicina != other.Kolicina) {
            return false;
        }
        if (this.Napravljeno != other.Napravljeno) {
            return false;
        }
        if (!Objects.equals(this.artikal, other.artikal)) {
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
        return "StavkaPorudzbine";
    }
    
    @Override
    public String vratiVrednostiZaInsert() {
        return "'"+RedniBrojStavke+"','"+Kolicina+"','"+Napravljeno+"','"+artikal.getArtikalID()+"','"+"','"+porudzbina.getPorudzbinaID()+"'";
    }
    
    @Override
    public String vratiNazivTabeleZaJoin() {
        return "Porudzbina INNER JOIN "+
                "(StavkaPorudzbine INNER JOIN Artikal "+
                "ON StavkaPorudzbine.ArtikalID = Artikal.ArtikalID) "+
                "ON Porudzbina.PorudzbinaID = StavkaPorudzbine.PorudzbinaID";
    }
}
