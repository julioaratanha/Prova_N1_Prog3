package br.femass.edu.prova_prog3_n1_julio.gui;

import br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication;
import br.femass.edu.prova_prog3_n1_julio.Model.*;
import br.femass.edu.prova_prog3_n1_julio.dao.UsuarioDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

import static br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication.usuarioDao;

public class GerenciaUsuarioController implements Initializable {

    private Boolean aluno;

    //List Views
    @FXML
    private ListView<Usuario> LstUsuarios;


    //Botões
    @FXML
    private Button BtnIncluirAluno;

    @FXML
    private Button BtnIncluirProfessor;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnRetorna;


    //Text Fields
    @FXML
    private TextField TxtCodigo;

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtLogradouro;

    @FXML
    private TextField TxtNumero;

    @FXML
    private TextField TxtComplemento;

    @FXML
    private TextField TxtBairro;

    @FXML
    private TextField TxtCidade;

    @FXML
    private TextField TxtUf;

    @FXML
    private TextField TxtTelDdd;

    @FXML
    private TextField TxtTelNum;


    //Combo Box
    @FXML
    private ComboBox<Curso> CboCurso;

    //Métodos Auxiliares
    private void limparTela(){
        TxtNome.setText("");
        TxtLogradouro.setText("");
        TxtNumero.setText("");
        TxtComplemento.setText("");
        TxtBairro.setText("");
        TxtCidade.setText("");
        TxtUf.setText("");
        TxtTelDdd.setText("");
        TxtTelNum.setText("");
        TxtCodigo.setText("");
        CboCurso.setValue(null);
    }

    private void habilitarInterface(Boolean incluir){
        TxtNome.setDisable(!incluir);
        TxtNome.setEditable(incluir);

        TxtLogradouro.setDisable(!incluir);
        TxtLogradouro.setEditable(incluir);

        TxtNumero.setDisable(!incluir);
        TxtNumero.setEditable(incluir);

        TxtComplemento.setDisable(!incluir);
        TxtComplemento.setEditable(incluir);

        TxtBairro.setDisable(!incluir);
        TxtBairro.setEditable(incluir);

        TxtCidade.setDisable(!incluir);
        TxtCidade.setEditable(incluir);

        TxtUf.setDisable(!incluir);
        TxtUf.setEditable(incluir);

        TxtTelDdd.setDisable(!incluir);
        TxtTelDdd.setEditable(incluir);

        TxtTelNum.setDisable(!incluir);
        TxtTelNum.setEditable(incluir);

        CboCurso.setEditable(incluir);
        CboCurso.setDisable(!incluir);

        BtnGravar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnIncluirAluno.setDisable(incluir);
        BtnIncluirProfessor.setDisable(incluir);
        BtnExcluir.setDisable(incluir);
        LstUsuarios.setDisable(incluir);
    }

    private void exibirUsuario(){
        Usuario usuario = LstUsuarios.getSelectionModel().getSelectedItem();
        TxtNome.setText(usuario.getNome());
        TxtLogradouro.setText(usuario.getEndereco().getLogradouro());
        TxtNumero.setText(Integer.toString(usuario.getEndereco().getNumero()));
        TxtComplemento.setText(usuario.getEndereco().getComplemento());
        TxtBairro.setText(usuario.getEndereco().getBairro());
        TxtCidade.setText(usuario.getEndereco().getCidade());
        TxtUf.setText(usuario.getEndereco().getUf());
        TxtTelDdd.setText(Integer.toString(usuario.getTelefone().getDdd()));
        TxtTelNum.setText(Long.toString(usuario.getTelefone().getNumero()));
        TxtCodigo.setText(usuario.getCodigo().toString());
        try{
            Aluno aluno = (Aluno) usuario;
            CboCurso.setValue(aluno.getCurso());
            CboCurso.setVisible(true);
        }catch (Exception e){
            CboCurso.setVisible(false);
        }
    }


    //Actions
    @FXML
    private void LstLivros_MouseClicked(MouseEvent evento){
        exibirUsuario();
    }

    @FXML
    private void LstLivros_KeyPressed(KeyEvent evento){
        exibirUsuario();
    }

    @FXML
    private void BtnIncluirAluno_Action(ActionEvent evento){
        habilitarInterface(true);
        CboCurso.setVisible(true);
        limparTela();
        TxtNome.requestFocus();
        aluno=true;
    };

    @FXML
    private void BtnIncluirProfessor_Action(ActionEvent evento){
        habilitarInterface(true);
        limparTela();
        TxtNome.requestFocus();
    };

    @FXML
    private void BtnExcluir_Action(ActionEvent evento){
        Usuario usuario = LstUsuarios.getSelectionModel().getSelectedItem();
        if (usuario==null) return;
        try {
            usuarioDao.excluir(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarLista();
        limparTela();

    };

    @FXML
    private void BtnGravar_Action(ActionEvent evento){
        if (aluno) {
            Aluno aluno = new Aluno(Curso.valueOf(String.valueOf(CboCurso.getValue())));
            cadastroUsuario(aluno);
        }
        else {
            Professor professor = new Professor();
            cadastroUsuario(professor);
        }
        atualizarLista();
        habilitarInterface(false);
        limparTela();
        aluno=false;
        CboCurso.setVisible(false);
    };

    @FXML
    private void BtnCancelar_Action(ActionEvent evento){
        habilitarInterface(false);
        limparTela();
        aluno=false;
        CboCurso.setVisible(false);
    };

    @FXML
    private void BtnRetorna_Action(ActionEvent evento){
        BibliotecaApplication.irTelaPrincipal();
    }


    //Métodos Auxiliares
    private void cadastroUsuario(Usuario usuario){
        usuario.setNome(TxtNome.getText());
        usuario.setEndereco(new Endereco(TxtLogradouro.getText(), Integer.parseInt(TxtNumero.getText()), TxtComplemento.getText(), TxtBairro.getText(), TxtCidade.getText(),TxtUf.getText()));
        usuario.setTelefone(new Telefone(Integer.parseInt(TxtTelDdd.getText()), Long.parseLong(TxtTelNum.getText())));
        //if (aluno) ((Aluno) usuario).setCurso(CboCurso.getValue());
        try {
            usuarioDao.gravar(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void atualizarLista() {
        Set<Usuario> usuarios = null;
        try {
            usuarios = usuarioDao.listar();
        } catch (Exception e){
            usuarios = new HashSet<>();
        }
        ObservableList<Usuario> usuariosOb = FXCollections.observableArrayList(usuarios);
        LstUsuarios.setItems(usuariosOb);
        GeradorCodigo.codigoUsuario=usuarios.size();
    }





    //Inicializador
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Curso> cursos = FXCollections.observableArrayList(Curso.values());
        CboCurso.setItems(cursos);

        atualizarLista();

    }
}
