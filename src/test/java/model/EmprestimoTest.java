package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Copia;
import br.femass.edu.prova_prog3_n1_julio.Model.Emprestimo;
import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import br.femass.edu.prova_prog3_n1_julio.Model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoTest {

    Usuario usuario = new Usuario();
    Livro livro = new Livro();
    Emprestimo emprestimo = new Emprestimo(usuario, livro);

    @Test
    void getDataEmprestimo() {
        LocalDate hoje = LocalDateTime.now().toLocalDate();
        emprestimo.setDataEmprestimoEDataDevolucaoPrevista(hoje);
        Integer mesEsperado = hoje.getMonthValue();
        Integer diaEsperado = hoje.getDayOfMonth();
        Integer anoEsperado = hoje.getYear();
        Integer mesObservado = emprestimo.getDataEmprestimo().getMonthValue();
        Integer diaObservado = emprestimo.getDataEmprestimo().getDayOfMonth();
        Integer anoObservado = emprestimo.getDataEmprestimo().getYear();
        Assertions.assertEquals(mesEsperado, mesObservado);
        Assertions.assertEquals(diaEsperado, diaObservado);
        Assertions.assertEquals(anoEsperado, anoObservado);
    }

    @Test
    void setDataEmprestimoEDevolucaoPrevista() {
        LocalDate esperadoEmprestimo = LocalDate.of(2022, 05, 20);
        emprestimo.getUsuario().setPrazoDevolucao(10);
        emprestimo.setDataEmprestimoEDataDevolucaoPrevista(esperadoEmprestimo);
        LocalDate esperadoDevolucao = emprestimo.getDataEmprestimo().plusDays(emprestimo.getUsuario().getPrazoDevolucao());
        Integer mesEsperadoEmprestimo = esperadoEmprestimo.getMonthValue();
        Integer diaEsperadoEmprestimo = esperadoEmprestimo.getDayOfMonth();
        Integer anoEsperadoEmprestimo = esperadoEmprestimo.getYear();
        Integer mesEsperadoDevolucao = esperadoDevolucao.getMonthValue();
        Integer diaEsperadoDevolucao = esperadoDevolucao.getDayOfMonth();
        Integer anoEsperadoDevolucao = esperadoDevolucao.getYear();
        Integer mesObservadoEmprestimo = emprestimo.getDataEmprestimo().getMonthValue();
        Integer diaObservadoEmprestimo = emprestimo.getDataEmprestimo().getDayOfMonth();
        Integer anoObservadoEmprestimo = emprestimo.getDataEmprestimo().getYear();
        Integer mesObservadoDevolucao = emprestimo.getDataDevolucaoPrevista().getMonthValue();
        Integer diaObservadoDevolucao = emprestimo.getDataDevolucaoPrevista().getDayOfMonth();
        Integer anoObservadoDevolucao = emprestimo.getDataDevolucaoPrevista().getYear();
        Assertions.assertEquals(mesEsperadoEmprestimo, mesObservadoEmprestimo);
        Assertions.assertEquals(diaEsperadoEmprestimo, diaObservadoEmprestimo);
        Assertions.assertEquals(anoEsperadoEmprestimo, anoObservadoEmprestimo);
        Assertions.assertEquals(mesEsperadoDevolucao, mesObservadoDevolucao);
        Assertions.assertEquals(diaEsperadoDevolucao, diaObservadoDevolucao);
        Assertions.assertEquals(anoEsperadoDevolucao, anoObservadoDevolucao);
        Assertions.assertTrue(emprestimo.getAtivo());
    }

    @Test
    void setDataDevolucaoEfetiva(){
        Emprestimo emprestimo1 = new Emprestimo(new Usuario(), new Livro());
        emprestimo1.setDataEmprestimoEDataDevolucaoPrevista(LocalDate.of(2022, 05, 20));
        Assertions.assertTrue(emprestimo1.getAtivo());
        emprestimo1.setDataDevolucaoEfetiva(LocalDate.of(2022, 05, 25));
        Assertions.assertEquals(LocalDate.of(2022, 05, 25), emprestimo1.getDataDevolucaoEfetiva());
        Assertions.assertFalse(emprestimo1.getAtivo());
    }

    @Test
    void getDataDevolucaoPrevista() {
        LocalDate hoje = LocalDateTime.now().toLocalDate();
        emprestimo.getUsuario().setPrazoDevolucao(10);
        Integer prazo=emprestimo.getUsuario().getPrazoDevolucao();
        emprestimo.setDataEmprestimoEDataDevolucaoPrevista(hoje);
        LocalDate esperado = hoje.plusDays(prazo);
        Integer mesEsperado = esperado.getMonthValue();
        Integer diaEsperado = esperado.getDayOfMonth();
        Integer anoEsperado = esperado.getYear();
        Integer mesObservado = emprestimo.getDataDevolucaoPrevista().getMonthValue();
        Integer diaObservado = emprestimo.getDataDevolucaoPrevista().getDayOfMonth();
        Integer anoObservado = emprestimo.getDataDevolucaoPrevista().getYear();
        Assertions.assertEquals(mesEsperado, mesObservado);
        Assertions.assertEquals(diaEsperado, diaObservado);
        Assertions.assertEquals(anoEsperado, anoObservado);
    }

    @Test
    void setLivro() {
        Livro esperado = new Livro();
        emprestimo.setLivro(esperado);
        Assertions.assertEquals(esperado, emprestimo.getLivro());
    }

    @Test
    void testeToString(){
        Emprestimo emprestimo2 = new Emprestimo(new Usuario(), new Livro());
        emprestimo2.getLivro().setTitulo("Livro qualquer");
        emprestimo2.getUsuario().setPrazoDevolucao(10);
        Assertions.assertEquals("Reservado: Livro qualquer - Aguardando confirmação", emprestimo2.toString());
        emprestimo2.setDataEmprestimoEDataDevolucaoPrevista(LocalDate.of(2022, 05, 20));
        Assertions.assertEquals("Livro qualquer - Data de Devolução prevista: 30/05/2022", emprestimo2.toString());
        emprestimo2.setDataDevolucaoEfetiva(LocalDate.of(2022, 05, 30));
        Assertions.assertEquals("Livro qualquer - Data de Devolução efetiva: 30/05/2022", emprestimo2.toString());
    }

}