package org.biblioteca.biblioteca009.Repositorios;



import org.biblioteca.biblioteca009.Modelos.Livros;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivrosRepository {

    private final String url = "jdbc:mysql://localhost:3306/biblioteca008"; // ajuste conforme seu banco
    private final String usuario = "root";
    private final String senha = null;

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

    public void salvar(Livros livro) {
        String sql = "INSERT INTO livros (exemplar, autor, edicao, ano, disponibilidade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getExemplar());
            stmt.setString(2, livro.getAutor());;
            stmt.setString(3, livro.getDisponibilidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livros buscarPorId(int id) {
        String sql = "SELECT * FROM livros WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return construirLivro(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Livros> listarTodos() {
        List<Livros> lista = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(construirLivro(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(Livros livro) {
        String sql = "UPDATE livros SET exemplar = ?, autor = ?, edicao = ?, ano = ?, disponibilidade = ? WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getExemplar());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getDisponibilidade());
            stmt.setInt(4, livro.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Livros construirLivro(ResultSet rs) throws SQLException {
        Livros livro = new Livros();
        livro.setId(rs.getInt("id"));
        livro.setExemplar(rs.getString("exemplar"));
        livro.setAutor(rs.getString("autor"));
        livro.setDisponibilidade(rs.getString("disponibilidade"));
        return livro;
    }
}

