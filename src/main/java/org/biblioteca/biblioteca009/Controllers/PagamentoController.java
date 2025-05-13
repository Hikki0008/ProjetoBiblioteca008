package org.biblioteca.biblioteca009.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.biblioteca.biblioteca009.Modelos.Emprestimos;
import org.biblioteca.biblioteca009.Services.PagamentoService;

import java.time.LocalDate;

public class PagamentoController {

    @FXML
    private TextField campoIdEmprestimo;

    @FXML
    private Label mensagemLabel;

    private final PagamentoService pagamentoService = new PagamentoService();

    @FXML
    private void onRegistrarPagamento() {
        try {
            int idEmprestimo = Integer.parseInt(campoIdEmprestimo.getText());

            // Mock do empréstimo (em app real, busque do banco)
            Emprestimos emprestimos = new Emprestimos();
            emprestimos.setIdEmprestimos(idEmprestimo);
            emprestimos.setDevolvido(true);
            emprestimos.setDataDevolucao(LocalDate.now());

            pagamentoService.registrarPagamento(emprestimos);
            mensagemLabel.setText("Pagamento registrado com sucesso!");

        } catch (NumberFormatException e) {
            mensagemLabel.setText("ID do empréstimo inválido.");
        } catch (Exception e) {
            mensagemLabel.setText("Erro ao registrar pagamento.");
            e.printStackTrace();
        }
    }

    @FXML
    private void onVoltarInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Biblioteca008/Resources/views/inicio.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Tela de Início");
            stage.setScene(new Scene(root));
            stage.show();

            // Fecha janela atual
            ((Stage) campoIdEmprestimo.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
