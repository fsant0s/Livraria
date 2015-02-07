package ch.makery.address.controller;

import ch.makery.address.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ConsultaClienteController {
	
	Main main = new Main();

    @FXML
    private TableColumn<?, ?> fone;

    @FXML
    private TableColumn<?, ?> rg;

    @FXML
    private Button buttomExcluir;

    @FXML
    private Button buttomEditar;

    @FXML
    private TableColumn<?, ?> cpf;

    @FXML
    private TableColumn<?, ?> grupo;

    @FXML
    private Button buttomPesquisar;

    @FXML
    private TableColumn<?, ?> cod;

    @FXML
    private TextField txtNome;

    @FXML
    private Button butomVoltar;

    @FXML
    private TableColumn<?, ?> nome;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    void pesquisar(ActionEvent event) {

    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void excluir(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
    	main.iniciaTelas("view/Consulta.fxml");
    }

}