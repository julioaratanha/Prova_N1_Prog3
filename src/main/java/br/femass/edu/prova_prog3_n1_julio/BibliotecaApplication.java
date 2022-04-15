package br.femass.edu.prova_prog3_n1_julio;

import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import br.femass.edu.prova_prog3_n1_julio.Model.Usuario;
import br.femass.edu.prova_prog3_n1_julio.dao.AutorDao;
import br.femass.edu.prova_prog3_n1_julio.dao.UsuarioDao;
import br.femass.edu.prova_prog3_n1_julio.gui.CopiasController;
import br.femass.edu.prova_prog3_n1_julio.dao.LivroDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class BibliotecaApplication extends Application {

    private static Stage stage;
    public static LivroDao livroDao;
    public static UsuarioDao usuarioDao;
    public static AutorDao autorDao;


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        this.livroDao= new LivroDao();
        this.usuarioDao = new UsuarioDao();
        this.autorDao = new AutorDao();
        irTelaPrincipal();
    }

    private static void trocarTela(String arquivoFXML, String titulo){
        FXMLLoader loader = new FXMLLoader(BibliotecaApplication.class.getResource(arquivoFXML));
        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 786, 560);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
    }

//Menu Bibliotecária

    public static void irTelaPrincipal(){
        trocarTela("inicial-view.fxml", "Biblioteca");
    }

    public static void irTelaGerenciaUsuarios(){
        trocarTela("gerencia-usuario-view.fxml", "Usuarios");
    }

    public static void irTelaGerenciaLivros(){
        trocarTela("gerencia-livro-view.fxml", "Livros");
    }

    public static void irTelaGerenciaAutores(){
        trocarTela("gerencia-autores-view.fxml", "Autores");
    }

    public static void irTelaGerenciaEmprestimos(){
        trocarTela("gerencia-emprestimos-view.fxml", "Empréstimos");
    }

//Menu Usuário
    public static void irTelaConsultaReservaLivros(){
        trocarTela("consulta-livro-view.fxml", "Livros Disponíveis");
    }


/*
    public static void irTelaCopias(Livro livro){
        CopiasController.livro=livro;
        trocarTela("copias-view.fxml", "Copias");
    }

 */

    public static void main(String[] args) {
        launch();
    }
}