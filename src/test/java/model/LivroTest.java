package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Autor;
import br.femass.edu.prova_prog3_n1_julio.Model.Copia;
import br.femass.edu.prova_prog3_n1_julio.Model.Genero;
import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    Livro livro = new Livro();

    @Test
    void setCodigo() {
        Integer esperado = 8;
        livro.setCodigo(esperado);
        Assertions.assertEquals(esperado, livro.getCodigo());
    }

    @Test
    void setTitulo() {
        String esperado = "O Amanhecer";
        livro.setTitulo(esperado);
        Assertions.assertEquals(esperado, livro.getTitulo());
    }

    @Test
    void setAutor() {
        Autor esperado = new Autor("George", "Orwell");
        livro.setAutor(esperado);
        Assertions.assertEquals(esperado, livro.getAutor());
    }

    @Test
    void setGenero() {
        Genero esperado = new Genero("Ficção");
        livro.setGenero(esperado);
        Assertions.assertEquals(esperado, livro.getGenero());
    }

    @Test
    void setAno() {
        Integer esperado = 2022;
        livro.setAno(esperado);
        Assertions.assertEquals(esperado, livro.getAno());
    }

    @Test
    void setEdicao() {
        String esperado = "3";
        livro.setEdicao(esperado);
        Assertions.assertEquals(esperado, livro.getEdicao());
    }

    @Test
    void adicionarCopia() {
        Integer esperado = livro.getCopias().size()+1;
        Copia copia = new Copia(livro);
        if (livro.getCopias().isEmpty()){
            livro.adicionarCopia(copia);
            Assertions.assertTrue(copia.getFixo());
        }else livro.adicionarCopia(copia);
        Assertions.assertEquals(esperado, livro.getCopias().size());
    }

    @Test
    void removerCopia() {
        Integer esperado = livro.getCopias().size()-1;
        if (!this.livro.getCopias().isEmpty()) {
            livro.removerCopia(livro.getCopias().iterator().next());
            Assertions.assertEquals(esperado, livro.getCopias().size());
        }else Assertions.assertEquals(0, livro.getCopias().size());
    }

    @Test
    void copiasDisponiveis() {
        Livro teste = new Livro();
        livro.adicionarCopia(new Copia(teste));
        livro.adicionarCopia(new Copia(teste));
        livro.adicionarCopia(new Copia(teste));
        livro.adicionarCopia(new Copia(teste));
        livro.adicionarCopia(new Copia(teste));
        livro.getCopias().iterator().next().setEmprestada(true);
        Integer esperado = 3;
        Assertions.assertEquals(esperado, livro.copiasDisponiveis());
    }
}