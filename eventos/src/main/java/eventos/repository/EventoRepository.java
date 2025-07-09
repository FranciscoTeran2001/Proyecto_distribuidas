package eventos.repository;


import eventos.entity.Evento;

import eventos.enums.EstadoEvento;
import eventos.enums.ValidacionOrganizadorEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    // Busca eventos por ID de organizador (usando el ID directo, no relación JPA)
    List<Evento> findByOrganizadorId(Long organizadorId);

    // Busca eventos por estado de validación
    List<Evento> findByValidacionEstado(ValidacionOrganizadorEstado estado);

    // Busca eventos por estado de negocio (ACTIVO/CANCELADO)
    List<Evento> findByEstado(EstadoEvento estado);

    // Actualización eficiente del estado de validación
    @Transactional
    @Modifying
    @Query("UPDATE Evento e SET e.validacionEstado = :estado WHERE e.id = :eventoId")
    void actualizarEstadoValidacion(Long eventoId, ValidacionOrganizadorEstado estado);

    // Método para buscar con ambos estados (opcional)
    Optional<Evento> findByIdAndValidacionEstado(Long id, ValidacionOrganizadorEstado estado);
}