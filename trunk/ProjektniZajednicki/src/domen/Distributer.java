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
public class Distributer extends DomenskiObjekat implements Serializable{
    private int DistributerID;
    private String NazivDistributera;

    public Distributer() {
    }

    public Distributer(int DistributerID, String NazivDistributera) {
        this.DistributerID = DistributerID;
        this.NazivDistributera = NazivDistributera;
    }

    public int getDistributerID() {
        return DistributerID;
    }

    public void setDistributerID(int DistributerID) {
        this.DistributerID = DistributerID;
    }

    public String getNazivDistributera() {
        return NazivDistributera;
    }

    public void setNazivDistributera(String NazivDistributera) {
        this.NazivDistributera = NazivDistributera;
    }

    @Override
    public String toString() {
        return NazivDistributera;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.DistributerID;
        hash = 97 * hash + Objects.hashCode(this.NazivDistributera);
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
        final Distributer other = (Distributer) obj;
        if (this.DistributerID != other.DistributerID) {
            return false;
        }
        if (!Objects.equals(this.NazivDistributera, other.NazivDistributera)) {
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
        return "Distributer";
    }
    
    @Override
    public String vratiVrednostiZaInsert() {
        return "'"+DistributerID+"','"+NazivDistributera+"'";
    }
    
    @Override
    public String vratiNazivTabeleZaJoin() {
        return vratiNazivTabele();
    }
}
