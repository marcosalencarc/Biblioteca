/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassePrincipal;

import ClassesBase.Livro;
import ClassesBase.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Biblioteca implements Serializable {

    private ArrayList listaLivros;
    private int qntLivros = 0;
    private ArrayList listaUsuarios;
    private int qntUsers = 0;

    public Biblioteca() {
        listaUsuarios = new ArrayList();
        listaLivros = new ArrayList();
    }

    public void inserirLivro(Livro L) {
        Livro lib;
        int k;
        listaLivros.add(L);
        for (k = qntLivros - 1; k >= 0 && Integer.parseInt((lib = (Livro) listaLivros.get(k)).getIdLivro()) > Integer.parseInt(L.getIdLivro()); k--) {
            listaLivros.set(k + 1, lib);
        }
        listaLivros.set(k + 1, L);
        qntLivros++;
    }

    private int busca(int i, int f, String cod) {
        if (i == f) {
            if (Integer.parseInt(((Livro) listaLivros.get(i)).getIdLivro()) == Integer.parseInt(cod)) {
                return i;
            }
            return -1;
        }
        int m = (i + f) / 2;
        if (Integer.parseInt(cod) > Integer.parseInt(((Livro) listaLivros.get(m)).getIdLivro())) {
            return busca(m + 1, f, cod);
        }
        return busca(i, m, cod);
    }

    public int buscarLivroCodigo(String cod) {
        if (qntLivros == 0) {
            return -1;
        }
        return busca(0, qntLivros - 1, cod);
    }

    public int buscarLivroTitulo(String titulo) {

        int i = 0;
        Iterator it = listaLivros.iterator();
        while (it.hasNext()) {
            if (((Livro) it.next()).getTituloLivro().equals(titulo)) {
                return i;
            }
            i++;
        }
        return -1;

    }

    public int buscarLivroAutor(String autor) {

        int i = 0;
        Iterator it = listaLivros.iterator();
        while (it.hasNext()) {
            if (((Livro) it.next()).getAutorLivro().equals(autor)) {
                return i;
            }
            i++;
        }
        return -1;

    }

    public void editarLivro(String codigo) {
        int pos = buscarLivroCodigo(codigo);
        if (pos != -1)//encoontra o livro
        {
            ((Livro) listaLivros.get(pos)).setTituloLivro(JOptionPane.showInputDialog("Inserir novo titulo"));
            ((Livro) listaLivros.get(pos)).setAutorLivro(JOptionPane.showInputDialog("Inserir novo autor"));

        } else//não encontra o livro
        {
            JOptionPane.showMessageDialog(null, "Livro não existe", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void eliminarLibro(String cod) {
        int pos = buscarLivroCodigo(cod);
        if (pos != -1) {
            listaLivros.remove(pos);
            qntLivros--;
        } else {
            JOptionPane.showMessageDialog(null, "Libro no existe", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }

    public int quantidadeLivros() {
        return qntLivros;
    }

    public int quantidadeLivDisponibles() {
        int cont = 0;
        Iterator it = listaLivros.iterator();
        while (it.hasNext()) {
            if (((Livro) it.next()).disponibilidade()) {
                cont++;
            }
        }
        return cont;

    }

    public Livro livro(int i) {
        return (Livro) listaLivros.get(i);
    }

    public void inseirUsuario(Usuario U) {
        listaUsuarios.add(U);
        qntUsers++;
    }

    public int buscarUsuario(String cod) {
        int i = 0;
        Iterator it = listaUsuarios.iterator();
        while (it.hasNext()) {
            if (((Usuario) it.next()).getCodigo().equals(cod)) {
                return i;
            }
            i++;
        }
        return -1;

    }

    public Usuario usuario(int i) {
        return (Usuario) listaUsuarios.get(i);
    }

    public int numeroUsuarios() {
        return qntUsers;
    }
}
