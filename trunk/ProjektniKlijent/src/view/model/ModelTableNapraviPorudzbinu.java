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
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTableNapraviPorudzbinu extends AbstractTableModel {

    String[] nazivKolona = new String[]{"Artikal", "Kolicina", "Cena"};
    List<NovaPorudzbina> listaNovePorudzbine;

    public ModelTableNapraviPorudzbinu() {
        listaNovePorudzbine = new ArrayList<>();
    }

    public ModelTableNapraviPorudzbinu(List<NovaPorudzbina> listaNovePorudzbine) {
        this.listaNovePorudzbine = listaNovePorudzbine;
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
        switch (columnIndex) {
            case 0:
                return np.getArtikal();
            case 1:
                return np.getKolicina();
            case 2:
                return np.getCena();
            default:
                return "greska";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        NovaPorudzbina np = listaNovePorudzbine.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (listaNovePorudzbine.size() > 1) {                           //provera ako nije prvi uneti artikal
                    for (int i = 0; i < listaNovePorudzbine.size() - 1; i++) {   //prolazi kroz listu do posledenjeg izabranog artikla (ne treba da proverava njega)
                        Artikal artikal = (Artikal) aValue;
                        if (listaNovePorudzbine.get(i).getArtikal().getArtikalID() == artikal.getArtikalID()) {
                            JOptionPane.showMessageDialog(null, "Greška, izabrani artikal ste već uneli.");
                            obrisiRed(rowIndex);
                        } else np.setArtikal((Artikal) aValue);
                    }
                } else np.setArtikal((Artikal) aValue);
                break;
            case 1:
                try{
                    String kolicinaString = String.valueOf(aValue);
                    int kolicina = Integer.valueOf(kolicinaString);
                    if (kolicina<0) {
                        JOptionPane.showMessageDialog(null, "Uneta količina mora biti veća od 1.");
                        np.setKolicina(0);
                    }else{
                        np.setKolicina(kolicina);
                        Artikal artikal = np.getArtikal();
                        int cena = (int) artikal.getCena();
                        int ukupnaCena = cena * kolicina;
                        np.setCena(ukupnaCena);
                        dodajRed(np);
                        obrisiRed(rowIndex);
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Niste uneli podatak u pravom formatu.");
                }
                break;
            case 2:
                System.out.println("Cena: " + np.getCena());
                break;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return true;
            case 1:
                return true;
            case 2:
                return false;
            default:
                return true;
        }
    }

    public void dodajRed(NovaPorudzbina np) {
        listaNovePorudzbine.add(np);
        fireTableDataChanged();
    }

    public void obrisiRed(int rb) {
        listaNovePorudzbine.remove(rb);
        fireTableDataChanged();
    }
    
    public NovaPorudzbina vratiObjekat(int rb){
        NovaPorudzbina np = listaNovePorudzbine.get(rb);
        return np;
    }

}
