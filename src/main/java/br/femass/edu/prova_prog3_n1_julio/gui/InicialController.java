package br.femass.edu.prova_prog3_n1_julio.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import br.femass.edu.prova_prog3_n1_julio.BibliotecaApplication;

public class InicialController {


    //Botões
    @FXML
    private Button BtnUsuarios;

    @FXML
    private Button BtnLivros;

    @FXML
    private Button BtnAutores;

    @FXML
    private Button BtnEmprestimos;

    @FXML
    private Button BtnConsultaReserva;
    //Actions
    @FXML
    private void BtnUsuarios_Action(ActionEvent evento){
        BibliotecaApplication.irTelaGerenciaUsuarios();
    }

    @FXML
    private void BtnLivros_Action(ActionEvent evento){
        BibliotecaApplication.irTelaGerenciaLivros();
    }

    @FXML
    private void BtnAutores_Action(ActionEvent evento){
        BibliotecaApplication.irTelaGerenciaAutores();
    }

    @FXML
    private void BtnEmprestimos_Action(ActionEvent evento){
        BibliotecaApplication.irTelaGerenciaEmprestimos();
    }

    @FXML
    private void BtnConsultaReserva_Action(ActionEvent evento){
        BibliotecaApplication.irTelaConsultaReservaLivros();
    }
}
