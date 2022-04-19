package br.femass.edu.prova_prog3_n1_julio.gui;

import br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication;
import br.femass.edu.prova_prog3_n1_julio.Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import static br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication.usuarioDao;

public class IdentificaUsuarioController implements Initializable {
    public static Usuario usuarioSelecionado;

    //Text Fields
    @FXML
    private TextField TxtNome;

    //Botões
    @FXML
    private Button BtnRetorna;

    @FXML
    private Button BtnConsulta;

    //Actions
    @FXML
    private void BtnRetorna_Action(ActionEvent evento){
        BibliotecaApplication.irTelaPrincipal();
    }

    @FXML
    private void BtnConsulta_Action(ActionEvent evento){
        Set<Usuario> usuarios = new HashSet<>();
        String nome = TxtNome.getText();

        try {
            usuarios.addAll(usuarioDao.listar());
            for (Usuario usuario : usuarios){
                if (usuario.getNome().equals(nome)) {
                    usuarioSelecionado=usuario;
                    BibliotecaApplication.irTelaConsultaReservaLivros();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TxtNome.requestFocus();
    }
}
