package br.femass.edu.prova_prog3_n1_julio.gui;
import br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication;
import br.femass.edu.prova_prog3_n1_julio.Model.*;
import br.femass.edu.prova_prog3_n1_julio.dao.LivroDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

import static br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication.autorDao;
import static br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication.livroDao;

public class GerenciaLivroController implements Initializable {


    private Livro livroSelecionado;

    //List View
    @FXML
    private ListView<Livro> LstLivros;

    //Botões
    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnRetorna;

    @FXML
    private Button BtnAdicionaCopia;

    @FXML
    private Button BtnRemoveCopia;


    //Text Fields
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
    private TextField TxtNumCopias;

    @FXML
    private TextField TxtCopDisp;

    @FXML
    private Label LblCopDisp;

    //Métodos Auxiliares
    private void limparTela(){
        TxtTitulo.setText("");
        TxtNomeAutor.setText("");
        TxtSobrenomeAutor.setText("");
        TxtGenero.setText("");
        TxtAno.setText("");
        TxtEdicao.setText("");
        TxtCodigo.setText("");
        TxtNumCopias.setText("");
        TxtCopDisp.setText("");
        TxtCopDisp.setVisible(false);
        LblCopDisp.setVisible(false);
    }

    private void habilitarInterface(Boolean incluir){
        TxtTitulo.setDisable(!incluir);
        TxtTitulo.setEditable(incluir);
        TxtNomeAutor.setDisable(!incluir);
        TxtNomeAutor.setEditable(incluir);
        TxtSobrenomeAutor.setDisable(!incluir);
        TxtSobrenomeAutor.setEditable(incluir);
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
        LstLivros.setDisable(incluir);
        BtnAdicionaCopia.setDisable(true);
        BtnRemoveCopia.setDisable(true);
    }

    private void exibirLivro(){
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        TxtTitulo.setText(livro.getTitulo());
        TxtNomeAutor.setText(livro.getAutor().getNome());
        TxtSobrenomeAutor.setText(livro.getAutor().getSobrenome());
        TxtGenero.setText(livro.getGenero().getNome());
        TxtAno.setText(Integer.toString(livro.getAno()));
        TxtEdicao.setText(livro.getEdicao());
        TxtCodigo.setText(Integer.toString(livro.getCodigo()));
        TxtNumCopias.setText(Integer.toString(livro.getCopias().size()));
        TxtCopDisp.setVisible(true);
        TxtCopDisp.setText(livro.copiasDisponiveis().toString());
        LblCopDisp.setVisible(true);
        livroSelecionado=livro;
        BtnAdicionaCopia.setDisable(false);
        BtnRemoveCopia.setDisable(false);
    }

    //Actions
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
        TxtTitulo.requestFocus();
    };

    @FXML
    private void BtnExcluir_Action(ActionEvent evento){
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        if (livro==null) return;
        try {
            livroDao.excluir(livro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();
        limparTela();

    };

    @FXML
    private void BtnGravar_Action(ActionEvent evento){
        Livro livro = new Livro();
        Autor autor = new Autor(TxtNomeAutor.getText(), TxtSobrenomeAutor.getText());
        livro.setTitulo(TxtTitulo.getText());
        livro.setAutor(autor);
        livro.setGenero(new Genero(TxtGenero.getText()));
        livro.setAno(Integer.parseInt(TxtAno.getText()));
        livro.setEdicao(TxtEdicao.getText());
        for (int i=0; i<Integer.parseInt(TxtNumCopias.getText()); i++){
            livro.adicionarCopia(new Copia(livro));
        }
        try {
            autorDao.gravar(autor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            livroDao.gravar(livro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarLista();
        habilitarInterface(false);
        limparTela();
    };

    @FXML
    private void BtnCancelar_Action(ActionEvent evento){
        habilitarInterface(false);
        limparTela();
    };

    @FXML
    private void BtnRetorna_Action(ActionEvent evento){
        BibliotecaApplication.irTelaPrincipal();
    }

    @FXML
    private void BtnAdicionaCopia_Action(ActionEvent evento){
        livroSelecionado.adicionarCopia(new Copia(livroSelecionado));
        atualizarLista();
        exibirLivro();
    }

    @FXML
    private void BtnRemoveCopia_Action(ActionEvent evento){
        for (Copia copia: livroSelecionado.getCopias()){
            //remove apenas uma copia que não seja fixa e que não esteja emprestada.
            //não é possível remover caso só haja uma cópia. Neste caso deve-se excluir o livro.
            if (!copia.getFixo() && (!copia.getEmprestada())) {
                livroSelecionado.removerCopia(copia);
                break;
            }
        }
        atualizarLista();
        exibirLivro();
    }


    //Inicializador
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         atualizarLista();
    }

    private void atualizarLista() {
        Set<Livro> livros = new HashSet<>();
        try {
            livros.addAll(livroDao.listar());
        } catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<Livro> livrosOb = FXCollections.observableArrayList(livros);
        LstLivros.setItems(livrosOb);
        GeradorCodigo.codigoLivro=livros.size();
    }


}