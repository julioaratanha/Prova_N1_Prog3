package br.femass.edu.prova_prog3_n1_julio.Model;


import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;



public class Emprestimo {

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoEfetiva;

    private Usuario usuario;
    private Livro livro;
    private Boolean ativo = false;

    public Emprestimo(Usuario usuario, Livro livro) {
         this.usuario=usuario;
         this.livro=livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimoEDataDevolucaoPrevista(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(this.usuario.getPrazoDevolucao());
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }

    public void setDataDevolucaoEfetiva(LocalDate dataDevolucaoEfetiva) {
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
        setAtivo(false);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (this.ativo) return this.livro.getTitulo()+" - Data de Devolução prevista: "+this.dataDevolucaoPrevista.format(formatter);
        else if(this.dataDevolucaoEfetiva!=null) return this.livro.getTitulo()+" - Data de Devolução efetiva: "+this.dataDevolucaoEfetiva.format(formatter);
        else return "Reservado: "+this.livro.getTitulo()+" - Aguardando confirmação";
    }
}