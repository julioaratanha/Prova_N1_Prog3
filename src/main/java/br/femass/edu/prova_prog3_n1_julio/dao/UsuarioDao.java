package br.femass.edu.prova_prog3_n1_julio.dao;

import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import br.femass.edu.prova_prog3_n1_julio.Model.Usuario;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.collections.ArrayConverter;
import com.thoughtworks.xstream.mapper.Mapper;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsuarioDao implements Dao<Usuario> {
    private static final String nomeArquivo = "usuario.xml";
    private Set<Usuario> usuarios = new HashSet<>();

    private void persistir(){
        XStream xstream = new XStream();
        try{

            //ArrayConverter converter = new ArrayConverter();
            String xml=xstream.toXML(usuarios);
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
    public void gravar(Usuario usuario) throws Exception {
        //listar();
        usuarios.add(usuario);
        //persistir();
    }

    @Override
    public Set<Usuario> listar() throws Exception {
        /*
        try{
            File arquivo = new File(nomeArquivo);
            XStream xstream = new XStream();
            usuarios = (List<Usuario>) xstream.fromXML(arquivo);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return usuarios;
    }

    @Override
    public void excluir(Usuario usuario) throws Exception {
        usuarios.remove(usuario);
        //persistir();
    }

}
