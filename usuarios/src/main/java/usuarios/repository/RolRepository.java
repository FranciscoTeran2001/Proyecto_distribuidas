package usuarios.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import usuarios.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}