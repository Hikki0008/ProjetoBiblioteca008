package org.biblioteca.biblioteca009.Services;


import org.biblioteca.biblioteca008.Modelos.Usuario;
import org.biblioteca.biblioteca008.Repositorios.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository repository = new UsuarioRepository();

    public boolean autenticar(String cpf, String senha) {
        Usuario usuario = repository.buscarPorcpf(cpf);
        return usuario != null && usuario.getSenha().equals(senha);
    }
}
