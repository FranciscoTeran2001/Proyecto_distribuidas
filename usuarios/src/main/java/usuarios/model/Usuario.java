package usuarios.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "correo_electronico", nullable = false, unique = true, length = 100)
    private String correoElectronico;

    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;


   /* // Relaciones futuras, si se quiere: eventos organizados e inscripciones
    @OneToMany(mappedBy = "organizador")
    @JsonIgnore
    private List<Evento> eventosOrganizados;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Inscripcion> inscripciones;*/

}

