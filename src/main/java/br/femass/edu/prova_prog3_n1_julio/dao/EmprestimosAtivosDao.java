package br.femass.edu.prova_prog3_n1_julio.dao;

import br.femass.edu.prova_prog3_n1_julio.Model.Emprestimo;

import java.util.HashSet;
import java.util.Set;

public class EmprestimosAtivosDao implements Dao<Emprestimo> {

    private static String nomeArquivo = "emprestimos_ativos.xml";
    private Set<Emprestimo> emprestimosAtivos = new HashSet<>();

/*
    private void persistir(){
        XStream xstream = new XStream();
        try{

            //ArrayConverter converter = new ArrayConverter();
            String xml=xstream.toXML(emprestimosAtivos);
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
        emprestimosAtivos.add(emprestimo);
        //persistir();
    }

    @Override
    public Set<Emprestimo> listar() throws Exception {
        /*
        try{
            File arquivo = new File(nomeArquivo);
            XStream xstream = new XStream();
            emprestimosAtivos = (Set<Emprestimo>) xstream.fromXML(arquivo);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return emprestimosAtivos;
    }

    @Override
    public void excluir(Emprestimo emprestimo) throws Exception {
        emprestimosAtivos.remove(emprestimo);
        //persistir();
    }
}
