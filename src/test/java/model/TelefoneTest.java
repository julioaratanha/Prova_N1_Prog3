package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Telefone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelefoneTest {

    Telefone telefone = new Telefone(22, 981113366L);

    @Test
    void getDdd() {
        Integer esperado = 22;
        Assertions.assertEquals(esperado, telefone.getDdd());
    }

    @Test
    void setDdd() {
        Integer esperado = 21;
        telefone.setDdd(esperado);
        Assertions.assertEquals(esperado, telefone.getDdd());
    }

    @Test
    void getNumero() {
        Long esperado = 981113366L;
        Assertions.assertEquals(esperado, telefone.getNumero());
    }

    @Test
    void setNumero() {
        Long esperado = 982114364L;
        telefone.setNumero(esperado);
        Assertions.assertEquals(esperado, telefone.getNumero());
    }
}