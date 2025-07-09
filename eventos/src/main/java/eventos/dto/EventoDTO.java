package eventos.dto;


import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
public class EventoDTO {
private String titulo;
private String descripcion;
private LocalDateTime fechaHora;
private String ubicacion;
    private String tipo;

    @NotNull
    private Long organizadorId;
}
