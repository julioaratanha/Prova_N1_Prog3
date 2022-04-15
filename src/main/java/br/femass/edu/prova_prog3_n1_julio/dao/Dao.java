package br.femass.edu.prova_prog3_n1_julio.dao;

import java.util.Set;

public interface Dao<T> {
    public void gravar(T objeto) throws Exception;
    public Set listar() throws Exception;
    public void excluir(T objeto) throws Exception;
}
