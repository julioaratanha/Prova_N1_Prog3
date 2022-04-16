package br.femass.edu.prova_prog3_n1_julio;

import br.femass.edu.prova_prog3_n1_julio.dao.*;
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
    public static EmprestimosAtivosDao emprestimosAtivosDao;
    public static ReservasDao reservasDao;
    public static HistoricoDeEmprestimosDao historicoDeEmprestimosDao;


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        this.livroDao= new LivroDao();
        this.usuarioDao = new UsuarioDao();
        this.autorDao = new AutorDao();
        this.emprestimosAtivosDao = new EmprestimosAtivosDao();
        this.reservasDao = new ReservasDao();
        this.historicoDeEmprestimosDao = new HistoricoDeEmprestimosDao();
        irTelaPrincipal();
    }

    private static void trocarTela(String arquivoFXML, String titulo, Integer largura, Integer altura ){
        FXMLLoader loader = new FXMLLoader(BibliotecaApplication.class.getResource(arquivoFXML));
        Scene scene = null;
        try {
            scene = new Scene(loader.load(), largura, altura);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
    }

//Menu Bibliotecária

    public static void irTelaPrincipal(){
        trocarTela("inicial-view.fxml", "Biblioteca", 786, 560);
    }

    public static void irTelaGerenciaUsuarios(){
        trocarTela("gerencia-usuario-view.fxml", "Usuarios", 786, 560);
    }

    public static void irTelaGerenciaLivros(){
        trocarTela("gerencia-livro-view.fxml", "Livros", 786, 560);
    }

    public static void irTelaGerenciaAutores(){
        trocarTela("gerencia-autores-view.fxml", "Autores", 786, 560);
    }

    public static void irTelaGerenciaEmprestimos(){
        trocarTela("gerencia-emprestimos-view.fxml", "Empréstimos", 786, 560);
    }

//Menu Usuário

    public static void irTelaIdentificaUsuario(){
        trocarTela("identifica-usuario-view.fxml", "Identificação", 350, 150);
    }

    public static void irTelaConsultaReservaLivros(){
        trocarTela("consulta-livro-view.fxml", "Livros Disponíveis", 786, 560);
    }



    //Main
    public static void main(String[] args) {
        launch();
    }
}