/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.ServerKontroler;

/**
 *
 * @author Nevena
 */
public class PokreniServer {
    public static void main(String[] args) {
        PokreniServer server = new PokreniServer();
        try {
            server.pokreni();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void pokreni() throws SQLException, Exception {
        try {
            ServerSocket ssoket = new ServerSocket(9000);
                
            while (true) {
                Socket soket = ssoket.accept();
                ServerKontroler s = new ServerKontroler();
                s.setSoket(soket);
                s.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
