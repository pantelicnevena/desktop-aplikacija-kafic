/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.model;

import domen.StavkaPorudzbine;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTableSanker extends AbstractTableModel{
    String[] nazivKolona = new String[] {"Artikal", "Kolicina"};
    List<StavkaPorudzbine> listaStavki;

    public ModelTableSanker(List<StavkaPorudzbine> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public int getRowCount() {
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaPorudzbine sp = listaStavki.get(rowIndex);
        switch(columnIndex){
            case 0: return sp.getArtikal().getNazivArtikla();
            case 1: return sp.getKolicina();
            default: return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }
    
    public StavkaPorudzbine vratiObjekat(int rb){
        StavkaPorudzbine stavka = (StavkaPorudzbine) listaStavki.get(rb);
        return stavka;
    }
    
    public void obrisiRed(int rb){
        listaStavki.remove(rb);
        fireTableDataChanged();
    }
    
}
