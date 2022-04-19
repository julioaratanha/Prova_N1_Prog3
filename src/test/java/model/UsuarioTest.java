package model;

import br.femass.edu.prova_prog3_n1_julio.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    Usuario usuario = new Usuario();

    @Test
    void setCodigo() {
        Integer esperado = 5;
        usuario.setCodigo(esperado);
        Assertions.assertEquals(esperado, usuario.getCodigo());
    }

    @Test
    void setNome() {
        String esperado = "Julio";
        usuario.setNome(esperado);
        Assertions.assertEquals(esperado, usuario.getNome());
    }

    @Test
    void setEndereco() {
        Endereco esperado = new Endereco("Rua X", 100, "Ap.103", "Praia Campista", "Macaé", "RJ");
        usuario.setEndereco(esperado);
        Assertions.assertEquals(esperado, usuario.getEndereco());
    }

    @Test
    void setTelefone() {
        Telefone esperado = new Telefone(22, 987775588L);
        usuario.setTelefone(esperado);
        Assertions.assertEquals(esperado, usuario.getTelefone());
    }

    @Test
    void setPrazoDevolucao() {
        Integer esperado = 10;
        usuario.setPrazoDevolucao(esperado);
        Assertions.assertEquals(esperado, usuario.getPrazoDevolucao());
    }

    @Test
    void numeroDeEmprestimosAtivosENumeroDeReservas(){
        Usuario usuario1 = new Usuario();
        usuario1.setPrazoDevolucao(10);
        Livro livro1 = new Livro();
        livro1.adicionarCopia(new Copia(livro1));
        livro1.adicionarCopia(new Copia(livro1));
        Livro livro2 = new Livro();
        livro2.adicionarCopia(new Copia(livro2));
        livro2.adicionarCopia(new Copia(livro2));
        Livro livro3 = new Livro();
        livro3.adicionarCopia(new Copia(livro3));
        livro3.adicionarCopia(new Copia(livro3));
        Livro livro4 = new Livro();
        livro4.adicionarCopia(new Copia(livro4));
        livro4.adicionarCopia(new Copia(livro4));
        Livro livro5 = new Livro();
        livro5.adicionarCopia(new Copia(livro5));
        livro5.adicionarCopia(new Copia(livro5));
        usuario1.incluirEmprestimo(new Emprestimo(usuario1, livro1));
        usuario1.incluirEmprestimo(new Emprestimo(usuario1, livro2));
        usuario1.incluirEmprestimo(new Emprestimo(usuario1, livro3));
        usuario1.incluirEmprestimo(new Emprestimo(usuario1, livro4));
        usuario1.incluirEmprestimo(new Emprestimo(usuario1, livro5));
        Integer tresEmprestimosAtivos=1;
        for (Emprestimo emprestimo: usuario1.getEmprestimos()){
            if (tresEmprestimosAtivos<=3) emprestimo.setAtivo(true);
            tresEmprestimosAtivos++;
        }
        Assertions.assertEquals(3, usuario1.numeroDeEmprestimosAtivos());
        Assertions.assertEquals(2, usuario1.numeroDeReservas());
    }

    @Test
    void incluirEmprestimo() {
        Set<Emprestimo> emprestimosOriginal = usuario.getEmprestimos();
        Integer emprestimosEsperados = emprestimosOriginal.size()+1;
        Livro livro = new Livro();
        Emprestimo emprestimo = new Emprestimo(usuario, livro);
        emprestimo.setLivro(new Livro());
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        Integer copiasDisponiveisEsperadas = emprestimo.getLivro().copiasDisponiveis()-1;
        usuario.incluirEmprestimo(emprestimo);
        Assertions.assertEquals(emprestimosEsperados, usuario.getEmprestimos().size());
        Assertions.assertEquals(copiasDisponiveisEsperadas, emprestimo.getLivro().copiasDisponiveis());
    }

    @Test
    void excluirEmprestimo() {
        Set<Emprestimo> emprestimosOriginal = usuario.getEmprestimos();
        Integer emprestimosEsperados;
        Livro livro = new Livro();
        Emprestimo emprestimo = new Emprestimo(usuario, livro);
        emprestimo.setLivro(new Livro());
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        emprestimo.getLivro().adicionarCopia(new Copia(emprestimo.getLivro()));
        Integer copiasDisponiveisOriginais=emprestimo.getLivro().copiasDisponiveis();
        Integer copiasDisponiveisEsperadas;
        if (emprestimosOriginal.isEmpty()) {
            usuario.incluirEmprestimo(emprestimo);
            usuario.excluirEmprestimo(emprestimo);
            emprestimosEsperados=0;
            copiasDisponiveisEsperadas=emprestimo.getLivro().copiasDisponiveis();
            Assertions.assertEquals(copiasDisponiveisEsperadas, copiasDisponiveisOriginais);
        }
        else {
            usuario.excluirEmprestimo(emprestimo);
            emprestimosEsperados = emprestimosOriginal.size()-1;
            copiasDisponiveisEsperadas=emprestimo.getLivro().copiasDisponiveis();
            Assertions.assertEquals(copiasDisponiveisEsperadas, copiasDisponiveisOriginais+1);

        }

        Assertions.assertEquals(emprestimosEsperados, usuario.getEmprestimos().size());
    }

    @Test
    void testeToString(){
        Aluno aluno = new Aluno(Curso.Sistemas_de_Informação);
        aluno.setNome("Fulano de Tal");
        Professor professor = new Professor();
        professor.setNome("Cicrano de Tal");
        Assertions.assertEquals("Aluno de Sistemas_de_Informação -> Fulano de Tal", aluno.toString());
        Assertions.assertEquals("Professor -> Cicrano de Tal", professor.toString());
    }

    @Test
    void testeEquals(){
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Fulano de Tal");
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Fulano de Tal");
        Assertions.assertTrue(usuario1.equals(usuario2));
    }
}