package alura.foro.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.Map;

@ControllerAdvice
public class ManejadorDeErrores {
    @ExceptionHandler(TopicoNoEncontradoException.class)
    public ResponseEntity<?> manejarTopicoNoEncontrado (TopicoNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        "Error", "Tópico no encontrado",
                        "Mensaje", ex.getMessage(),
                        "fecha", LocalDate.now().toString()
                )
        );
    }
}
