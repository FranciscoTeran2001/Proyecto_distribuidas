package usuarios.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usuarios.dto.LoginDTO;
import usuarios.dto.UsuarioDTO;
import usuarios.dto.UsuarioRegistroDTO;
import usuarios.dto.ResponseDto;
import usuarios.model.Rol;
import usuarios.model.Usuario;
import usuarios.repository.RolRepository;
import usuarios.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private NotificacionProducer producer;

    // @Autowired
    // private NotificacionProducer producer;

    // 1. Crear usuario
    public ResponseDto registrarUsuario(UsuarioRegistroDTO dto) {
        if (usuarioRepository.findByCorreoElectronico(dto.getCorreoElectronico()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setContrasenaHash(dto.getContrasena()); // Aquí puedes aplicar encoding si usas Spring Security
        usuario.setRol(rol);

        Usuario guardado = usuarioRepository.save(usuario);

        producer.enviarNotificacion("Usuario "+guardado.getNombre()+" registrado", "Usuario");

        return new ResponseDto("Usuario registrado exitosamente", toDTO(guardado));
    }

    // 2. Listar todos los usuarios
    public List<ResponseDto> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new ResponseDto("Usuario: " + usuario.getCorreoElectronico(), toDTO(usuario)))
                .collect(Collectors.toList());
    }

    // 3. Obtener usuario por ID
    public ResponseDto obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el usuario con id: " + id));
        return new ResponseDto("Usuario encontrado con ID: " + id, toDTO(usuario));
    }

    // 4. Actualizar usuario
    public ResponseDto actualizarUsuario(Long id, UsuarioRegistroDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el usuario con id: " + id));

        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setContrasenaHash(dto.getContrasena()); // Puedes codificar
        usuario.setRol(rol);

        Usuario actualizado = usuarioRepository.save(usuario);
        return new ResponseDto("Usuario actualizado exitosamente", toDTO(actualizado));
    }

    // 5. Eliminar usuario
    public ResponseDto eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el usuario con id: " + id));
        usuarioRepository.delete(usuario);
        return new ResponseDto("Usuario eliminado exitosamente con id: " + id, null);
    }

    // 🔁 Utilidad: convertir a DTO
    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreoElectronico(usuario.getCorreoElectronico());
        dto.setRol(usuario.getRol().getNombre());
        return dto;
    }

    public ResponseDto login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(loginDTO.getCorreoElectronico())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // Comparación simple de contraseñas sin hash
        if (!loginDTO.getContrasena().equals(usuario.getContrasenaHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Por ahora retornamos los datos del usuario
        return new ResponseDto("Login exitoso", toDTO(usuario));
    }
}

