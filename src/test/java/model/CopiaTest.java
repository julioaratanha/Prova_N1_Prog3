package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Copia;
import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CopiaTest {
    Livro livro = new Livro();
    Copia copia = new Copia(livro);

    @Test
    void setEGetCodigo() {
        Integer esperado = 6;
        copia.setCodigo(esperado);
        Assertions.assertEquals(esperado, copia.getCodigo());
    }

    @Test
    void setEGetFixo() {
        Boolean esperado = true;
        copia.setFixo(true);
        Assertions.assertEquals(esperado, copia.getFixo());
    }

    @Test
    void getLivro() {
        Assertions.assertEquals(livro, copia.getLivro());
    }

    @Test
    void setLivro() {
        Livro esperado = new Livro();
        copia.setLivro(esperado);
        Assertions.assertEquals(esperado, copia.getLivro());
    }

    @Test
    void setEGetEmprestada() {
        Boolean esperado = true;
        copia.setEmprestada(esperado);
        Assertions.assertEquals(esperado, copia.getEmprestada());
    }

}