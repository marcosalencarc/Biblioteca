/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import ClassesBase.Livro;
import ClassesBase.Usuario;
import ClassePrincipal.Biblioteca;
import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul
 */
public class Servidor {

    Biblioteca B;
    InetAddress clientes[];
    int n = 0;

    public Servidor() {
        enviarFichero();
        clientes = new InetAddress[100];

        ServerSocket s = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        try {
            s = new ServerSocket(5432);
            JOptionPane.showMessageDialog(null, "O servidor está iniciado");
            System.out.println("Servidor activo ...");
            while (true) {

                Socket s1 = s.accept();
                oos = new ObjectOutputStream(s1.getOutputStream());
                oos.flush();
                ois = new ObjectInputStream(s1.getInputStream());

                Pedido p = (Pedido) ois.readObject();
                Object obj = null;
                switch (p.getPedido()) {
                    case -1: {
                        obj = B;
                        System.out.println("--> há conectado um aluno");
                        InetAddress ia = s1.getInetAddress();
                        int i;
                        for (i = 0; i < n; i++) {
                            if (String.valueOf(clientes[i]).equals(String.valueOf(ia))) {
                                break;
                            }
                        }
                        if (i == n) {
                            clientes[n] = ia;
                            n++;
                        }
                    }
                    break;
                    case 0: {
                        obj = B;
                    }
                    break;
                    case 1: {
                        obj = B.quantidadeLivros();
                    }
                    break;
                    case 2: {//buscar livro por codigo
                        String cod = (String) p.getObj();
                        int pos = B.buscarLivroCodigo(cod);
                        if (pos != -1) {
                            obj = B.livro(pos);
                        }
                    }
                    break;
                    case 3: {//buscar livro por título
                        String titulo = (String) p.getObj();
                        int pos = B.buscarLivroTitulo(titulo);
                        if (pos != -1) {
                            obj = B.livro(pos);
                        }
                    }
                    break;
                    case 4: {//buscar livro por autor
                        String autor = (String) p.getObj();
                        int pos = B.buscarLivroAutor(autor);
                        if (pos != -1) {
                            obj = B.livro(pos);
                        }
                    }
                    break;
                    case 5: {//buscar usuario por código
                        String cod = (String) p.getObj();
                        int pos = B.buscarUsuario(cod);
                        obj = pos;
                    }
                    break;
                    case 6: {//inserir usuario
                        Usuario u = (Usuario) p.getObj();
                        B.inseirUsuario(u);
                    }
                    break;
                    case 7: {
                        obj = B.quantidadeLivDisponibles();
                    }
                    break;
                    case 8: {//O usuario pos tem livro?
                        int pos = ((Integer) p.getObj()).intValue();
                        obj = B.usuario(pos).temLivro();
                    }
                    break;
                    case 9: {//devolve o livro do usuario pos
                        int pos = ((Integer) p.getObj()).intValue();
                        obj = B.usuario(pos).getLib();
                    }
                    break;
                    case 10: {//buscar livro por codigo e devolve a posição
                        String cod = (String) p.getObj();
                        int pos = B.buscarLivroCodigo(cod);
                        obj = pos;
                    }
                    break;
                    case 11: {// estado do livro posição
                        int posicion = ((Integer) p.getObj()).intValue();
                        if (B.livro(posicion).disponibilidade()) {
                            B.livro(posicion).setDisponible(false);
                        } else {
                            B.livro(posicion).setDisponible(true);
                        }
                    }
                    break;
                    case 12: {//O estado do usuario pos
                        int pos = ((Integer) p.getObj()).intValue();
                        if (B.usuario(pos).temLivro()) {
                            B.usuario(pos).setTemLivro(false);
                        } else {
                            B.usuario(pos).setTemLivro(true);
                        }
                    }
                    break;
                    case 13: {//O livro posição está disponivel?
                        int posicion = ((Integer) p.getObj()).intValue();
                        obj = B.livro(posicion).disponibilidade();
                    }
                    break;
                    case 14: {//devolve o livro posição
                        int posicion = ((Integer) p.getObj()).intValue();
                        obj = B.livro(posicion);
                    }
                    break;
                    case 15: {//atribuí ao usuário depois que ele pediu Livro
                        Object[] array = (Object[]) p.getObj();
                        int pos = ((Integer) array[0]).intValue();
                        Livro lib = (Livro) array[1];
                        B.usuario(pos).setLib(lib);
                    }
                    break;
                    case 16: {
                        String mensaje = (String) p.getObj();
                        Socket s2 = null;
                        ObjectOutputStream oos2 = null;
                        for (int i = 0; i < n; i++) {
                            try {
                                String cliente = String.valueOf(clientes[i]);
                                int X = cliente.length();
                                s2 = new Socket(cliente.substring(1, X), 8013); //argumentos: HOST, PORTO
                                oos2 = new ObjectOutputStream(s2.getOutputStream());
                                oos2.flush();
                                oos2.writeObject("- " + mensaje);

                            } catch (ConnectException e) {
                                System.err.println("Error: " + e.getMessage());
                            } catch (IOException e) {
                                System.err.println("Error: " + e.getMessage());
                            } finally {
                                try {
                                    if (oos2 != null) {
                                        oos2.close();
                                    }
                                    if (s2 != null) {
                                        s2.close();
                                    }
                                } catch (IOException e) {
                                    System.err.println("Error: " + e.getMessage());
                                }
                            }
                        }
                    }
                    break;
                }
                oos.writeObject(obj);
                guardarFichero();
                s1.close();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                System.err.println("Error" + e.getMessage());
            }
        }
    }

    private void enviarFichero() {
        try {
            File fichero = new File("fichero");
            if (!fichero.exists()) {
                B = new Biblioteca();
            } else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fichero"));
                B = (Biblioteca) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void guardarFichero() {
        try {
            ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("fichero"));
            ous.writeObject(B);
            ous.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
