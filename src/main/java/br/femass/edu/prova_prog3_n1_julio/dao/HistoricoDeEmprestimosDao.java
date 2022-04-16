package br.femass.edu.prova_prog3_n1_julio.dao;

import br.femass.edu.prova_prog3_n1_julio.Model.Emprestimo;

import java.util.HashSet;
import java.util.Set;

public class HistoricoDeEmprestimosDao implements Dao<Emprestimo> {

    private static String nomeArquivo = "historico_de_emprestimos.xml";
    private Set<Emprestimo> historicoDeEmprestimos = new HashSet<>();
/*
    private void persistir(){
        XStream xstream = new XStream();
        try{

            //ArrayConverter converter = new ArrayConverter();
            String xml=xstream.toXML(historicoDeEmprestimos);
            FileWriter arquivo = null;
            try {
                arquivo = new FileWriter(nomeArquivo);
                arquivo.write(xml);
                arquivo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }*/

    @Override
    public void gravar(Emprestimo emprestimo) throws Exception {
        //listar();
        historicoDeEmprestimos.add(emprestimo);
        //persistir();
    }

    @Override
    public Set<Emprestimo> listar() throws Exception {
        /*
        try{
            File arquivo = new File(nomeArquivo);
            XStream xstream = new XStream();
            historicoDeEmprestimos = (Set<Emprestimo>) xstream.fromXML(arquivo);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return historicoDeEmprestimos;
    }

    @Override
    public void excluir(Emprestimo emprestimo) throws Exception {
        historicoDeEmprestimos.remove(emprestimo);
        //persistir();
    }
}
