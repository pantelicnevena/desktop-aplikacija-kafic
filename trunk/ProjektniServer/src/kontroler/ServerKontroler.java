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
import domen.Razduzenje;
import domen.StavkaPorudzbine;
import domen.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemskeOpetacije.ArtikalSO;
import sistemskeOpetacije.DistributerSO;
import sistemskeOpetacije.IzbrisiArtikalSO;
import sistemskeOpetacije.IzbrisiDistributeraSO;
import sistemskeOpetacije.IzbrisiKategorijuSO;
import sistemskeOpetacije.IzbrisiPorudzbinuSO;
import sistemskeOpetacije.IzbrisiRazduzenjeSO;
import sistemskeOpetacije.IzbrisiZaposlenogSO;
import sistemskeOpetacije.IzmeniArtikalSO;
import sistemskeOpetacije.IzmeniPorudzbinuSO;
import sistemskeOpetacije.IzmeniStavkeSO;
import sistemskeOpetacije.IzmeniZaposlenogSO;
import sistemskeOpetacije.KategorijaSO;
import sistemskeOpetacije.NaplacenaPorudzbinaSO;
import sistemskeOpetacije.NapravljenaStavkaSO;
import sistemskeOpetacije.OpstaSO;
import sistemskeOpetacije.PorudzbinaSO;
import sistemskeOpetacije.RazduzenjeSO;
import sistemskeOpetacije.StavkaSO;
import sistemskeOpetacije.UlazArtikalaSO;
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
        if (poruka.equals("vratiPorudzbinaID")) {
            OpstaSO opsta = new PorudzbinaSO();
            TObjekat odgovor;
            try {
                Porudzbina porudzbina = new Porudzbina();
                zahtev.setObjekat(porudzbina);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                int max = 0;
                for (int i = 0; i<lista.size(); i++){
                    Porudzbina p = (Porudzbina)lista.get(i);
                    if (p.getPorudzbinaID()> max) max = p.getPorudzbinaID();
                }
                int id = max+1;
                odgovor = new TObjekat(id, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("vratiStavkaID")) {
            OpstaSO opsta = new StavkaSO();
            TObjekat odgovor;
            try {
                StavkaPorudzbine stavka = new StavkaPorudzbine();
                zahtev.setObjekat(stavka);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                int max = 0;
                for (int i = 0; i<lista.size(); i++){
                    StavkaPorudzbine sp = (StavkaPorudzbine)lista.get(i);
                    System.out.println("stavka "+sp.getRedniBrojStavke());
                    if (sp.getRedniBrojStavke()> max) max = sp.getRedniBrojStavke();
                }
                int id = max+1;
                System.out.println("SK: id:" +id);
                odgovor = new TObjekat(id, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("vratiRazduzenjeID")) {
            OpstaSO opsta = new RazduzenjeSO();
            TObjekat odgovor;
            try {
                Razduzenje razduzenje = new Razduzenje();
                zahtev.setObjekat(razduzenje);
                List<DomenskiObjekat> lista = opsta.vratiSve((DomenskiObjekat) zahtev.getObjekat());
                int max = 0;
                for (int i = 0; i<lista.size(); i++){
                    Razduzenje r = (Razduzenje)lista.get(i);
                    if (r.getRazduzenjeID()> max) max = r.getRazduzenjeID();
                }
                int id = max+3;
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
            OpstaSO opsta = new IzbrisiDistributeraSO();
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
        if (poruka.equals("obrisiArtikal")) {
            OpstaSO opsta = new IzbrisiArtikalSO();
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
        if (poruka.equals("obrisiKategoriju")) {
            OpstaSO opsta = new IzbrisiKategorijuSO();
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
        if (poruka.equals("obrisiZaposlenog")) {
            OpstaSO opsta = new IzbrisiPorudzbinuSO();
            TObjekat odgovor;
            try {
                opsta.izvrsi((DomenskiObjekat) zahtev.getObjekat());
                
                opsta = new IzbrisiRazduzenjeSO();
                opsta.izvrsi((DomenskiObjekat) zahtev.getObjekat());
                
                opsta = new IzbrisiZaposlenogSO();
                opsta.izvrsi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("obrisiPorudzbinu")) {
            OpstaSO opsta = new IzbrisiPorudzbinuSO();
            TObjekat odgovor;
            Porudzbina porudzbina = (Porudzbina)zahtev.getObjekat();
            Razduzenje razduzenje = porudzbina.getRazduzenje();
            try {
                opsta.izvrsi((DomenskiObjekat) zahtev.getObjekat());
                
                opsta = new IzbrisiRazduzenjeSO();
                opsta.izvrsi((DomenskiObjekat) razduzenje);
                
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }
            
            OpstaSO opsta2 = new IzbrisiRazduzenjeSO();
            TObjekat odgovor2;
            try {
                opsta.izvrsi((DomenskiObjekat) zahtev.getObjekat());
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("ulazArtikala")) {
            OpstaSO opsta = new UlazArtikalaSO();
            TObjekat odgovor;
            try {
                List<Artikal> artikli = (List<Artikal>) zahtev.getObjekat();
                System.out.println(""+artikli);
                
                for(int i = 0; i<artikli.size(); i++){
                    opsta.izvrsi((DomenskiObjekat) artikli.get(i));
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
            OpstaSO opsta = new NapravljenaStavkaSO();
            TObjekat odgovor;
            try {
                StavkaPorudzbine stavka = (StavkaPorudzbine) zahtev.getObjekat();
                opsta.izvrsi((DomenskiObjekat) stavka);
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("naplacenaPorudzbina")) {
            OpstaSO opsta = new NaplacenaPorudzbinaSO();
            TObjekat odgovor;
            try {
                Porudzbina porudzbina = (Porudzbina) zahtev.getObjekat();
                opsta.izvrsi((DomenskiObjekat) porudzbina);
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("izmeniPorudzbinu")) {
            OpstaSO opsta = new IzmeniPorudzbinuSO();
            TObjekat odgovor;
            try {
                Porudzbina porudzbina = (Porudzbina) zahtev.getObjekat();
                List<StavkaPorudzbine> lista = (List<StavkaPorudzbine>)zahtev.getObjekat();
                opsta.izvrsi((DomenskiObjekat) porudzbina);
                
                opsta = new IzmeniStavkeSO();
                opsta.izvrsi(porudzbina);
                
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("sopstvenePorudzbine")) {
            OpstaSO opsta = new PorudzbinaSO();
            TObjekat odgovor;
            try {
                Zaposleni zaposleni = (Zaposleni)zahtev.getObjekat();
                String where = "Porudzbina.ZaposleniID = "+zaposleni.getZaposleniID();
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
        if (poruka.equals("stavkePorudzbine")) {
            OpstaSO opsta = new StavkaSO();
            TObjekat odgovor;
            try {
                Porudzbina porudzbina = (Porudzbina)zahtev.getObjekat();
                String where = "StavkaPorudzbine.PorudzbinaID = "+porudzbina.getPorudzbinaID();
                StavkaPorudzbine sp = new StavkaPorudzbine();
                List<DomenskiObjekat> stavkePorudzbine = opsta.pronadji((DomenskiObjekat) sp, where);
                
                odgovor = new TObjekat(stavkePorudzbine, "ok");
                System.out.println("" + odgovor.getObjekat());
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("izmenaProfila")) {
            OpstaSO opsta = new IzmeniZaposlenogSO();
            TObjekat odgovor;
            try {
                Zaposleni zaposleni = (Zaposleni) zahtev.getObjekat();
                opsta.izvrsi((DomenskiObjekat) zaposleni);
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("izmeniArtikal")) {
            OpstaSO opsta = new IzmeniArtikalSO();
            TObjekat odgovor;
            try {
                Artikal artikal = (Artikal) zahtev.getObjekat();
                opsta.izvrsi((DomenskiObjekat) artikal);
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("napraviPorudzbinu")) {
            OpstaSO opsta = new RazduzenjeSO();
            TObjekat odgovor;
            List<StavkaPorudzbine> lista = (List<StavkaPorudzbine>)zahtev.getObjekat();
            Porudzbina porudzbina = lista.get(0).getPorudzbina();
            Razduzenje razduzenje = porudzbina.getRazduzenje();
            List<Artikal> artikli = new ArrayList<>();
            try {
                opsta.izvrsi((DomenskiObjekat) razduzenje);
                
                opsta = new PorudzbinaSO();
                opsta.izvrsi((DomenskiObjekat) porudzbina);
                
                opsta = new StavkaSO();
                for (int i = 0; i<lista.size(); i++){
                    Artikal artikal = new Artikal();
                    StavkaPorudzbine sp = lista.get(i);
                    artikal = sp.getArtikal();
                    int poruceno = sp.getKolicina();
                    int stanje = (int)artikal.getStanjeNaZalihama();
                    int novoStanje = stanje - poruceno;
                    artikal.setStanjeNaZalihama(novoStanje);
                    artikli.add(artikal);
                    opsta.izvrsi((DomenskiObjekat) sp);
                }
                
                opsta = new UlazArtikalaSO();
                for (int i=0; i<artikli.size(); i++){
                    Artikal a = artikli.get(i);
                    opsta.izvrsi((DomenskiObjekat)a);
                }
                System.out.println("4. artikli: ok");
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        if (poruka.equals("napraviStavke")) {
            OpstaSO opsta = new StavkaSO();
            TObjekat odgovor;
            List<StavkaPorudzbine> lista = (List<StavkaPorudzbine>)zahtev.getObjekat();
            Porudzbina porudzbina = lista.get(0).getPorudzbina();
            Razduzenje razduzenje = porudzbina.getRazduzenje();
            List<Artikal> artikli = new ArrayList<>();
            try {
                
                for (int i = 0; i<lista.size(); i++){
                    Artikal artikal = new Artikal();
                    StavkaPorudzbine sp = lista.get(i);
                    artikal = sp.getArtikal();
                    int poruceno = sp.getKolicina();
                    int stanje = (int)artikal.getStanjeNaZalihama();
                    int novoStanje = stanje - poruceno;
                    artikal.setStanjeNaZalihama(novoStanje);
                    artikli.add(artikal);
                    opsta.izvrsi((DomenskiObjekat) sp);
                }
                
                odgovor = new TObjekat(null, "ok");
            } catch (Exception ex) {
                odgovor = new TObjekat(null, "greska");
            }

            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(odgovor);
        }
        
        
        
    }
    
}
