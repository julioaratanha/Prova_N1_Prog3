package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Autor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutorTest {
    Autor autor = new Autor("Julio", "Aratanha");
    @Test
    void getNome() {
        String nomeEsperado = "Julio";
        Assertions.assertEquals(nomeEsperado, autor.getNome());
    }

    @Test
    void setNome() {
        String nomeEsperado = "Pedro";
        autor.setNome(nomeEsperado);
        Assertions.assertEquals(nomeEsperado, autor.getNome());
    }

    @Test
    void getSobrenome() {
        String sobrenomeEsperado = "Aratanha";
        Assertions.assertEquals(sobrenomeEsperado, autor.getSobrenome());
    }

    @Test
    void setSobrenome() {
        String sobrenomeEsperado = "Araujo";
        autor.setSobrenome(sobrenomeEsperado);
        Assertions.assertEquals(sobrenomeEsperado, autor.getSobrenome());
    }

    @Test
    void testeToString(){
        autor.setNome("Fulano");
        autor.setSobrenome("Tal");
        Assertions.assertEquals("Fulano Tal", autor.toString());
    }

    @Test
    void testeEquals(){
        autor.setNome("Fulano");
        autor.setSobrenome("Tal");
        Autor autor2 = new Autor("Fulano", "Tal");
        Assertions.assertTrue(autor.equals(autor2));
    }
}