package br.femass.edu.prova_prog3_n1_julio.gui;

import br.femass.edu.prova_prog3_n1_julio.Model.Copia;
import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication;

public class CopiasController implements Initializable {
    public static Livro livro;
    public static Copia copiaSelecionada;
    //private CopiasDao copiasDao = new CopiasDao();

    @FXML
    private TextField TxtCodigo;

    @FXML
    private TextField TxtTitulo;

    @FXML
    private TextField TxtNomeAutor;

    @FXML
    private TextField TxtSobrenomeAutor;

    @FXML
    private TextField TxtGenero;

    @FXML
    private TextField TxtAno;

    @FXML
    private TextField TxtEdicao;

    @FXML
    private TextField TxtCopiasDisp;

    @FXML
    private ListView<Copia> LstCopias;

    @FXML
    private Button BtnAdicionarCopia;

    @FXML
    private Button BtnRemoverCopia;

    @FXML
    private Button BtnRetorna;


    private void exibirLivro(){
        TxtTitulo.setText(livro.getTitulo());
        TxtNomeAutor.setText(livro.getAutor().getNome());
        TxtSobrenomeAutor.setText(livro.getAutor().getSobrenome());
        TxtGenero.setText(livro.getGenero().getNome());
        TxtAno.setText(Integer.toString(livro.getAno()));
        TxtEdicao.setText(livro.getEdicao());
        TxtCodigo.setText(Integer.toString(livro.getCodigo()));
        TxtCopiasDisp.setText(livro.copiasDisponiveis().toString());
    }

    private void selecionarCopia(){
        copiaSelecionada=LstCopias.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void LstLivros_MouseClicked(MouseEvent evento){
        selecionarCopia();
    }

    @FXML
    private void LstLivros_KeyPressed(KeyEvent evento){
        selecionarCopia();
    }

    @FXML
    private void BtnAdicionarCopia_Action(){
        livro.adicionarCopia(new Copia(livro));
        atualizarLista();
    }

    @FXML
    private void BtnRemoverCopia_Action(){
        livro.removerCopia(copiaSelecionada);
        atualizarLista();
    }

    @FXML
    private void BtnRetorna_Action(ActionEvent evento){
        BibliotecaApplication.irTelaGerenciaLivros();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       exibirLivro();
       atualizarLista();
    }

    private void atualizarLista() {
        ObservableList<Copia> copiasOb = FXCollections.observableArrayList(livro.getCopias());
        LstCopias.setItems(copiasOb);
    }
}
