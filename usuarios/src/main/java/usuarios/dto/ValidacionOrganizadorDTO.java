package usuarios.dto;

import lombok.Data;

@Data
public class ValidacionOrganizadorDTO {
    private Long usuarioId;
    private String accion; // "VALIDAR" o "RESPUESTA"
    private boolean valido;
    private String mensaje;
}
