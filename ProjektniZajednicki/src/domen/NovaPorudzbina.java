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
public class NovaPorudzbina {
    private Artikal artikal;
    private int kolicina;
    private int cena;

    public NovaPorudzbina() {
    }

    public NovaPorudzbina(Artikal artikal, int kolicina, int cena) {
        this.artikal = artikal;
        this.kolicina = kolicina;
        this.cena = cena;
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
        return "NovaPorudzbina{" + "artikal=" + artikal + ", kolicina=" + kolicina + ", cena=" + cena + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.artikal);
        hash = 71 * hash + this.kolicina;
        hash = 71 * hash + this.cena;
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
        final NovaPorudzbina other = (NovaPorudzbina) obj;
        if (!Objects.equals(this.artikal, other.artikal)) {
            return false;
        }
        if (this.kolicina != other.kolicina) {
            return false;
        }
        if (this.cena != other.cena) {
            return false;
        }
        return true;
    }
    
    
}
