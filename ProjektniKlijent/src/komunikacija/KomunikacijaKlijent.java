/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import transfer.TObjekat;

/**
 *
 * @author Nevena
 */
public class KomunikacijaKlijent {
    private Socket soket;
    private static KomunikacijaKlijent instance;
    private HashMap<String, Object> hashMapa;

    public HashMap<String, Object> getHashMapa() {
        return hashMapa;
    }

    public void setHashMapa(HashMap<String, Object> hashMapa) {
        this.hashMapa = hashMapa;
    }
    
    public KomunikacijaKlijent () throws IOException {
        soket = new Socket("127.0.0.1", 9000);
        System.out.println("Klijent se povezao sa serverom");
        hashMapa = new HashMap<>();
    }
    
    public static KomunikacijaKlijent vratiObjekat () throws IOException {
        if (instance == null) {
            instance = new KomunikacijaKlijent();
        }
        return instance;
    }
    
    public void posalji (TObjekat objekat) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(soket.getOutputStream());
        outSocket.writeObject(objekat);
    }
    
    public TObjekat procitaj () throws IOException, ClassNotFoundException {
        ObjectInputStream inSocket = new ObjectInputStream(soket.getInputStream());
        TObjekat objekat = (TObjekat) inSocket.readObject();
        return objekat;
    }
}
