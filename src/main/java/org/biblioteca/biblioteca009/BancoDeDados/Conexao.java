package org.biblioteca.biblioteca009.BancoDeDados;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca008";
    private static final String USUARIO = "root";
    private static final String SENHA = "hikki";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL não encontrado.");
            e.printStackTrace();
        }

        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
