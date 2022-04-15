package model;

import br.femass.edu.prova_prog3_n1_julio.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void incluirEmprestimo() {
        List<Emprestimo> emprestimosOriginal = usuario.getEmprestimos();
        Integer emprestimosEsperados = emprestimosOriginal.size()+1;
        Emprestimo emprestimo = new Emprestimo(usuario);
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
        List<Emprestimo> emprestimosOriginal = usuario.getEmprestimos();
        Integer emprestimosEsperados;
        Emprestimo emprestimo = new Emprestimo(usuario);
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
            usuario.excluirEmprestimo(usuario.getEmprestimos().get(0));
            emprestimosEsperados=0;
            copiasDisponiveisEsperadas=emprestimo.getLivro().copiasDisponiveis();
            Assertions.assertEquals(copiasDisponiveisEsperadas, copiasDisponiveisOriginais);
        }
        else {
            usuario.excluirEmprestimo(usuario.getEmprestimos().get(0));
            emprestimosEsperados = emprestimosOriginal.size()-1;
            copiasDisponiveisEsperadas=emprestimo.getLivro().copiasDisponiveis();
            Assertions.assertEquals(copiasDisponiveisEsperadas, copiasDisponiveisOriginais+1);

        }

        Assertions.assertEquals(emprestimosEsperados, usuario.getEmprestimos().size());
    }
}