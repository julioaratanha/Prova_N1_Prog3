package br.femass.edu.prova_prog3_n1_julio.gui;

import br.femass.edu.prova_prog3_n1_julio.Model.Copia;
import br.femass.edu.prova_prog3_n1_julio.Model.Emprestimo;
import br.femass.edu.prova_prog3_n1_julio.Model.GeradorCodigo;
import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication;

import static br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication.*;
import static br.femass.edu.prova_prog3_n1_julio.gui.IdentificaUsuarioController.usuarioSelecionado;

public class ConsultaLivroController implements Initializable {

    private Livro livroSelecionado;

    //List View
    @FXML
    private ListView<Livro> LstLivros;

    @FXML
    private ListView<Emprestimo> LstEmprestimos;

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
    private TextField TxtCopiasDisp;

    @FXML
    private TextField TxtEmpAtivos;

    @FXML
    private TextField TxtReservas;

    //Botões
    @FXML
    private Button BtnRetorna;

    @FXML
    private Button BtnReserva;

    @FXML
    private Button BtnCancelaReserva;


    //Métodos Auxiliares
    private void exibirLivro(Livro livro){
        if (livro==null) return;
        TxtTitulo.setText(livro.getTitulo());
        TxtNomeAutor.setText(livro.getAutor().getNome());
        TxtSobrenomeAutor.setText(livro.getAutor().getSobrenome());
        TxtGenero.setText(livro.getGenero().getNome());
        TxtAno.setText(Integer.toString(livro.getAno()));
        TxtEdicao.setText(livro.getEdicao());
        TxtCodigo.setText(Integer.toString(livro.getCodigo()));
        TxtCopiasDisp.setText(livro.copiasDisponiveis().toString());
        livroSelecionado=livro;
    }

    private void atualizarLista() {
        Integer ativos=usuarioSelecionado.numeroDeEmprestimosAtivos();
        Integer reservas=usuarioSelecionado.numeroDeReservas();
        TxtEmpAtivos.setText(ativos.toString());
        TxtReservas.setText(reservas.toString());
        Set<Livro> livros = new HashSet<>();
        try {
            livros.addAll(livroDao.listar());
        } catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<Livro> livrosOb = FXCollections.observableArrayList(livros);
        LstLivros.setItems(livrosOb);
        ObservableList<Emprestimo> emprestimosOb = FXCollections.observableArrayList(usuarioSelecionado.getEmprestimos());
        LstEmprestimos.setItems(emprestimosOb);
    }

    //Actions
    @FXML
    private void LstLivros_MouseClicked(MouseEvent evento){
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        exibirLivro(livro);
    }

    @FXML
    private void LstLivros_KeyPressed(KeyEvent evento){
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        exibirLivro(livro);
    }

    @FXML
    private void LstEmprestimos_MouseClicked(MouseEvent evento){
        Emprestimo emprestimo = LstEmprestimos.getSelectionModel().getSelectedItem();
        if (emprestimo==null) return;
        exibirLivro(emprestimo.getLivro());
    }

    @FXML
    private void LstEmprestimos_KeyPressed(KeyEvent evento){
        Emprestimo emprestimo = LstEmprestimos.getSelectionModel().getSelectedItem();
        if (emprestimo==null) return;
        exibirLivro(emprestimo.getLivro());
    }


    @FXML
    private void BtnRetorna_Action(ActionEvent evento){
        BibliotecaApplication.irTelaPrincipal();
    }

    @FXML
    private void BtnReserva_Action(ActionEvent evento){
        Emprestimo emprestimo = new Emprestimo(usuarioSelecionado, livroSelecionado);
        Alert alert = new Alert(AlertType.WARNING);
        if (livroSelecionado==null) alert.setContentText("Por favor, selecione um livro!");
        else {
            alert.setContentText(usuarioSelecionado.incluirEmprestimo(emprestimo));
            if (usuarioSelecionado.getEmprestimos().size()<5 && livroSelecionado.copiasDisponiveis()>0) {
                try {
                    usuarioDao.gravar(usuarioSelecionado);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    reservasDao.gravar(emprestimo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        exibirLivro(livroSelecionado);
        atualizarLista();
        alert.show();
    }

    @FXML
    private void BtnCancelaReserva_Action(ActionEvent evento){
        Alert alert = new Alert(AlertType.WARNING);
        Emprestimo emprestimo = LstEmprestimos.getSelectionModel().getSelectedItem();
        if (emprestimo==null) {
            alert.setContentText("Por favor, selecione uma reserva!");
            alert.show();
            return;
        }
        if (!emprestimo.getAtivo()) {
            alert.setContentText(usuarioSelecionado.excluirEmprestimo(emprestimo));
            try {
                usuarioDao.gravar(usuarioSelecionado);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                reservasDao.excluir(emprestimo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else alert.setContentText("Empréstimo ativo! Não é possível excluir!");
        alert.show();
        exibirLivro(emprestimo.getLivro());
        atualizarLista();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       atualizarLista();
    }

}
