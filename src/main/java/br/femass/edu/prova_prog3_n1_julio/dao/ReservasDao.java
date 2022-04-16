package br.femass.edu.prova_prog3_n1_julio.dao;

import br.femass.edu.prova_prog3_n1_julio.Model.Emprestimo;

import java.util.HashSet;
import java.util.Set;

public class ReservasDao implements Dao<Emprestimo> {

    private static String nomeArquivo = "reservas.xml";
    private Set<Emprestimo> reservas = new HashSet<>();
/*
    private void persistir(){
        XStream xstream = new XStream();
        try{

            //ArrayConverter converter = new ArrayConverter();
            String xml=xstream.toXML(reservas);
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
        reservas.add(emprestimo);
        //persistir();
    }

    @Override
    public Set<Emprestimo> listar() throws Exception {
        /*
        try{
            File arquivo = new File(nomeArquivo);
            XStream xstream = new XStream();
            reservas = (Set<Emprestimo>) xstream.fromXML(arquivo);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return reservas;
    }

    @Override
    public void excluir(Emprestimo emprestimo) throws Exception {
        reservas.remove(emprestimo);
        //persistir();
    }
}
