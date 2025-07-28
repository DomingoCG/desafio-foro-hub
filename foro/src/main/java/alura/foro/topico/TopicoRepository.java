package alura.foro.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);//Evita que se ingresen 2 solicitudes cuyo titulo y mensaje sean iguales

    @Query ("""
            SELECT t FROM Topico t
            WHERE (:nombreCurso IS NULL OR LOWER(t.curso) LIKE LOWER(CONCAT('%', :nombreCurso,'%')))
            AND (:anio IS NULL OR YEAR(t.fecha) = :anio)
            """)
    Page<Topico> buscarPorCursoYAnio(
            @Param("nombreCurso") String nombreCurso,
            @Param("anio") Integer anio,
            Pageable pageable);
}
