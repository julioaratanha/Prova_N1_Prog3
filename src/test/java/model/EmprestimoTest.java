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
        LocalDate esperado = LocalDate.of(2022, 05, 20);
        emprestimo.setDataEmprestimoEDataDevolucaoPrevista(esperado);
        Integer mesEsperado = esperado.getMonthValue();
        Integer diaEsperado = esperado.getDayOfMonth();
        Integer anoEsperado = esperado.getYear();
        Integer mesObservado = emprestimo.getDataEmprestimo().getMonthValue();
        Integer diaObservado = emprestimo.getDataEmprestimo().getDayOfMonth();
        Integer anoObservado = emprestimo.getDataEmprestimo().getYear();
        Assertions.assertEquals(mesEsperado, mesObservado);
        Assertions.assertEquals(diaEsperado, diaObservado);
        Assertions.assertEquals(anoEsperado, anoObservado);
    }

    @Test
    void getDataDevolucaoPrevista() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDate hoje = agora.toLocalDate();
        Integer prazo=emprestimo.getUsuario().getPrazoDevolucao();
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

}