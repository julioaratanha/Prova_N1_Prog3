package model;

import br.femass.edu.prova_prog3_n1_julio.Model.Professor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    @Test
    void getPrazoDevolucao(){
        Professor professor = new Professor();
        Assertions.assertEquals(30, professor.getPrazoDevolucao());
    }

}