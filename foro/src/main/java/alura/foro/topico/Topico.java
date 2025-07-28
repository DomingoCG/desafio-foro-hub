package alura.foro.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String titulo;
    String mensaje;
    LocalDate fecha;
    String status;
    String autor;
    String curso;
    String respuestas;

    public void actualizarDatos(@Valid DatosActualizarTopico datos) {
        if (datos.titulo()!= null){
            this.titulo = datos.titulo();
        }
        if (datos.mensaje()!= null){
            this.mensaje = datos.mensaje();
        }
        if (datos.fecha()!= null){
            this.fecha = datos.fecha();
        }
        if (datos.status()!= null){
            this.status = datos.status();
        }
        if (datos.autor()!= null){
            this.autor = datos.autor();
        }
        if (datos.curso()!= null){
            this.curso = datos.curso();
        }
        if (datos.respuestas()!= null){
            this.respuestas = datos.respuestas();
        }

    }
}
