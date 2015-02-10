package ch.makery.address.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import ch.makery.address.Main;
import ch.makery.address.model.Cliente;
import ch.makery.address.model.Fornecedores;
import ch.makery.address.model.Grupos;
import ch.makery.address.util.ConectaBanco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastroFornecedorController implements Initializable {
	ConectaBanco conecta = new ConectaBanco();
	Main main = new Main();
	
	private ObservableList<Grupos> gruposSugestoes = FXCollections.observableArrayList();
	private ObservableList<Grupos> gruposAceitos = FXCollections.observableArrayList();
	
	Fornecedores fornecedor = new Fornecedores();
	
	@FXML
	private TextField txtOutros;

	@FXML
	private Button buttomVoltarGrupo;

	@FXML
	private TableView<Grupos> sugestaoFornecedor;

	@FXML
	private TableView<Grupos> tabfornecedor;

	@FXML
	private Button buttomLimpar;

	@FXML
	private TextField txtNome;

	@FXML
	private Button buttomVoltar;

	@FXML
	private TableColumn<Grupos, String> nome;

	@FXML
	private Button buttomConfirmar;

	@FXML
	private TextField txtCpfCnpj;

	@FXML
	private Button buttomIrGrupo;

	@FXML
	private TableColumn<Grupos, String> cod;

	@FXML
	private TableColumn<Grupos, String> codSugerido;

	@FXML
	private TableColumn<Grupos, String> nomeSugerido;
	
    
    
    public void initialize(URL location, ResourceBundle resources) {
    	conecta.conexao();
    	conecta.executaSQL("select * from grupos where tipo_grupo = 3");
    	try {
			while (conecta.rs.next()) {
				gruposSugestoes.add(new Grupos(String.valueOf(conecta.rs.getInt("id_grupo")),conecta.rs.getString("nome_grupo")));
				codSugerido.setCellValueFactory(new PropertyValueFactory<Grupos, String>("id"));
				nomeSugerido.setCellValueFactory(new PropertyValueFactory<Grupos, String>("nome"));
				sugestaoFornecedor.setItems(gruposSugestoes);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao inicializar Grupos de fornecedores");
		}
    }
    
    
    
    @FXML
    void irGrupo(ActionEvent event) {
    	if (sugestaoFornecedor.getSelectionModel().getSelectedItem() != null ){
    	    Grupos grup = sugestaoFornecedor.getSelectionModel().getSelectedItem();
    	    fornecedor.getArrayGrupo().add(grup);
    	    gruposAceitos.add(new Grupos(grup.getId(),grup.getNome()));
	    	cod.setCellValueFactory(new PropertyValueFactory<Grupos, String>("id"));
			nome.setCellValueFactory(new PropertyValueFactory<Grupos, String>("nome"));
			tabfornecedor.setItems(gruposAceitos);
	    }
    }

    @FXML
    void voltarGrupo(ActionEvent event) {

    }

    @FXML
    void limpar(ActionEvent event) {
    	txtNome.setText("");
    	txtCpfCnpj.setText("");
    	txtOutros.setText("");
    	gruposAceitos.clear();
    }

    @FXML
    void confirmar(ActionEvent event) {
    	conecta.conexao();
    	try {
			PreparedStatement pst = conecta.conn.prepareStatement("insert into fornecedores (nome_fornecedor, cnpj_fornecedor, outros_fornecedor,id_grupo) values(?,?,?,?)");
	    	Fornecedores fornecedor = new Fornecedores(txtNome.getText(),txtCpfCnpj.getText(),txtOutros.getText(),null);
			pst.setString(1, fornecedor.getNome());
	    	pst.setString(2,fornecedor.getCnpj() );
	    	pst.setString(3,fornecedor.getOutros() );
	    	pst.setString(4, fornecedor.getGrupo());
	    	//pst.executeUpdate(5, fornec);
	    	JOptionPane.showMessageDialog(null,"Cadastro Realizado com Sucesso");
    	} catch (SQLException e) {
    		JOptionPane.showMessageDialog(null,"Erro ao cadastrar"+e);
		}
    	txtNome.setText("");
    	txtCpfCnpj.setText("");
    	txtOutros.setText("");
    }

    @FXML
    void voltar(ActionEvent event) {
    	main.iniciaTelas("view/Cadastro.fxml");
    }

}
