package usuarios.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import usuarios.dto.ValidacionOrganizadorDTO;
import usuarios.model.Usuario;
import usuarios.repository.UsuarioRepository;

@Service
public class OrganizadorValidatorService {

    private final UsuarioRepository usuarioRepository;

    public OrganizadorValidatorService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @RabbitListener(queues = "usuarios.validar.organizador")
    public ValidacionOrganizadorDTO validarOrganizador(ValidacionOrganizadorDTO request) {
        ValidacionOrganizadorDTO response = new ValidacionOrganizadorDTO();
        response.setUsuarioId(request.getUsuarioId());
        response.setAccion("RESPUESTA");

        try {
            Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Verificar si el usuario tiene rol de organizador
            if ("ORGANIZADOR".equals(usuario.getRol().getNombre())) {
                response.setValido(true);
                response.setMensaje("Usuario validado como organizador");
            } else {
                response.setValido(false);
                response.setMensaje("El usuario no tiene rol de organizador");
            }
        } catch (Exception e) {
            response.setValido(false);
            response.setMensaje("Error al validar organizador: " + e.getMessage());
        }

        return response;
    }
}
