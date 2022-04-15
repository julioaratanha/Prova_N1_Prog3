package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Endereco;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    private Endereco endereco = new Endereco("Rua X", 100, "Ap. 201", "Praia Campista", "Macaé", "RJ");

    @org.junit.jupiter.api.Test
    void getLogradouro() {
        String esperado = "Rua X";
        Assertions.assertEquals(esperado, endereco.getLogradouro());
    }

    @org.junit.jupiter.api.Test
    void setLogradouro() {
        String esperado = "Rua X";
        endereco.setLogradouro(esperado);
        Assertions.assertEquals(esperado, endereco.getLogradouro());
    }

    @org.junit.jupiter.api.Test
    void getNumero() {
        Integer esperado = 100;
        Assertions.assertEquals(esperado, endereco.getNumero());
    }

    @org.junit.jupiter.api.Test
    void setNumero() {
        Integer esperado = 200;
        endereco.setNumero(esperado);
        Assertions.assertEquals(esperado, endereco.getNumero());
    }

    @org.junit.jupiter.api.Test
    void getComplemento() {
        String esperado = "Ap. 201";
        Assertions.assertEquals(esperado, endereco.getComplemento());
    }

    @org.junit.jupiter.api.Test
    void setComplemento() {
        String esperado = "Ap. 202";
        endereco.setComplemento(esperado);
        Assertions.assertEquals(esperado, endereco.getComplemento());
    }

    @org.junit.jupiter.api.Test
    void getBairro() {
        String esperado = "Praia Campista";
        Assertions.assertEquals(esperado, endereco.getBairro());
    }

    @org.junit.jupiter.api.Test
    void setBairro() {
        String esperado = "Praia do Pecado";
        endereco.setBairro(esperado);
        Assertions.assertEquals(esperado, endereco.getBairro());
    }

    @org.junit.jupiter.api.Test
    void getCidade() {
        String esperado = "Macaé";
        Assertions.assertEquals(esperado, endereco.getCidade());
    }

    @org.junit.jupiter.api.Test
    void setCidade() {
        String esperado = "Rio das Ostras";
        endereco.setCidade(esperado);
        Assertions.assertEquals(esperado, endereco.getCidade());
    }

    @org.junit.jupiter.api.Test
    void getUf() {
        String esperado = "RJ";
        Assertions.assertEquals(esperado, endereco.getUf());
    }

    @org.junit.jupiter.api.Test
    void setUf() {
        String esperado = "MG";
        endereco.setUf(esperado);
        Assertions.assertEquals(esperado, endereco.getUf());
    }
}