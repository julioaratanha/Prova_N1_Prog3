package br.femass.edu.prova_prog3_n1_julio.Model;

import java.util.*;

public class Genero {
    private String nome;
    public Genero(String nome) {
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


     @Override
     public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genero)) return false;
        Genero genero = (Genero) o;
        return getNome().equals(genero.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome());
        }
}