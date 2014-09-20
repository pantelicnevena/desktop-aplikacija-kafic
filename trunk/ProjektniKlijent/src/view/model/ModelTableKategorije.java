/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.model;

import domen.KategorijaArtikla;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTableKategorije extends AbstractTableModel{
    String[] nazivKolona = new String[] {"ID","Kategorija"};
    List<KategorijaArtikla> listaKategorija;

    public ModelTableKategorije(List<KategorijaArtikla> listaKategorija) {
        this.listaKategorija = listaKategorija;
    }
    
    @Override
    public int getRowCount() {
        return listaKategorija.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KategorijaArtikla kategorija = listaKategorija.get(rowIndex);
        switch(columnIndex){
            case 0: return kategorija.getKategorijaID();
            case 1: return kategorija.getNazivKategorije();
            default: return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }
    
    public void obrisiRed(int rb){
        listaKategorija.remove(rb);
        fireTableDataChanged();
    }
}
