package br.femass.edu.prova_prog3_n1_julio.dao;

import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;
//import com.thoughtworks.xstream.converters.collections;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.security.AnyTypePermission;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LivroDao implements Dao<Livro> {
    private static String nomeArquivo = "livros.xml";
    private Set<Livro> livros = new HashSet<>();

    private void persistir(){
        XStream xstream = new XStream();
        try{
            String xml=xstream.toXML(livros);

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

    @Override
    public void gravar(Livro livro) throws Exception {
        livros.add(livro);
        persistir();
    }

    @Override
    public Set<Livro> listar() throws Exception {
        try{
            File arquivo = new File(nomeArquivo);
            XStream xstream = new XStream();
            xstream.allowTypes(new Class[] {br.femass.edu.prova_prog3_n1_julio.Model.Livro.class});
            xstream.allowTypes(new Class[] {br.femass.edu.prova_prog3_n1_julio.Model.Autor.class});
            xstream.allowTypes(new Class[] {br.femass.edu.prova_prog3_n1_julio.Model.Copia.class});
            xstream.allowTypes(new Class[] {br.femass.edu.prova_prog3_n1_julio.Model.Genero.class});
            livros = (Set<Livro>) xstream.fromXML(arquivo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return livros;
    }

    @Override
    public void excluir(Livro livro) throws Exception {
        livros.remove(livro);
        persistir();
    }
}
