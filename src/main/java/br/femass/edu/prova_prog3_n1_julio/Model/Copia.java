package br.femass.edu.prova_prog3_n1_julio.Model;

import java.util.*;

public class Copia {
    private Integer codigo;
    private Boolean fixo=false;
    private Boolean emprestada=false;
    private Livro livro;

    public Copia(Livro livro) {
        this.codigo=GeradorCodigo.codigoCopia+1;
        GeradorCodigo.codigoCopia++;
        this.livro=livro;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Boolean getEmprestada() {
        return emprestada;
    }

    public void setEmprestada(Boolean emprestada) {
        this.emprestada = emprestada;
    }

    @Override
    public String toString() {
        if (this.fixo) return "Cópia número "+this.codigo+" do livro '"+ this.livro.getTitulo()+"' (Proibido empréstimo! Leitura apenas na Biblioteca!)";
        else return "Cópia número "+this.codigo+" do livro '"+ this.livro.getTitulo()+"'";
    }
}