package br.femass.edu.prova_prog3_n1_julio.Model;


public class Aluno extends Usuario {

    private Curso curso;

    public Aluno(Curso curso) {
        this.prazoDevolucao=5;
        this.curso=curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}