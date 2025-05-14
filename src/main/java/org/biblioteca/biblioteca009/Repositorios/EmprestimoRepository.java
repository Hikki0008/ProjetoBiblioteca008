package org.biblioteca.biblioteca009.Repositorios;


import org.biblioteca.biblioteca009.Modelos.Emprestimos;
import org.biblioteca.biblioteca009.Modelos.Livros;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {
    private final String url = "jdbc:mysql://localhost:3306/biblioteca008";
    private final String usuario = "root";
    private final String senha = "hikki";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

    public void salvar(Emprestimos emp) {
        String sql = "INSERT INTO emprestimos (livro_id, cliente_id, data_emprestimo, data_devolucao, devolvido) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emp.getIdLivro());
            stmt.setInt(2, emp.getIdCliente());
            stmt.setDate(3, Date.valueOf(emp.getDataEmprestimos()));
            stmt.setDate(4, Date.valueOf(emp.getDataDevolucao()));
            stmt.setBoolean(5, emp.isDevolvido());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Emprestimos emp) {
        String sql = "UPDATE emprestimos SET data_devolucao = ?, devolvido = ? WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(emp.getDataDevolucao())); // Atualiza a data de devolução
            stmt.setBoolean(2, emp.isDevolvido()); // Marca como devolvido ou não
            stmt.setInt(3, emp.getIdEmprestimos()); // Identificador do empréstimo para atualização

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emprestimos> listarTodos() {
        List<Emprestimos> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Emprestimos emp = new Emprestimos();
                emp.setIdEmprestimos(rs.getInt("id"));
                emp.setIdLivro(rs.getInt("livro_id"));
                emp.setIdCliente(rs.getInt("cliente_id"));
                emp.setDataEmprestimos(rs.getDate("data_emprestimo").toLocalDate());
                emp.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
                emp.setDevolvido(rs.getBoolean("devolvido"));

                emprestimos.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }

    /**
     * Classe responsável por gerenciar as operações de persistência de dados dos livros.
     */
    public static class LivrosRepository {

        private final String url = "jdbc:mysql://localhost:3306/biblioteca";
        private final String usuario = "root";
        private final String senha = "sua_senha";

        private Connection conectar() throws SQLException {
            return DriverManager.getConnection(url, usuario, senha);
        }

        public void salvar(Livros livro) {
            String sql = "INSERT INTO livros (exemplar, autor, edicao, ano, disponibilidade) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, livro.getExemplar());
                stmt.setString(2, livro.getAutor());
                stmt.setString(5, livro.getDisponibilidade());

                stmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao salvar livro: " + e.getMessage(), e);
            }
        }


        public Livros buscarPorId(int id) {
            String sql = "SELECT * FROM livros WHERE id = ?";

            try (Connection conn = conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return new Livros(
                            rs.getInt("id"),
                            rs.getString("exemplar"),
                            rs.getString("autor"),
                            rs.getString("edicao"),
                            rs.getString("ano"),
                            rs.getString("disponibilidade")
                    );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        public List<Livros> listarTodos() {
            List<Livros> livros = new ArrayList<>();
            String sql = "SELECT * FROM livros";

            try (Connection conn = conectar();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    livros.add(new Livros(
                            rs.getInt("id"),
                            rs.getString("exemplar"),
                            rs.getString("autor"),
                            rs.getString("edicao"),
                            rs.getString("ano"),
                            rs.getString("disponibilidade")
                    ));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return livros;
        }

        public void atualizar(Livros livro) {
            String sql = "UPDATE livros SET exemplar = ?, autor = ?, edicao = ?, ano = ?, disponibilidade = ? WHERE id = ?";

            try (Connection conn = conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, livro.getExemplar());
                stmt.setString(2, livro.getAutor());
                stmt.setString(5, livro.getDisponibilidade());
                stmt.setInt(6, livro.getId());

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
    }
}
