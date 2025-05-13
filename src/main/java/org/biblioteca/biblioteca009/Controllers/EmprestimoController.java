package org.biblioteca.biblioteca009.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.biblioteca.biblioteca009.Modelos.Emprestimos;
import org.biblioteca.biblioteca009.Repositorios.EmprestimoRepository;
import org.biblioteca.biblioteca009.Services.EmprestimoService;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoController {

    @FXML private TextField campoIdLivro;
    @FXML private TextField campoIdCliente;
    @FXML private DatePicker campoDataEmprestimo;
    @FXML private DatePicker campoDataDevolucao;
    @FXML private Button btnRegistrarEmprestimo;
    @FXML private Button btnRegistrarDevolucao;
    @FXML private ListView<String> listaEmprestimos;

    private final EmprestimoService emprestimoService = new EmprestimoService();
    private final EmprestimoRepository.LivrosRepository livrosRepository = new EmprestimoRepository.LivrosRepository();

    @FXML
    public void initialize() {
        listarEmprestimos();

        btnRegistrarEmprestimo.setOnAction(event -> registrarEmprestimo());
        btnRegistrarDevolucao.setOnAction(event -> registrarDevolucao());
    }

    private void registrarEmprestimo() {
        int idLivro = Integer.parseInt(campoIdLivro.getText());
        int idCliente = Integer.parseInt(campoIdCliente.getText());
        LocalDate dataEmprestimo = campoDataEmprestimo.getValue();
        LocalDate dataDevolucao = campoDataDevolucao.getValue();

        Emprestimos emprestimos = new Emprestimos();
        boolean sucesso = emprestimoService.registrarEmprestimo(emprestimos);

        if (sucesso) {
            listarEmprestimos();
        } else {
            exibirAlerta("Erro ao registrar empréstimo", "Livro indisponível para empréstimo.");
        }
    }

    private void registrarDevolucao() {
        try {
            int idEmprestimo = Integer.parseInt(campoIdLivro.getText());  // Supondo que você use o id do livro como ID do empréstimo
            LocalDate dataDevolucao = campoDataDevolucao.getValue();
            emprestimoService.registrarDevolucao(idEmprestimo, dataDevolucao);
            listarEmprestimos();
        } catch (Exception e) {
            exibirAlerta("Erro ao registrar devolução", "Empréstimo não encontrado.");
        }
    }

    private void listarEmprestimos() {
        List<Emprestimos> emprestimos = emprestimoService.listarTodos();
        ObservableList<String> emprestimosList = FXCollections.observableArrayList();

        for (Emprestimos emprestimo : emprestimos) {
            String texto = "ID: " + emprestimo.getIdEmprestimos() +
                    ", Livro ID: " + emprestimo.getIdLivro() +
                    ", Cliente ID: " + emprestimo.getIdCliente() +
                    ", Devolvido: " + (emprestimo.isDevolvido() ? "Sim" : "Não");
            emprestimosList.add(texto);
        }

        listaEmprestimos.setItems(emprestimosList);
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
    @FXML
    private void onAbrirPagamentos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Biblioteca008/Resources/views/pagamento.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro de Pagamento");
            stage.setScene(new Scene(root));
            stage.show();

            // Fecha a tela atual (opcional)
            ((Stage) root.getScene().getWindow()).close();
        } catch (Exception e) {
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

            // Fecha a janela atual usando um elemento da interface
            Stage janelaAtual = (Stage) campoIdLivro.getScene().getWindow();
            janelaAtual.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
