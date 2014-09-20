/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.model;

import domen.Artikal;
import domen.NovaPorudzbina;
import domen.Ulaz;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTableNapraviPorudzbinu extends AbstractTableModel{
    String[] nazivKolona = new String[] {"Artikal", "Kolicina", "Cena"};
    List<NovaPorudzbina> listaNovePorudzbine;

    public ModelTableNapraviPorudzbinu() {
        listaNovePorudzbine = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return listaNovePorudzbine.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NovaPorudzbina np = listaNovePorudzbine.get(rowIndex);
        switch(columnIndex){
            case 0: return np.getArtikal();
            case 1: return np.getKolicina();
            case 2: return np.getCena();
            default: return "greska";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        NovaPorudzbina np = listaNovePorudzbine.get(rowIndex);
        switch (columnIndex){
            case 0: np.setArtikal((Artikal) aValue); break;
            case 1: String kolicinaString = String.valueOf(aValue);
                    int kolicina = Integer.valueOf(kolicinaString);
                    int cena = (int) np.getArtikal().getCena();
                    np.setKolicina(kolicina);
                    np.setCena(kolicina*cena);
                    break;
            case 2: System.out.println("Cena: bla bla"); break;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    public void dodajRed(NovaPorudzbina np){
        listaNovePorudzbine.add(np);
        fireTableDataChanged();
    }
    
    public void obrisiRed(int rb){
        listaNovePorudzbine.remove(rb);
        fireTableDataChanged();
    }
    
}
