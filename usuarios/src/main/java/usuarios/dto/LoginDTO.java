package usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "El correo es requerido")
    @Email(message = "Formato de correo inválido")
    private String correoElectronico;

    @NotBlank(message = "La contraseña es requerida")
    private String contrasena;
}
