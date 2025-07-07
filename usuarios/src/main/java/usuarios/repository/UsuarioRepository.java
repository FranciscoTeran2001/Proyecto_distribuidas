package usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usuarios.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}

