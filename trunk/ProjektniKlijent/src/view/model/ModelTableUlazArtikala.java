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
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTableUlazArtikala extends AbstractTableModel {

    String[] nazivKolona = new String[]{"Artikal", "Kolicina", "Cena"};
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
        switch (columnIndex) {
            case 0:
                return ulaz.getArtikal();
            case 1:
                return ulaz.getKolicina();
            case 2:
                return ulaz.getCena();
            default:
                return "greska";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Ulaz ulaz = listaUlaza.get(rowIndex);
        System.out.println("" + listaUlaza);
        switch (columnIndex) {
            case 0:
                if (listaUlaza.size() > 1) {                           //provera ako nije prvi uneti artikal
                    for (int i = 0; i < listaUlaza.size() - 1; i++) {   //prolazi kroz listu do posledenjeg izabranog artikla (ne treba da proverava njega)
                        Artikal artikal = (Artikal) aValue;
                        if (listaUlaza.get(i).getArtikal().getArtikalID() == artikal.getArtikalID()) {
                            JOptionPane.showMessageDialog(null, "Greška, izabrani artikal ste već uneli.");
                            obrisiRed(rowIndex);
                        } else {
                            ulaz.setArtikal((Artikal) aValue);
                        }
                    }
                } else {
                    ulaz.setArtikal((Artikal) aValue);
                }
                break;
            case 1:
                String kolicinaString = String.valueOf(aValue);
                try {
                    int kolicina = Integer.valueOf(kolicinaString);
                    if (kolicina < 0) {
                        JOptionPane.showMessageDialog(null, "Količina ne sme biti manja od 0.");
                        ulaz.setKolicina(0);
                    } else {
                        ulaz.setKolicina(kolicina);
                    }
                    break;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Niste uneli vrednost u pravom formatu. Molimo Vas da unesete broj.");
                }
            case 2:
                String cenaString = String.valueOf(aValue);
                try {
                    int cena = Integer.valueOf(cenaString);
                    if (cena < 0) {
                        JOptionPane.showMessageDialog(null, "Cena ne sme biti manja od 0.");
                        ulaz.setCena(0);
                    } else {
                        ulaz.setCena(cena);
                    }
                    break;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Niste uneli vrednost u pravom formatu. Molimo Vas da unesete broj.");
                }

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

    public void dodajRed(Ulaz u) {
        listaUlaza.add(u);
        fireTableDataChanged();
    }

    public void obrisiRed(int rb) {
        listaUlaza.remove(rb);
        fireTableDataChanged();
    }

    public Ulaz vratiObjekat(int rb) {
        Ulaz ulaz = listaUlaza.get(rb);
        return ulaz;
    }

}
