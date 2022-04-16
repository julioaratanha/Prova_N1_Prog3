package br.femass.edu.prova_prog3_n1_julio.Model;


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
         //LocalDate agora = LocalDateTime.now().toLocalDate();
         //this.dataEmprestimo= agora.toLocalDate();
         //LocalDate devolucao = agora.plusDays(this.usuario.getPrazoDevolucao());
         //this.dataDevolucaoPrevista=devolucao;
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
        if (this.ativo) return this.livro.getTitulo()+" - Data de Devolução prevista: "+this.dataDevolucaoPrevista;
        return " Reservado: "+this.livro.getTitulo()+" - Aguardando confirmação";
    }
}