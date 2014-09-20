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

    public ModelTableSanker() {
        listaStavki = new ArrayList<>();
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
        return 0;
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }
    
    
}
