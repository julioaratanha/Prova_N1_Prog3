package br.femass.edu.prova_prog3_n1_julio.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class Usuario {

    private Integer codigo;
    private String nome;
    private Endereco endereco;
    private Telefone telefone;
    protected Integer prazoDevolucao=0;
    private Set<Emprestimo> emprestimos = new HashSet<>();
    private Boolean atraso = false;

    public Usuario() {
        this.codigo=GeradorCodigo.codigoUsuario+1;
        GeradorCodigo.codigoUsuario++;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Integer getPrazoDevolucao() {
        return prazoDevolucao;
    }

    public void setPrazoDevolucao(Integer prazoDevolucao) {
        this.prazoDevolucao = prazoDevolucao;
    }

    public Set<Emprestimo> getEmprestimos(){
        return this.emprestimos;
    }

    public Integer numeroDeEmprestimosAtivos() {
        Integer numero = 0;
        for (Emprestimo emprestimo : getEmprestimos()){
            if (emprestimo.getAtivo()) numero++;
        }
        return numero;
    }

    public String incluirEmprestimo(Emprestimo emprestimo){
        if (this.emprestimos.size()<5){
            if (emprestimo.getLivro().copiasDisponiveis()>0) {
                //Dentre as cópias disponíveis para empréstimo, seleciona uma e muda o estado dela para emprestada
                for (Copia copia : emprestimo.getLivro().getCopias()){
                    if ((!copia.getFixo()) && (!copia.getEmprestada())){
                        copia.setEmprestada(true);
                        break;
                    }
                }
                this.emprestimos.add(emprestimo);
                return "Empréstimo efetivado com sucesso!";
            }
            return "Não foi possível efetivar empréstimo! Não há cópias deste livro disponíveis!";
        }
        else return "Não foi possível efetivar empréstimo! Limite de empréstimos atingido (5)";
    }

    public String excluirEmprestimo(Emprestimo emprestimo){
        if (!this.emprestimos.isEmpty()) {
            LocalDateTime agora = LocalDateTime.now();
            LocalDate dataEfetiva = agora.toLocalDate();
            if (dataEfetiva.isAfter(emprestimo.getDataDevolucaoPrevista())) this.atraso=true;

            for (Copia copia : emprestimo.getLivro().getCopias()){
                if ((!copia.getFixo()) && (copia.getEmprestada())){
                    copia.setEmprestada(false);
                    break;
                }
            }
            this.emprestimos.remove(emprestimo);
            if (this.atraso) {
                this.atraso=false;
                return "Empréstimo excluído com sucesso, porém, com atraso!!";
            };
            return "Empréstimo excluído com sucesso!";
        }
        else return "Não há empréstimos a excluir!";
    }



    @Override
    public String toString() {
        if (this instanceof Aluno) return "Aluno de "+((Aluno) this).getCurso()+" -> "+this.nome ;
        else return "Professor -> "+this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return nome.equals(usuario.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}