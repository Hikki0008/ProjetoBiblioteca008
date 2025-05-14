package org.biblioteca.biblioteca009.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private Button buttonLivros;
    @FXML
    private Button buttonUsuarios;
    @FXML
    private Button buttonEmprestimos;
    @FXML
    private Button buttonPagamento;
    @FXML
    private Button buttonCliente;

    @FXML
    public void initialize() {
        // Adicionando ações aos botões
        buttonLivros.setOnAction(event -> abrirTela());
        buttonUsuarios.setOnAction(event -> abrirTela());
        buttonEmprestimos.setOnAction(event -> abrirTelaEmprestimos());
        buttonPagamento.setOnAction(event -> abrirTelaPagamento());
        buttonCliente.setOnAction(event -> abrirTelaCliente());
    }

    private void abrirTela() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/biblioteca/biblioteca009/Livros.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) buttonLivros.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela: ");
        }
    }
    private void abrirTelaEmprestimos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/biblioteca/biblioteca009/Emprestimos.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) buttonEmprestimos.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela: ");
        }
    }
    private void abrirTelaPagamento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/biblioteca/biblioteca009/Pagamento.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) buttonPagamento.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela: ");
        }
    }
    private void abrirTelaCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/biblioteca/biblioteca009/cliente.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) buttonCliente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela: ");
        }
    }
}
