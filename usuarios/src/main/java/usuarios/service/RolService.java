package usuarios.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usuarios.dto.ResponseDto;
import usuarios.dto.RolDTO;
import usuarios.model.Rol;
import usuarios.repository.RolRepository;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public ResponseDto crearRol(RolDTO dto) {
        if (rolRepository.findByNombre(dto.getNombre()) != null) {
            throw new RuntimeException("El rol ya existe");
        }

        Rol rol = new Rol();
        rol.setNombre(dto.getNombre());

        Rol guardado = rolRepository.save(rol);
        return new ResponseDto("Rol creado exitosamente", guardado);
    }
}
