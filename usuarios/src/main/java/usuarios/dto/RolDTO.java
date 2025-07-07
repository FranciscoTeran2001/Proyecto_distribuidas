package usuarios.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RolDTO {
    @NotBlank(message = "El nombre del rol es requerido")
    private String nombre;
}