/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbbr;

import domen.DomenskiObjekat;
import domen.Zaposleni;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nevena
 */
public class DBBroker {
    
    Connection konekcija;

    public void otvoriTransakciju() throws Exception {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

        konekcija = DriverManager.getConnection("jdbc:odbc:kafic");
        konekcija.setAutoCommit(false);
    }

    public void potvrdiTransakciju() throws SQLException {
        konekcija.commit();
        konekcija.close();
        System.out.println("Uradjen commit.");
    }

    public void ponistiTransakciju() throws SQLException {
        if (konekcija != null) {
            konekcija.rollback();
            konekcija.close();
        }
        System.out.println("Uradjen rollback.");
    }

    public ResultSet vratiDomObj (DomenskiObjekat dom) throws SQLException {
        System.out.println("DBB: "+dom.vratiNazivTabele());
        konekcija = DriverManager.getConnection("jdbc:odbc:kafic");
        String upit = "SELECT * FROM "+dom.vratiNazivTabeleZaJoin();
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        ResultSet rss = rs;
        return rss;
    }
    
    public void sacuvaj(DomenskiObjekat domenskiObjekat) throws ClassNotFoundException, SQLException, Exception {

        try {
            String upit = "INSERT INTO " + domenskiObjekat.vratiNazivTabele() + " VALUES (" + domenskiObjekat.vratiVrednostiZaInsert() + ")";
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            System.out.println("Uspesno je odradjena operacija");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Operacija cuvanja nije izvrsena!");
        }

    }
    
    public void izmeni (DomenskiObjekat domenskiObjekat, int id) throws ClassNotFoundException, SQLException, Exception {
        try{
            konekcija = DriverManager.getConnection("jdbc:odbc:kafic");
            Zaposleni zaposleni = (Zaposleni) domenskiObjekat;
            int zaposleniID = zaposleni.getZaposleniID();
            String ime = zaposleni.getIme();
            String prezime = zaposleni.getPrezime();
            String korisnickoIme = zaposleni.getKorisnickoIme();
            String korisnickaSifra = zaposleni.getKorisnickaSifra();
            
             String upit = "UPDATE " + domenskiObjekat.vratiNazivTabele() + 
                     " SET Ime= '"+ime+"', Prezime= '"+prezime+"', "
                     + "KorisnickoIme= '"+korisnickoIme+"', KorisnickaSIfra= '"+
                     korisnickaSifra+" WHERE ZaposleniID= "+zaposleniID;
             
            System.out.println(upit);
            Statement state = konekcija.createStatement();
            
            state.executeUpdate(upit);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new Exception("greska");
        }
    }
    
     public ResultSet nadji (DomenskiObjekat dom, String upit) throws SQLException {
        konekcija = DriverManager.getConnection("jdbc:odbc:kafic");
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return rs;
    }
     
     public boolean obrisi(String up) {
        try {
            konekcija = DriverManager.getConnection("jdbc:odbc:kafic");
            
            Statement st = konekcija.createStatement();
            st.executeUpdate(up);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
