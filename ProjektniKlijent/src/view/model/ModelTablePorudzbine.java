/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import domen.Porudzbina;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nevena
 */
public class ModelTablePorudzbine extends AbstractTableModel {

    String[] nazivKolona = new String[]{"ID", "Datum porudzbine", "Zaposleni", "Ukupna vrednost", "Razduzeno"};
    List<Porudzbina> listaPorudzbina;

    public ModelTablePorudzbine(List<Porudzbina> listaPorudzbina) {
        this.listaPorudzbina = listaPorudzbina;
        for (int i = 0; i < listaPorudzbina.size(); i++) {
            Porudzbina p = (Porudzbina) listaPorudzbina.get(i);
            for (int j = i + 1; j < listaPorudzbina.size(); j++) {
                if (listaPorudzbina.get(j).getPorudzbinaID() == p.getPorudzbinaID()) {
                    System.out.println("iste");
                    listaPorudzbina.remove(j);
                    System.out.println("" + listaPorudzbina);
                }
            }
        }
    }

    @Override
    public int getRowCount() {
        return listaPorudzbina.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Porudzbina porudzbina = listaPorudzbina.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return porudzbina.getPorudzbinaID();
            case 1:
                return porudzbina.getDatumPorudzbine();
            case 2:
                String ime = porudzbina.getZaposleni().getIme();
                String prezime = porudzbina.getZaposleni().getPrezime();
                String zaposleni = ime + " " + prezime;
                return zaposleni;
            case 3:
                return porudzbina.getRazduzenje().getUkupnaVrednost();
            case 4:
                if (porudzbina.isRazduzeno()) {
                    return "Da";
                } else {
                    return "Ne";
                }
            default:
                return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }

    public Porudzbina vratiObjekat(int rb) {
        Porudzbina porudzbina = (Porudzbina) listaPorudzbina.get(rb);
        return porudzbina;
    }

    public void obrisiRed(int rb) {
        listaPorudzbina.remove(rb);
        fireTableDataChanged();
    }

}
