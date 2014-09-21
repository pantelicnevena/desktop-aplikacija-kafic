/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbbr;

import domen.Artikal;
import domen.DomenskiObjekat;
import domen.Porudzbina;
import domen.StavkaPorudzbine;
import domen.Zaposleni;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    public void ulazArtikala (Artikal artikal) throws SQLException{
        String upit = "UPDATE Artikal SET StanjeNaZalihama = ?, Cena = ? WHERE ArtikalID = ?";
        PreparedStatement prepareStatement = konekcija.prepareStatement(upit);
        prepareStatement.setDouble(1, artikal.getStanjeNaZalihama());
        prepareStatement.setDouble(2, artikal.getCena());
        prepareStatement.setInt(3, artikal.getArtikalID());
        System.out.println("Upit: "+upit);
        prepareStatement.executeUpdate();
        prepareStatement.close();
    }
    
    public void izmena (DomenskiObjekat dom, String upit) throws SQLException{
        Statement statement = konekcija.createStatement();
        System.out.println("Upit: "+upit);
        statement.executeUpdate(upit);
     }
    
     public ResultSet nadji (DomenskiObjekat dom, String upit) throws SQLException {
        konekcija = DriverManager.getConnection("jdbc:odbc:kafic");
        System.out.println("Upit: "+upit+"dom objekat: "+dom);
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
