/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemskeOpetacije;

import dbbr.DBBroker;
import domen.DomenskiObjekat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nevena
 */
public abstract class OpstaSO {
    protected DBBroker db;

    public OpstaSO() {
        db = new DBBroker();
    }

    public void izvrsi(DomenskiObjekat domObj) throws Exception {
        try {
            otvoriTransakciju();
            izvrsiOperaciju(domObj);
            potvrdiTransakciju();
        } catch (Exception ex) {
            ponistiTransakciju();
            ex.printStackTrace();
            throw new Exception("Operacija cuvanja nije izvrsena!");

        }
    }
    public void izmeni(DomenskiObjekat domObj) throws Exception {
        try {
            otvoriTransakciju();
            izmeniObjekat(domObj);
            potvrdiTransakciju();
        } catch (Exception ex) {
            ponistiTransakciju();
            ex.printStackTrace();
            throw new Exception("Operacija izmene nije izvrsena!");

        }
    }
    
    public void obrisi (DomenskiObjekat dom) throws Exception {
        try {
            otvoriTransakciju();
            //proveriPreduslov();
            izbrisi(dom);
            potvrdiTransakciju();
        } catch (Exception ex) {
            ponistiTransakciju();
            ex.printStackTrace();
            throw new Exception("Operacija cuvanja nije izvrsena!");

        }
    }
    
    public List<DomenskiObjekat> pronadji (DomenskiObjekat dom, String upit) throws SQLException {
        try {
            otvoriTransakciju();
            //proveriPreduslov();
            List<DomenskiObjekat> lista = nadji(dom, upit);
            potvrdiTransakciju();
            return lista;
        } catch (Exception ex) {
            ponistiTransakciju();
            ex.printStackTrace();
            return null;

        }
    }

    public List<DomenskiObjekat> vratiSve(DomenskiObjekat dom) throws SQLException {
        try {
            otvoriTransakciju();
            List<DomenskiObjekat> lista = vrati(dom);
            potvrdiTransakciju();
            return lista;
        } catch (Exception ex) {
            ponistiTransakciju();
            ex.printStackTrace();
            return null;
        }
    }

    private void otvoriTransakciju() throws Exception {
        db.otvoriTransakciju();
    }

    protected abstract void proveriPreduslov();

    private void potvrdiTransakciju() throws SQLException {
        db.potvrdiTransakciju();
    }

    private void ponistiTransakciju() throws SQLException {
        db.ponistiTransakciju();
    }

    protected abstract void izvrsiOperaciju(DomenskiObjekat domObj) throws Exception;
    
    protected abstract void izmeniObjekat(DomenskiObjekat domObj) throws Exception;

    protected abstract List<DomenskiObjekat> vrati(DomenskiObjekat dom) throws Exception;

    protected abstract List<DomenskiObjekat> nadji(DomenskiObjekat dom, String upit) throws Exception;

    protected abstract void izbrisi(DomenskiObjekat dom) throws Exception;
}
