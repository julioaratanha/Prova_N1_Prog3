package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Genero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneroTest {

    @Test
    void testEquals() {
        Genero genero1 = new Genero("Genero");
        Genero genero2 = new Genero("Genero");
        Assertions.assertEquals(genero1, genero2);
    }
}