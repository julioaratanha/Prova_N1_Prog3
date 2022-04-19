package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Aluno;
import br.femass.edu.prova_prog3_n1_julio.Model.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    void getESetCurso() {
        Aluno aluno= new Aluno(Curso.Sistemas_de_Informação);
        aluno.setCurso(Curso.Administração);
        Assertions.assertEquals(Curso.Administração, aluno.getCurso());
    }

}