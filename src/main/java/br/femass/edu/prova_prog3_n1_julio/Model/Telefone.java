package br.femass.edu.prova_prog3_n1_julio.Model;

import java.util.*;


public class Telefone {


    private Integer ddd;
    private Long numero;

    public Telefone(Integer ddd, Long numero) {
        this.ddd=ddd;
        this.numero=numero;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

}