package org.biblioteca.biblioteca009.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.biblioteca.biblioteca009.Modelos.Livros;
import org.biblioteca.biblioteca009.Repositorios.EmprestimoRepository;

public class LivrosController {

    @FXML
    private TextField campoExemplar;
    @FXML
    private TextField campoAutor;
    @FXML
    private TextField campoEdicao;
    @FXML
    private TextField campoAno;
    @FXML
    private TextField campoDisponibilidade;

    @FXML
    private TableView<Livros> tabelaLivros;
    @FXML
    private TableColumn<Livros, Integer> colId;
    @FXML
    private TableColumn<Livros, String> colExemplar;
    @FXML
    private TableColumn<Livros, String> colAutor;
    @FXML
    private TableColumn<Livros, String> colEdicao;
    @FXML
    private TableColumn<Livros, Integer> colAno;
    @FXML
    private TableColumn<Livros, String> colDisponibilidade;

    private final EmprestimoRepository.LivrosRepository livroRepo = new EmprestimoRepository.LivrosRepository();
    private ObservableList<Livros> listaLivros;

    private Livros livroSelecionado;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colExemplar.setCellValueFactory(cellData -> cellData.getValue().exemplarProperty());
        colAutor.setCellValueFactory(cellData -> cellData.getValue().autorProperty());
        colAno.setCellValueFactory(cellData -> cellData.getValue().anoProperty().asObject());
        colDisponibilidade.setCellValueFactory(cellData -> cellData.getValue().disponibilidadeProperty());

        carregarLivros();
    }

    private void carregarLivros() {
        try {
            listaLivros = FXCollections.observableArrayList(livroRepo.listarTodos());
            tabelaLivros.setItems(listaLivros);
        } catch (Exception e) {
            exibirErro("Erro ao carregar livros", e.getMessage());
        }
    }

    @FXML
    public void salvarLivro() {
        try {
            Livros livros = new Livros();
            livros.setExemplar(campoExemplar.getText());
            livros.setAutor(campoAutor.getText());
            livros.setDisponibilidade(campoDisponibilidade.getText());

            livroRepo.salvar(livros);
            carregarLivros();
            limparCampos();
        } catch (NumberFormatException e) {
            exibirErro("Erro de formato", "O campo 'Ano' deve conter apenas números.");
        } catch (Exception e) {
            exibirErro("Erro ao salvar livro", e.getMessage());
        }
    }

    @FXML
    public void atualizarLivro() {
        if (livroSelecionado != null) {
            try {
                livroSelecionado.setExemplar(campoExemplar.getText());
                livroSelecionado.setAutor(campoAutor.getText());
                livroSelecionado.setDisponibilidade(campoDisponibilidade.getText());

                livroRepo.atualizar(livroSelecionado);
                carregarLivros();
                limparCampos();
            } catch (NumberFormatException e) {
                exibirErro("Erro de formato", "O campo 'Ano' deve conter apenas números.");
            } catch (Exception e) {
                exibirErro("Erro ao atualizar livro", e.getMessage());
            }
        }
    }

    @FXML
    public void deletar() {
        if (livroSelecionado != null) {
            try {
                livroRepo.deletar(livroSelecionado.getId());
                carregarLivros();
                limparCampos();
            } catch (Exception e) {
                exibirErro("Erro ao remover livro", e.getMessage());
            }
        }
    }

    @FXML
    public void limparCampos() {
        campoExemplar.clear();
        campoAutor.clear();
        campoEdicao.clear();
        campoAno.clear();
        campoDisponibilidade.clear();
        livroSelecionado = null;
        tabelaLivros.getSelectionModel().clearSelection();
    }

    @FXML
    public void preencherCamposComSelecao() {
        livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            campoExemplar.setText(livroSelecionado.getExemplar());
            campoAutor.setText(livroSelecionado.getAutor());
            campoDisponibilidade.setText(livroSelecionado.getDisponibilidade());
        }
    }

    private void exibirErro(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
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

            // Fecha a janela atual usando qualquer nó da interface (ex: a tabela)
            Stage janelaAtual = (Stage) tabelaLivros.getScene().getWindow();
            janelaAtual.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
