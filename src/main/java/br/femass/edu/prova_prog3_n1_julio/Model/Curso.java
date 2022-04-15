package br.femass.edu.prova_prog3_n1_julio.Model;


public enum Curso {


    SI( "Sistemas_de_Informação"),
    EP ("Engenharia_de_Produção"),
    ADM ("Administração"),
    MAT( "Matemática");

    private String nome;

    Curso(String nome) {
        this.nome = nome;
    }

   /* @Override
    public String toString() {
        return this.nome;
    }*/
}