/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transfer;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class TObjekat implements Serializable{
    Object objekat;
    String poruka;

    public Object getObjekat() {
        return objekat;
    }

    public void setObjekat(Object objekat) {
        this.objekat = objekat;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public TObjekat(Object objekat, String poruka) {
        this.objekat = objekat;
        this.poruka = poruka;
    }

    @Override
    public String toString() {
        return "TObjekat{" + "objekat=" + objekat + ", poruka=" + poruka + '}';
    }
    
    
    
}
