package br.femass.edu.prova_prog3_n1_julio.gui;

import br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication;
import br.femass.edu.prova_prog3_n1_julio.Model.Emprestimo;
import br.femass.edu.prova_prog3_n1_julio.Model.Livro;
import br.femass.edu.prova_prog3_n1_julio.Model.Usuario;
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

import static br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication.*;
import static br.femass.edu.prova_prog3_n1_julio.gui.IdentificaUsuarioController.usuarioSelecionado;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class GerenciaEmprestimosController implements Initializable {

    //List View
    @FXML
    private ListView<Emprestimo> LstReservas;

    @FXML
    private ListView<Emprestimo> LstEmprestimosAtivos;

    @FXML
    private ListView<Emprestimo> LstHistorico;

    //Text Field
    @FXML
    private TextField TxtCopiasDisponiveis;

    @FXML
    private TextField TxtEmpAtivos;

    @FXML
    private TextField TxtDataEmpAtivo;

    @FXML
    private TextField TxtDataPrevDevolAtivo;

    @FXML
    private TextField TxtDataEmpAntigo;

    @FXML
    private TextField TxtDataPrevDevolAntigo;

    @FXML
    private TextField TxtDataEfetDevolAntigo;

    @FXML
    private TextField TxtNomeUsuario;

    //Botões
    @FXML
    private Button BtnRetorna;

    @FXML
    private Button BtnEfetEmpres;

    @FXML
    private Button BtnEfetDevol;


    //Métodos Auxiliares
    private void atualizarLista() {
        Set<Emprestimo> reservas = new HashSet<>();
        try {
            reservas.addAll(reservasDao.listar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<Emprestimo> reservasOb = FXCollections.observableArrayList(reservas);
        LstReservas.setItems(reservasOb);

        Set<Emprestimo> emprestimosAtivos = new HashSet<>();
        try {
            emprestimosAtivos.addAll(emprestimosAtivosDao.listar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<Emprestimo> emprestimosAtivosOb = FXCollections.observableArrayList(emprestimosAtivos);
        LstEmprestimosAtivos.setItems(emprestimosAtivosOb);

        Set<Emprestimo> historicoDeEmprestimos = new HashSet<>();
        try {
            historicoDeEmprestimos.addAll(historicoDeEmprestimosDao.listar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<Emprestimo> historicoDeEmprestimosOb = FXCollections.observableArrayList(historicoDeEmprestimos);
        LstHistorico.setItems(historicoDeEmprestimosOb);
    }

    private void exibirReserva(){
        Emprestimo reserva = LstReservas.getSelectionModel().getSelectedItem();
        if (reserva==null) return;
        TxtCopiasDisponiveis.setText(reserva.getLivro().copiasDisponiveis().toString());
        TxtEmpAtivos.setText(reserva.getUsuario().numeroDeEmprestimosAtivos().toString());
        TxtNomeUsuario.setText(reserva.getUsuario().getNome());
    }

    private void exibirEmprestimosAtivos(){
        Emprestimo emprestimoAtivo=LstEmprestimosAtivos.getSelectionModel().getSelectedItem();
        if (emprestimoAtivo==null) return;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        TxtCopiasDisponiveis.setText(emprestimoAtivo.getLivro().copiasDisponiveis().toString());
        TxtEmpAtivos.setText(emprestimoAtivo.getUsuario().numeroDeEmprestimosAtivos().toString());
        TxtDataEmpAtivo.setText(emprestimoAtivo.getDataEmprestimo().format(formatter));
        TxtDataPrevDevolAtivo.setText(emprestimoAtivo.getDataDevolucaoPrevista().format(formatter));
        TxtNomeUsuario.setText(emprestimoAtivo.getUsuario().getNome());
    }

    private void exibirHistorico(){
        Emprestimo historico = LstHistorico.getSelectionModel().getSelectedItem();
        if (historico==null) return;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        TxtCopiasDisponiveis.setText(historico.getLivro().copiasDisponiveis().toString());
        TxtEmpAtivos.setText(historico.getUsuario().numeroDeEmprestimosAtivos().toString());
        TxtDataEmpAntigo.setText(historico.getDataEmprestimo().format(formatter));
        TxtDataPrevDevolAntigo.setText(historico.getDataDevolucaoPrevista().format(formatter));
        TxtDataEfetDevolAntigo.setText(historico.getDataDevolucaoEfetiva().format(formatter));
        TxtNomeUsuario.setText(historico.getUsuario().getNome());
    }

    //Actions
    @FXML
    private void LstReservas_KeyPressed(KeyEvent evento){
        exibirReserva();
    }

    @FXML
    private void LstReservas_MouseClicked(MouseEvent evento){
        exibirReserva();
    }

    @FXML
    private void LstEmprestimosAtivos_KeyPressed(KeyEvent evento){
        exibirEmprestimosAtivos();
    }

    @FXML
    private void LstEmprestimosAtivos_MouseClicked(MouseEvent evento){
        exibirEmprestimosAtivos();
    }

    @FXML
    private void LstHistorico_KeyPressed(KeyEvent evento){
        exibirHistorico();
    }

    @FXML
    private void LstHistorico_MouseClicked(MouseEvent evento){
        exibirHistorico();
    }

    @FXML
    private void BtnEfetEmpres_Action(ActionEvent evento){
        Emprestimo emprestimo = LstReservas.getSelectionModel().getSelectedItem();
        if (emprestimo==null) return;
        try {
            reservasDao.excluir(emprestimo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        emprestimo.setDataEmprestimoEDataDevolucaoPrevista(LocalDateTime.now().toLocalDate());
        Usuario usuario = emprestimo.getUsuario();
        try {
            usuarioDao.gravar(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            emprestimosAtivosDao.gravar(emprestimo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarLista();
    }

    @FXML
    private void BtnEfetDevol_Action(ActionEvent evento){
        Emprestimo emprestimo = LstEmprestimosAtivos.getSelectionModel().getSelectedItem();
        if (emprestimo==null) return;
        try {
            emprestimosAtivosDao.excluir(emprestimo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Usuario usuario = emprestimo.getUsuario();
        usuario.excluirEmprestimo(emprestimo);
        try {
            usuarioDao.gravar(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        emprestimo.setAtivo(false);
        emprestimo.setDataDevolucaoEfetiva(LocalDateTime.now().toLocalDate());
        try {
            historicoDeEmprestimosDao.gravar(emprestimo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarLista();
    }

    @FXML
    private void BtnRetorna_Action(ActionEvent evento){
        BibliotecaApplication.irTelaPrincipal();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }
}
