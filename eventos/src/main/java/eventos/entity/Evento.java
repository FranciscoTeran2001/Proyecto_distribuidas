package eventos.entity;


import eventos.enums.EstadoEvento;
import eventos.enums.ValidacionOrganizadorEstado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private LocalDateTime fechaHora;
    private String ubicacion;
    private String tipo;

    @Enumerated(EnumType.STRING)
    private EstadoEvento estado;

    @Column(name = "organizador_id")
    private Long organizadorId;


    @Enumerated(EnumType.STRING)
    @Column(name = "validacion_estado")
    private ValidacionOrganizadorEstado validacionEstado;
}




