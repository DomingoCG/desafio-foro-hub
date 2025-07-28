package alura.foro.topico;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosActualizarTopico(
                                    @NotBlank String titulo,
                                    @NotBlank String mensaje,
                                    @NotNull @JsonFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
                                    @NotBlank String status,
                                    @NotBlank String autor,
                                    @NotBlank String curso,
                                    @NotBlank String respuestas) {
}
