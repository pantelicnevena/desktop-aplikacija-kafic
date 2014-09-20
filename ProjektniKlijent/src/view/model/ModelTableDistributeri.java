/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.model;

import domen.Distributer;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTableDistributeri extends AbstractTableModel{
    String[] nazivKolona = new String[] {"ID", "Naziv"};
    List<Distributer> listaDIstributera;

    public ModelTableDistributeri(List<Distributer> listaDIstributera) {
        this.listaDIstributera = listaDIstributera;
    }
    
    @Override
    public int getRowCount() {
        return listaDIstributera.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Distributer distributer = listaDIstributera.get(rowIndex);
        switch(columnIndex){
            case 0: return distributer.getDistributerID();
            case 1: return distributer.getNazivDistributera();
            default: return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }
    
    public void obrisiRed(int rb){
        listaDIstributera.remove(rb);
        fireTableDataChanged();
    }
    
}
