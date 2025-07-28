package alura.foro.topico;

import java.time.LocalDate;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fecha,
        String status,
        String autor,
        String curso
) {
    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso());
    }
}
