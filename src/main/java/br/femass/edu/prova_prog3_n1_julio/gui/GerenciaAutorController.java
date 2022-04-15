package br.femass.edu.prova_prog3_n1_julio.gui;

import br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication;
import br.femass.edu.prova_prog3_n1_julio.Model.*;
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
import java.util.*;

import static br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication.autorDao;
import static br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication.livroDao;

public class GerenciaAutorController implements Initializable {

    private Autor autorSelecionado;

    //List View
    @FXML
    ListView<Autor> LstAutores;

    @FXML
    private ListView<Livro> LstLivros;

    //Text Fields
    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtSobrenome;

    @FXML
    private TextField TxtCodigo;

    @FXML
    private TextField TxtTitulo;

    @FXML
    private TextField TxtGenero;

    @FXML
    private TextField TxtAno;

    @FXML
    private TextField TxtEdicao;

    @FXML
    private TextField TxtNumCopias;

    //Botões
    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnRetorna;



    //Actions

    @FXML
    private void LstAutores_MouseClicked(MouseEvent evento){
        exibirAutor();
    }

    @FXML
    private void LstAutores_KeyPressed(KeyEvent evento){
        exibirAutor();
    }

    @FXML
    private void LstLivros_MouseClicked(MouseEvent evento){
        exibirLivro();
    }

    @FXML
    private void LstLivros_KeyPressed(KeyEvent evento){
        exibirLivro();
    }

    @FXML
    private void BtnIncluir_Action(ActionEvent evento){
        habilitarInterface(true);
        limparTela();
        TxtNome.requestFocus();
    }

    @FXML
    private void BtnExcluir_Action(ActionEvent evento){
        Autor autor = LstAutores.getSelectionModel().getSelectedItem();
        if (autor==null) return;
        try {
            for (Livro livro : autor.getLivros()){
                livroDao.excluir(livro);
            }
            autorDao.excluir(autor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarLista();
        limparTela();
    }

    @FXML
    private void BtnCancelar_Action(ActionEvent evento){
        habilitarInterface(false);
        limparTela();
    }

    @FXML
    private void BtnGravar_Action(ActionEvent evento){
        Autor autor = new Autor(TxtNome.getText(), TxtSobrenome.getText());

        try {
            autorDao.gravar(autor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarLista();
        habilitarInterface(false);
        limparTela();
    }

    @FXML
    private void BtnRetorna_Action(ActionEvent evento){
        BibliotecaApplication.irTelaPrincipal();
    }

    //Métodos Auxiliares
    private void limparTela(){
        TxtNome.setText("");
        TxtSobrenome.setText("");
        TxtCodigo.setText("");
        TxtTitulo.setText("");
        TxtGenero.setText("");
        TxtAno.setText("");
        TxtEdicao.setText("");
        TxtNumCopias.setText("");
    }

    private void habilitarInterface(Boolean incluir){
        LstAutores.setDisable(incluir);
        LstLivros.setDisable(incluir);
        TxtNome.setDisable(!incluir);
        TxtNome.setEditable(incluir);
        TxtSobrenome.setDisable(!incluir);
        TxtSobrenome.setEditable(incluir);
        TxtTitulo.setDisable(!incluir);
        TxtTitulo.setEditable(incluir);
        TxtGenero.setDisable(!incluir);
        TxtGenero.setEditable(incluir);
        TxtAno.setDisable(!incluir);
        TxtAno.setEditable(incluir);
        TxtEdicao.setDisable(!incluir);
        TxtEdicao.setEditable(incluir);
        TxtNumCopias.setDisable(!incluir);
        TxtNumCopias.setEditable(incluir);
        BtnGravar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnIncluir.setDisable(incluir);
        BtnExcluir.setDisable(incluir);
    }

    private void exibirAutor(){
        Autor autor = LstAutores.getSelectionModel().getSelectedItem();
        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getSobrenome());
    }

    private void exibirLivro(){
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        TxtCodigo.setText(Integer.toString(livro.getCodigo()));
        TxtTitulo.setText(livro.getTitulo());
        TxtGenero.setText(livro.getGenero().getNome());
        TxtAno.setText(Integer.toString(livro.getAno()));
        TxtEdicao.setText(livro.getEdicao());
        TxtNumCopias.setText(Integer.toString(livro.getCopias().size()));
    }

    //Inicializador
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }

    private void atualizarLista() {
        Set<Autor> autores = null;
        try {
            autores = autorDao.listar();
        } catch (Exception e){
            autores = new HashSet<>();
        }
        ObservableList<Autor> autoresOb = FXCollections.observableArrayList(autores);
        LstAutores.setItems(autoresOb);
    }

}
