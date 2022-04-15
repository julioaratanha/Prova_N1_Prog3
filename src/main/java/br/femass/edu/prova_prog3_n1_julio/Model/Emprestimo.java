package br.femass.edu.prova_prog3_n1_julio.Model;


import java.util.*;
import java.time.*;



public class Emprestimo {

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;

    private Usuario usuario;
    private Livro livro;
    public Emprestimo(Usuario usuario) {
         this.usuario=usuario;
         LocalDateTime agora = LocalDateTime.now();
         this.dataEmprestimo= agora.toLocalDate();
         LocalDateTime devolucao = agora.plusDays(this.usuario.getPrazoDevolucao());
         this.dataDevolucaoPrevista=devolucao.toLocalDate();
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
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
}