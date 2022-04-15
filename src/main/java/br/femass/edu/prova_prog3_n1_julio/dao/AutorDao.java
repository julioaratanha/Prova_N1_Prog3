package br.femass.edu.prova_prog3_n1_julio.dao;

import br.femass.edu.prova_prog3_n1_julio.Model.Autor;
import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import com.thoughtworks.xstream.XStream;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication.autorDao;

public class AutorDao implements Dao<Autor> {

    private static String nomeArquivo = "autores.xml";
    private Set<Autor> autores = new HashSet<>();

    private void persistir(){
        XStream xstream = new XStream();
        try{

            //ArrayConverter converter = new ArrayConverter();
            String xml=xstream.toXML(autores);
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

    }

    private Boolean verificaSeAutorExiste(Autor autor){
            for (Autor aut : autores){
                if (autor.equals(aut)) return true;
            }
            return false;
    }

    @Override
    public void gravar(Autor autor) throws Exception {
        //listar();
        if (verificaSeAutorExiste(autor)) autores.add(autor);
        //persistir();
    }

    @Override
    public Set<Autor> listar() throws Exception {
        /*
        try{
            File arquivo = new File(nomeArquivo);
            XStream xstream = new XStream();
            livros = (List<Livro>) xstream.fromXML(arquivo);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return autores;
    }

    @Override
    public void excluir(Autor autor) throws Exception {
        autores.remove(autor);
        //persistir();
    }
}
