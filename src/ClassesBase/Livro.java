/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBase;

import java.io.Serializable;

public class Livro implements Serializable {

    private String idLivro;
    private String tituloLivro;
    private String autorLivro;
    private boolean situacao = true;

    public Livro() {
    }

    public Livro(String cl, String tl, String al) {
        this.idLivro = cl;
        this.tituloLivro = tl;
        this.autorLivro = al;

    }

    public String getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(String idLivro) {
        this.idLivro = idLivro;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(String autorLivro) {
        this.autorLivro = autorLivro;
    }

    public boolean disponibilidade() {
        return situacao;
    }

    public void setDisponible(boolean estado) {
        this.situacao = estado;
    }

    @Override
    public String toString() {

        if (disponibilidade()) {
            return "Codigo: " + idLivro
                    + "\nTitulo: " + tituloLivro
                    + "\nAutor : " + autorLivro
                    + "\nEstado: Disponivel";
        } else {
            return "Codigo: " + idLivro
                    + "\nTitulo: " + tituloLivro
                    + "\nAutor : " + autorLivro
                    + "\nEstado: Ocupado";
        }
    }
}
