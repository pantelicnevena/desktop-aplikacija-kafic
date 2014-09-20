/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.model;

import domen.Artikal;
import domen.Ulaz;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTableUlazArtikala extends AbstractTableModel{
    String[] nazivKolona = new String [] {"Artikal", "Kolicina", "Cena"};
    List<Ulaz> listaUlaza;

    public ModelTableUlazArtikala() {
        listaUlaza = new ArrayList<>();
    }
       
    @Override
    public int getRowCount() {
        return listaUlaza.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ulaz ulaz = listaUlaza.get(rowIndex);
        switch(columnIndex){
            case 0: return ulaz.getArtikal();
            case 1: return ulaz.getKolicina();
            case 2: return ulaz.getCena();
            default: return "greska";
       }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Ulaz ulaz = listaUlaza.get(rowIndex);
        switch(columnIndex){
            case 0: ulaz.setArtikal((Artikal) aValue); break;
            case 1: String kolicinaString = String.valueOf(aValue);
                    int kolicina = Integer.valueOf(kolicinaString);
                    ulaz.setKolicina(kolicina); break;
            case 2: String cenaString = String.valueOf(aValue);
                    int cena = Integer.valueOf(cenaString);
                    System.out.println("MTU: Cena: "+ cena);
                    ulaz.setCena(cena); break;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }

    public List<Ulaz> getListaUlaza() {
        return listaUlaza;
    }

    public void setListaUlaza(List<Ulaz> listaUlaza) {
        this.listaUlaza = listaUlaza;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    public void dodajRed(Ulaz u){
        listaUlaza.add(u);
        fireTableDataChanged();
    }
    
    public void obrisiRed(int rb){
        listaUlaza.remove(rb);
        fireTableDataChanged();
    }
    
}
