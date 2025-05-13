package org.biblioteca.biblioteca009.Services;



import org.biblioteca.biblioteca008.Modelos.Livros;
import org.biblioteca.biblioteca008.Repositorios.EmprestimoRepository;

import java.util.List;

/**
 * Classe de serviço que usa o repositório para acessar os dados.
 * Pode conter validações, regras de negócio ou lógica adicional.
 */
public class LivrosService {

    private EmprestimoRepository.LivrosRepository repositorio = new EmprestimoRepository.LivrosRepository();

    public void adicionarLivro(Livros livro) {
        // Você pode colocar validações aqui antes de salvar
        repositorio.salvar(livro);
    }

    public Livros obterLivroPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    public List<Livros> listarLivros() {
        return repositorio.listarTodos();
    }

    public void atualizarLivro(Livros livro) {
        // Você pode validar aqui se o livro existe, etc.
        repositorio.atualizar(livro);
    }

    public void removerLivro(int id) {
        repositorio.deletar(id);
    }
}
