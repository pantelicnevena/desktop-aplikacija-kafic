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
public class Artikal extends DomenskiObjekat implements Serializable{
    private int ArtikalID;
    private String NazivArtikla;
    private String Ambalaza;
    private Date RokTrajanja;
    private double StanjeNaZalihama;
    private double Cena;
    private Distributer distributer;
    private KategorijaArtikla kategorija;

    public Artikal() {
    }

    public Artikal(int ArtikalID, String NazivArtikla, String Ambalaza, Date RokTrajanja, double StanjeNaZalihama, double Cena, Distributer distributer, KategorijaArtikla kategorija) {
        this.ArtikalID = ArtikalID;
        this.NazivArtikla = NazivArtikla;
        this.Ambalaza = Ambalaza;
        this.RokTrajanja = RokTrajanja;
        this.StanjeNaZalihama = StanjeNaZalihama;
        this.Cena = Cena;
        this.distributer = distributer;
        this.kategorija = kategorija;
    }

    public int getArtikalID() {
        return ArtikalID;
    }

    public void setArtikalID(int ArtikalID) {
        this.ArtikalID = ArtikalID;
    }

    public String getNazivArtikla() {
        return NazivArtikla;
    }

    public void setNazivArtikla(String NazivArtikla) {
        this.NazivArtikla = NazivArtikla;
    }

    public String getAmbalaza() {
        return Ambalaza;
    }

    public void setAmbalaza(String Ambalaza) {
        this.Ambalaza = Ambalaza;
    }

    public Date getRokTrajanja() {
        return RokTrajanja;
    }

    public void setRokTrajanja(Date RokTrajanja) {
        this.RokTrajanja = RokTrajanja;
    }

    public double getStanjeNaZalihama() {
        return StanjeNaZalihama;
    }

    public void setStanjeNaZalihama(double StanjeNaZalihama) {
        this.StanjeNaZalihama = StanjeNaZalihama;
    }

    public double getCena() {
        return Cena;
    }

    public void setCena(double Cena) {
        this.Cena = Cena;
    }

    public Distributer getDistributer() {
        return distributer;
    }

    public void setDistributer(Distributer distributer) {
        this.distributer = distributer;
    }

    public KategorijaArtikla getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaArtikla kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String toString() {
        return NazivArtikla + " " + Ambalaza + " - " + Cena;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.ArtikalID;
        hash = 41 * hash + Objects.hashCode(this.NazivArtikla);
        hash = 41 * hash + Objects.hashCode(this.Ambalaza);
        hash = 41 * hash + Objects.hashCode(this.RokTrajanja);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.StanjeNaZalihama) ^ (Double.doubleToLongBits(this.StanjeNaZalihama) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.Cena) ^ (Double.doubleToLongBits(this.Cena) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.distributer);
        hash = 41 * hash + Objects.hashCode(this.kategorija);
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
        final Artikal other = (Artikal) obj;
        if (this.ArtikalID != other.ArtikalID) {
            return false;
        }
        if (!Objects.equals(this.NazivArtikla, other.NazivArtikla)) {
            return false;
        }
        if (!Objects.equals(this.Ambalaza, other.Ambalaza)) {
            return false;
        }
        if (!Objects.equals(this.RokTrajanja, other.RokTrajanja)) {
            return false;
        }
        if (Double.doubleToLongBits(this.StanjeNaZalihama) != Double.doubleToLongBits(other.StanjeNaZalihama)) {
            return false;
        }
        if (Double.doubleToLongBits(this.Cena) != Double.doubleToLongBits(other.Cena)) {
            return false;
        }
        if (!Objects.equals(this.distributer, other.distributer)) {
            return false;
        }
        if (!Objects.equals(this.kategorija, other.kategorija)) {
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
        return "Artikal";
    }
    
    @Override
    public String vratiVrednostiZaInsert() {
        return "'"+ArtikalID+"','"+NazivArtikla+"','"+Ambalaza+"','"+RokTrajanja+"','"+StanjeNaZalihama+"','"+Cena+"','"+distributer.getDistributerID()+"','"+kategorija.getKategorijaID()+"'";
    }
    
    @Override
    public String vratiNazivTabeleZaJoin() {
        return "KategorijaArtikla INNER JOIN "+
                "(Distributer INNER JOIN Artikal ON "+
                "Distributer.DistributerID = Artikal.DistributerID) "+
                "ON KategorijaArtikla.KategorijaID = Artikal.KategorijaID";
    }
    
}
