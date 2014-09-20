/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.model;

import domen.Zaposleni;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTableZaposleni extends AbstractTableModel{
    String[] nazivKolona = new String[]{"ID", "Ime", "Prezime", "Korisničko ime", "Korisnička šifra"};
    List<Zaposleni> listaZaposlenih;

    public ModelTableZaposleni(List<Zaposleni> listaZaposlenih) {
        this.listaZaposlenih = listaZaposlenih;
    }
    
    @Override
    public int getRowCount() {
        return listaZaposlenih.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaposleni zaposleni = listaZaposlenih.get(rowIndex);
        switch(columnIndex){
            case 0: return zaposleni.getZaposleniID();
            case 1: return zaposleni.getIme();
            case 2: return zaposleni.getPrezime();
            case 3: return zaposleni.getKorisnickoIme();
            case 4: return zaposleni.getKorisnickaSifra();
            default: return "greška";
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }
    
    public void obrisiRed(int rb){
        listaZaposlenih.remove(rb);
        fireTableDataChanged();
    }
}
