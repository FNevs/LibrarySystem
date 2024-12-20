package service;

import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void alterarUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getMatricula().equals(usuario.getMatricula())) {
                usuarios.set(i, usuario);
                return;
            }
        }
    }

    public void excluirUsuario(String matricula) {
        usuarios.removeIf(u -> u.getMatricula().equals(matricula));
    }

    public Usuario consultarUsuario(String matricula) {
        for (Usuario u : usuarios) {
            if (u.getMatricula().equals(matricula)) {
                return u;
            }
        }
        return null;
    }
}
