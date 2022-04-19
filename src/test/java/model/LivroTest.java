package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Autor;
import br.femass.edu.prova_prog3_n1_julio.Model.Copia;
import br.femass.edu.prova_prog3_n1_julio.Model.Genero;
import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Livro livro1 = new Livro();
        Integer esperado = 0;
        Copia copia1 = new Copia(livro);
        Copia copia2 = new Copia(livro);
        Copia copia3 = new Copia(livro);
        Set<Copia> copias = new HashSet<>();
        copias.add(copia1);
        copias.add(copia2);
        copias.add(copia3);
        for (Copia copia : copias){
            esperado++;
            if (livro1.getCopias().isEmpty()) {
                livro1.adicionarCopia(copia);
                Assertions.assertTrue(copia.getFixo());
            }else {
                livro1.adicionarCopia(copia);
                Assertions.assertFalse(copia.getFixo());
            }
        }
        Assertions.assertEquals(esperado, livro1.getCopias().size());
    }

    @Test
    void removerCopia() {
        Livro livro1 = new Livro();
        Copia copia1 = new Copia(livro);
        Copia copia2 = new Copia(livro);
        Copia copia3 = new Copia(livro);
        copia3.setEmprestada(true);
        livro1.adicionarCopia(copia1);
        livro1.adicionarCopia(copia2);
        livro1.adicionarCopia(copia3);
        livro1.removerCopia();
        Integer esperado = 2;
        Assertions.assertEquals(esperado, livro1.getCopias().size());
        livro1.removerCopia();
        Assertions.assertEquals(esperado, livro1.getCopias().size());
        for (Copia copia : livro1.getCopias()){
            if (copia.getEmprestada()) {
                copia.setEmprestada(false);
                break;
            }
        }
        livro1.removerCopia();
        esperado=1;
        Assertions.assertEquals(esperado, livro1.getCopias().size());
    }

    @Test
    void copiasDisponiveis() {
        Livro teste = new Livro();
        teste.adicionarCopia(new Copia(teste));
        teste.adicionarCopia(new Copia(teste));
        teste.adicionarCopia(new Copia(teste));
        teste.adicionarCopia(new Copia(teste));
        teste.adicionarCopia(new Copia(teste));
        teste.getCopias().iterator().next().setEmprestada(true);
        Integer esperado = 3;
        Assertions.assertEquals(esperado, teste.copiasDisponiveis());
    }

    @Test
    void testeToString(){
        Livro livro2 = new Livro();
        livro2.setTitulo("Titulo do livro");
        livro2.setAutor(new Autor("Fulano", "de Tal"));
        String esperado = "Titulo do livro - Fulano de Tal - Cópias disponíveis: 0";
        Assertions.assertEquals(esperado, livro2.toString());
    }

    @Test
    void testeEquals(){
        Livro livro3 = new Livro();
        livro3.setTitulo("Titulo");
        livro3.setAutor(new Autor("Fulano", "Tal"));
        livro3.setGenero(new Genero("Qualquer"));
        livro3.setAno(2022);
        livro3.setEdicao("10");

        Livro livro4 = new Livro();
        livro4.setTitulo("Titulo");
        livro4.setAutor(new Autor("Fulano", "Tal"));
        livro4.setGenero(new Genero("Qualquer"));
        livro4.setAno(2022);
        livro4.setEdicao("10");

        Assertions.assertTrue(livro3.equals(livro4));

    }
}