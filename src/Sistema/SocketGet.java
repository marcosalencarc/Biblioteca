/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author Paul
 */
public class SocketGet extends Thread {

    public boolean continuar = true;
    public ServerSocket server = null;
    public JTextArea recebidos;

    public SocketGet(JTextArea recebidos) {
        super();
        this.recebidos = recebidos;
        try {
            server = new ServerSocket(8013);
        } catch (IOException ignorada) {
        }
    }

    @Override
    public void run() {

        ObjectInputStream ois = null;
        while (continuar) {
            try {
                Socket s1 = server.accept();
                ois = new ObjectInputStream(s1.getInputStream());
                String mensaje = (String) ois.readObject();
                recebidos.append(mensaje + "\n");
                ois.close();
                s1.close();
            } catch (ClassNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception ignorada) {
            }
        }

    }

    public void parar() {
        continuar = false;
    }
}
