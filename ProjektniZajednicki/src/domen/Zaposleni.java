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
public class Zaposleni extends DomenskiObjekat implements Serializable{
    private int ZaposleniID;
    private String Ime;
    private String Prezime;
    private String KorisnickoIme;
    private String KorisnickaSifra;

    public Zaposleni() {
    }

    public Zaposleni(int ZaposleniID, String Ime, String Prezime, String KorisnickoIme, String KorisnickaSifra) {
        this.ZaposleniID = ZaposleniID;
        this.Ime = Ime;
        this.Prezime = Prezime;
        this.KorisnickoIme = KorisnickoIme;
        this.KorisnickaSifra = KorisnickaSifra;
    }

    public int getZaposleniID() {
        return ZaposleniID;
    }

    public void setZaposleniID(int ZaposleniID) {
        this.ZaposleniID = ZaposleniID;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String Ime) {
        this.Ime = Ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String Prezime) {
        this.Prezime = Prezime;
    }

    public String getKorisnickoIme() {
        return KorisnickoIme;
    }

    public void setKorisnickoIme(String KorisnickoIme) {
        this.KorisnickoIme = KorisnickoIme;
    }

    public String getKorisnickaSifra() {
        return KorisnickaSifra;
    }

    public void setKorisnickaSifra(String KorisnickaSifra) {
        this.KorisnickaSifra = KorisnickaSifra;
    }

    @Override
    public String toString() {
        return Ime + " " + Prezime + " (" + KorisnickaSifra + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.ZaposleniID;
        hash = 47 * hash + Objects.hashCode(this.Ime);
        hash = 47 * hash + Objects.hashCode(this.Prezime);
        hash = 47 * hash + Objects.hashCode(this.KorisnickoIme);
        hash = 47 * hash + Objects.hashCode(this.KorisnickaSifra);
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
        final Zaposleni other = (Zaposleni) obj;
        if (this.ZaposleniID != other.ZaposleniID) {
            return false;
        }
        if (!Objects.equals(this.Ime, other.Ime)) {
            return false;
        }
        if (!Objects.equals(this.Prezime, other.Prezime)) {
            return false;
        }
        if (!Objects.equals(this.KorisnickoIme, other.KorisnickoIme)) {
            return false;
        }
        if (!Objects.equals(this.KorisnickaSifra, other.KorisnickaSifra)) {
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
        return "Zaposleni";
    }
    
    @Override
    public String vratiVrednostiZaInsert() {
        return "'"+ZaposleniID+"','"+Ime+"','"+Prezime+"','"+KorisnickoIme+"','"+KorisnickaSifra+"'";
    }
    
    @Override
    public String vratiNazivTabeleZaJoin() {
        return "Zaposleni";
    }
    
    
}
