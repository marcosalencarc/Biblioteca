/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClassesBase;

import java.io.Serializable;

public class Administrador extends Pessoa implements Serializable {

    private String senha;

    public Administrador() {
    }

    public Administrador(String nome, String dataDeNascimento, String senha) {
        super(nome, dataDeNascimento);
        this.senha = senha;

    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nPassword: " + senha;

    }
}
