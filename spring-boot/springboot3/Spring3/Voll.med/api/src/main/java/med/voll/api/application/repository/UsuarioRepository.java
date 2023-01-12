package med.voll.api.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import med.voll.api.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	UserDetails findByLogin(String username);

}
