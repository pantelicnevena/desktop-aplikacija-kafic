/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domen;

import java.util.Objects;

/**
 *
 * @author Nevena
 */
public class Ulaz {
    private Artikal artikal;
    private int kolicina;
    private int cena;

    public Ulaz(Artikal artikal, int kolicina, int cena) {
        this.artikal = artikal;
        this.kolicina = kolicina;
        this.cena = cena;
    }

    public Ulaz() {
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return artikal.getNazivArtikla() +":"+ kolicina;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.artikal);
        hash = 97 * hash + this.kolicina;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.cena) ^ (Double.doubleToLongBits(this.cena) >>> 32));
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
        final Ulaz other = (Ulaz) obj;
        if (!Objects.equals(this.artikal, other.artikal)) {
            return false;
        }
        if (this.kolicina != other.kolicina) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        return true;
    }
    
    
    
}
