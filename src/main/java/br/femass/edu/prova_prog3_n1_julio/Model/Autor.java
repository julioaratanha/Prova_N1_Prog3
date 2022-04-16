package br.femass.edu.prova_prog3_n1_julio.Model;

import java.util.*;


public class Autor {

    private String nome;
    private String sobrenome;

    public Autor(String nome, String sobrenome) {
        this.nome=nome;
        this.sobrenome=sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return this.nome+" "+this.sobrenome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return this.nome.equals(autor.nome) && this.sobrenome.equals(autor.sobrenome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome);
    }
}