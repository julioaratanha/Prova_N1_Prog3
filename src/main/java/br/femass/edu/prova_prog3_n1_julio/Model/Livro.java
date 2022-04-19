package br.femass.edu.prova_prog3_n1_julio.Model;

import java.util.*;
import com.thoughtworks.xstream.XStream;

public class Livro {

    private Integer codigo;
    private String titulo;
    private Autor autor;
    private Genero genero;
    private Integer ano;
    private String edicao;
    private Set<Copia> copias = new HashSet<>();

    public Livro() {
        this.codigo = GeradorCodigo.codigoLivro + 1;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public void adicionarCopia(Copia copia) {
        if (this.copias.isEmpty())
            copia.setFixo(true);
        this.copias.add(copia);
    }

    public void removerCopia() {
        for (Copia copia : this.copias) {
            // remove apenas uma copia que não seja fixa e que não esteja emprestada.
            // não é possível remover caso só haja uma cópia.
            if (!copia.getFixo() && (!copia.getEmprestada())) {
                this.copias.remove(copia);
                break;
            }
        }
    }

    public Set<Copia> getCopias() {
        return this.copias;
    }

    public Integer copiasDisponiveis() {
        Integer resultado = 0;
        for (Copia copia : this.copias) {
            if ((!copia.getEmprestada()) && (!copia.getFixo()))
                resultado++;
        }
        return resultado;
    }

    @Override
    public String toString() {
        return this.titulo + " - " + this.autor.getNome() + " " + this.autor.getSobrenome() + " - Cópias disponíveis: "
                + this.copiasDisponiveis();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Livro))
            return false;
        Livro livro = (Livro) o;
        return getTitulo().equals(livro.getTitulo()) && getAutor().equals(livro.getAutor())
                && getGenero().equals(livro.getGenero()) && getAno().equals(livro.getAno())
                && getEdicao().equals(livro.getEdicao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getAutor(), getGenero(), getAno(), getEdicao());
    }
}