/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBase;

import java.io.Serializable;

public class Usuario extends Pessoa implements Serializable {

    private String codigo;
    private boolean temLivro = false;
    private Livro lib;

    public Usuario() {
    }

    public Usuario(String nome, String dataDeNascimento, String codigo) {
        super(nome, dataDeNascimento);
        this.codigo = codigo;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean temLivro() {
        return temLivro;
    }

    public void setTemLivro(boolean b) {
        temLivro = b;
    }

    public Livro getLib() {
        return lib;
    }

    public void setLib(Livro lib) {
        this.lib = lib;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nCodigo do Estuduante: " + codigo;
    }
}
