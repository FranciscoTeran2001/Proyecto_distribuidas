package usuarios.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String rol; // nombre del rol (ej. "ORGANIZADOR")
}