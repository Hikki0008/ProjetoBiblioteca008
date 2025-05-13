package org.biblioteca.biblioteca009;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BibliotecaController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLoginButtonClick() {
        welcomeText.setText("Bem vindo a Biblioteca!");
    }
}