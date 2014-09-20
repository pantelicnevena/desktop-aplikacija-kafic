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
public class KategorijaArtikla extends DomenskiObjekat implements Serializable{
    private int KategorijaID;
    private String NazivKategorije;

    public KategorijaArtikla() {
    }

    public KategorijaArtikla(int KategorijaID, String NazivKategorije) {
        this.KategorijaID = KategorijaID;
        this.NazivKategorije = NazivKategorije;
    }

    public int getKategorijaID() {
        return KategorijaID;
    }

    public void setKategorijaID(int KategorijaID) {
        this.KategorijaID = KategorijaID;
    }

    public String getNazivKategorije() {
        return NazivKategorije;
    }

    public void setNazivKategorije(String NazivKategorije) {
        this.NazivKategorije = NazivKategorije;
    }

    @Override
    public String toString() {
        return NazivKategorije;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.KategorijaID;
        hash = 41 * hash + Objects.hashCode(this.NazivKategorije);
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
        final KategorijaArtikla other = (KategorijaArtikla) obj;
        if (this.KategorijaID != other.KategorijaID) {
            return false;
        }
        if (!Objects.equals(this.NazivKategorije, other.NazivKategorije)) {
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
        return "KategorijaArtikla";
    }
    
    @Override
    public String vratiVrednostiZaInsert() {
        return "'"+KategorijaID+"','"+NazivKategorije+"'";
    }
    
    @Override
    public String vratiNazivTabeleZaJoin() {
        return vratiNazivTabele();
    }
}
