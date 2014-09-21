/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Artikal;
import domen.Distributer;
import domen.DomenskiObjekat;
import domen.KategorijaArtikla;
import domen.Porudzbina;
import domen.Provera;
import domen.StavkaPorudzbine;
import domen.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemskeOpetacije.ArtikalSO;
import sistemskeOpetacije.DistributerSO;
import sistemskeOpetacije.KategorijaSO;
import sistemskeOpetacije.OpstaSO;
import sistemskeOpetacije.PorudzbinaSO;
import sistemskeOpetacije.StavkaSO;
import sistemskeOpetacije.ZaposleniSO;
import transfer.TObjekat;

/**
 *
 * @author Nevena
 */
public class ServerKontroler extends Thread {

    private ObjectInputStream inSoket;

    Socket soket;

    public void setSoket(Socket soket) {
        this.soket = soket;
    }

    @Override
    public void run() {
        try {
            pokreniKomunikaciju(soket);
            System.out.println("Server ceka klijenta");
        } catch (Exception ex) {
            Logger.getLogger(ServerKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pokreni() throws SQLException, Exception {
        try {
            ServerSocket ssoket = new ServerSocket(9000);
            Socket soket = ssoket.accept();
            pokreniKomunikaciju(soket);
        } catch (IOException ex) {
            Logger.getLogger(ServerKontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pokreniKomunikaciju(Socket soket) throws IOException, ClassNotFoundException, SQLException, Exception {
        while (true) {
            inSoket = new ObjectInputStream(soket.getInputStream());
            TObjekat zahtev = (TObjekat) inSoket.readObject();

            String poruka = zahtev.getPoruka();
            izvrsiOperaciju(zahtev, soket);
        }
    }

    public void izvrsiOperaciju(TObjekat zahtev, Socket soket) throws IOException, SQLException, Exception {
        String poruka = zahtev.getPoruka();
        System.out.println("Poruka: "+ zahtev);

        if (poruka.equals("provera")) {
            OpstaSO opsta = new ZaposleniSO();
            TObjekat odgovor;
            try {
                Provera provera = (Provera)zahtev.getObjekat();
                String ime = provera.getIme();
                String sifra = provera.getSifra();
                String where = "KorisnickoIme = '"+ime+"' AND KorisnickaSifra = '"+sifra+"'";
                List<DomenskiObjekat> lista = opsta.pronadji((DomenskiObjekat) zahtev.getObjekat(), where);
                odgovor = new TObjekat(lista, "ok");
                System.out.println("" + odgovor);
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("listaZaposlenih")) {
            OpstaSO opsta = new ZaposleniSO();
            TObjekat odgovor;
            try {
                Zaposleni zaposleni = new Zaposleni();
                zahtev.setObjekat(zaposleni);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(lista, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("listaDistributera")) {
            OpstaSO opsta = new DistributerSO();
            TObjekat odgovor;
            try {
                Distributer distributer = new Distributer();
                zahtev.setObjekat(distributer);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(lista, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("listaKategorija")) {
            OpstaSO opsta = new KategorijaSO();
            TObjekat odgovor;
            try {
                KategorijaArtikla kategorija = new KategorijaArtikla();
                zahtev.setObjekat(kategorija);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(lista, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("listaArtikala")) {
            OpstaSO opsta = new ArtikalSO();
            TObjekat odgovor;
            try {
                Artikal artikal = new Artikal();
                zahtev.setObjekat(artikal);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(lista, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("listaPorudzbina")) {
            OpstaSO opsta = new PorudzbinaSO();
            TObjekat odgovor;
            try {
                Porudzbina porudzbina = new Porudzbina();
                zahtev.setObjekat(porudzbina);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(lista, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("listaStavki")) {
            OpstaSO opsta = new PorudzbinaSO();
            TObjekat odgovor;
            try {
                Porudzbina porudzbina = new Porudzbina();
                zahtev.setObjekat(porudzbina);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(lista, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("vratiArtikalID")) {
            OpstaSO opsta = new ArtikalSO();
            TObjekat odgovor;
            try {
                Artikal artikal = new Artikal();
                zahtev.setObjekat(artikal);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                int max = 0;
                for (int i = 0; i<lista.size(); i++){
                    Artikal a = (Artikal)lista.get(i);
                    if (a.getArtikalID()> max) max = a.getArtikalID();
                }
                int id = max+1;
                odgovor = new TObjekat(id, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("vratiDistributerID")) {
            OpstaSO opsta = new DistributerSO();
            TObjekat odgovor;
            try {
                Distributer distributer = new Distributer();
                zahtev.setObjekat(distributer);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                int max = 0;
                for (int i = 0; i<lista.size(); i++){
                    Distributer d = (Distributer)lista.get(i);
                    if (d.getDistributerID()> max) max = d.getDistributerID();
                }
                int id = max+1;
                odgovor = new TObjekat(id, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("vratiKategorijaID")) {
            OpstaSO opsta = new KategorijaSO();
            TObjekat odgovor;
            try {
                KategorijaArtikla kategorija = new KategorijaArtikla();
                zahtev.setObjekat(kategorija);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                int max = 0;
                for (int i = 0; i<lista.size(); i++){
                    KategorijaArtikla k = (KategorijaArtikla)lista.get(i);
                    if (k.getKategorijaID()> max) max = k.getKategorijaID();
                }
                int id = max+1;
                odgovor = new TObjekat(id, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("vratiZaposleniID")) {
            OpstaSO opsta = new ZaposleniSO();
            TObjekat odgovor;
            try {
                Zaposleni zaposleni = new Zaposleni();
                zahtev.setObjekat(zaposleni);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                int max = 0;
                for (int i = 0; i<lista.size(); i++){
                    Zaposleni z = (Zaposleni)lista.get(i);
                    if (z.getZaposleniID() > max) max = z.getZaposleniID();
                }
                int id = max+1;
                odgovor = new TObjekat(id, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("sacuvajArtikal")) {
            OpstaSO opsta = new ArtikalSO();
            TObjekat odgovor;
            try {
                opsta.izvrsi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("sacuvajDistributera")) {
            OpstaSO opsta = new DistributerSO();
            TObjekat odgovor;
            try {
                opsta.izvrsi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("sacuvajKategoriju")) {
            OpstaSO opsta = new KategorijaSO();
            TObjekat odgovor;
            try {
                opsta.izvrsi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("sacuvajZaposlenog")) {
            OpstaSO opsta = new ZaposleniSO();
            TObjekat odgovor;
            try {
                System.out.println(""+zahtev.getObjekat());
                opsta.izvrsi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
                System.out.println("" + odgovor);
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("obrisiDistributera")) {
            OpstaSO opsta = new DistributerSO();
            TObjekat odgovor;
            try {
                opsta.obrisi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("obrisiArtikal")) {
            OpstaSO opsta = new ArtikalSO();
            TObjekat odgovor;
            try {
                opsta.obrisi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("obrisiKategoriju")) {
            OpstaSO opsta = new KategorijaSO();
            TObjekat odgovor;
            try {
                opsta.obrisi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("obrisiZaposlenog")) {
            OpstaSO opsta = new ZaposleniSO();
            TObjekat odgovor;
            try {
                opsta.obrisi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("ulazArtikala")) {
            OpstaSO opsta = new ArtikalSO();
            TObjekat odgovor;
            try {
                List<Artikal> artikli = (List<Artikal>) zahtev.getObjekat();
                System.out.println(""+artikli);
                
                for(int i = 0; i<artikli.size(); i++){
                    opsta.izmeni((DomenskiObjekat) artikli.get(i));
                }
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("nenapravljeneStavke")) {
            OpstaSO opsta = new StavkaSO();
            TObjekat odgovor;
            try {
                String where = "Napravljeno = 0";
                StavkaPorudzbine stavka = new StavkaPorudzbine();
                List<DomenskiObjekat> lista = opsta.pronadji((DomenskiObjekat) stavka, where);
                odgovor = new TObjekat(lista, "ok");
                System.out.println("" + odgovor.getObjekat());
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("nenaplacenePorudzbine")) {
            OpstaSO opsta = new PorudzbinaSO();
            TObjekat odgovor;
            try {
                String where = "Razduzeno = FALSE";
                Porudzbina porudzbina = new Porudzbina();
                List<DomenskiObjekat> nenaplacene = opsta.pronadji((DomenskiObjekat) porudzbina, where);
                
                odgovor = new TObjekat(nenaplacene, "ok");
                System.out.println("" + odgovor.getObjekat());
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("napravljenaStavka")) {
            OpstaSO opsta = new StavkaSO();
            TObjekat odgovor;
            try {
                StavkaPorudzbine stavka = (StavkaPorudzbine) zahtev.getObjekat();
                opsta.izmeni((DomenskiObjekat) stavka);
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("naplacenaPorudzbina")) {
            OpstaSO opsta = new PorudzbinaSO();
            TObjekat odgovor;
            try {
                Porudzbina porudzbina = (Porudzbina) zahtev.getObjekat();
                opsta.izmeni((DomenskiObjekat) porudzbina);
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("izmenaProfila")) {
            OpstaSO opsta = new ZaposleniSO();
            TObjekat odgovor;
            try {
                Zaposleni zaposleni = (Zaposleni) zahtev.getObjekat();
                opsta.izmeni((DomenskiObjekat) zaposleni);
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        
    }
}
