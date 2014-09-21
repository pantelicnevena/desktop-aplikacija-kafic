/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.model;

import domen.Artikal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTableArtikli extends AbstractTableModel{
    String[] nazivKolona = new String[] {"ID", "Naziv artikla", "Ambalaza", "Kategorija", "Rok trajanja", "Stanje na zalihama", "Cena", "Distributer"};
    List<Artikal> listaArtikala;

    public ModelTableArtikli(List<Artikal> listaArtikala) {
        this.listaArtikala = listaArtikala;
    }
    
    @Override
    public int getRowCount() {
        return listaArtikala.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Artikal artikal = listaArtikala.get(rowIndex);
        switch(columnIndex){
            case 0: return artikal.getArtikalID();
            case 1: return artikal.getNazivArtikla();
            case 2: return artikal.getAmbalaza();
            case 3: return artikal.getKategorija();
            case 4: return artikal.getRokTrajanja();
            case 5: return artikal.getStanjeNaZalihama();
            case 6: return artikal.getCena();
            case 7: return artikal.getDistributer();
            default: return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }
    
    public Artikal vratiObjekat(int rb){
        Artikal artikal = listaArtikala.get(rb);
        return artikal;
    }
    
    public void obrisiRed(int rb){
        listaArtikala.remove(rb);
        fireTableDataChanged();
    }
}
