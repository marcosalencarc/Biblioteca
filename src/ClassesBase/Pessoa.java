/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesBase;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {

    private String nome;
    private String dataDeNascimento;

    //contrutor sem parametros
    public Pessoa() {
    }

    //contrutor com parametos
    public Pessoa(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataDeNascimento = dataNascimento;
    }

    //Metodos de acesso aos atributos privados
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    //redefinindo o método toString da classe Object
    @Override
    public String toString() {
        return "Nome: " + nome + "\n DataDeNascimento: " + dataDeNascimento;

    }
}
