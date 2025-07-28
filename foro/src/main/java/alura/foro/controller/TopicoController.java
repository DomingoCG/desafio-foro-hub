package alura.foro.controller;

import alura.foro.excepciones.TopicoNoEncontradoException;
import alura.foro.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;//Crea una instancia de TopicoRepository

    @Transactional //Se tiene que usar para permitir las modificaciones en la base de datos
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody @Valid DatosCrearTopico datosTopico) {
        boolean existeDuplicado = repository.existsByTituloAndMensaje(
                datosTopico.titulo(),
                datosTopico.mensaje()
        );
        if (existeDuplicado) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Ya existe un tópico con ese título y mensaje");
        }

        //Cuando se utiliza la interfaz repository este es el metodo que sirve para guardar en la base de datos
        repository.save(new Topico(null, datosTopico.titulo(), datosTopico.mensaje(), datosTopico.fecha(), datosTopico.status(), datosTopico.autor(), datosTopico.curso(), datosTopico.respuestas()));

        return ResponseEntity.ok("Tópico creado exitosamente");
    }

    //Este metodo solo lee la base de datos, no va a modificar, por eso no es necesario agregar la anotacion @Transactional
    @GetMapping
    public Page<DatosListaTopico> listarTopicos(@PageableDefault(size = 10, sort = "fecha") Pageable paginacion) {
        return repository.findAll(paginacion)
                .map(DatosListaTopico::new);
        //Stream convierte cada topico en un DatoListaTopico
        // El map permite hacer una modificacion para cada topico, en este caso, se llama al constructor de DatosListaTopico que crea y devuelve para cada topico un nuevo objeto del tipo DatosListaTopico
        //El tolist reconvierte a una lista el stream
    }

    @GetMapping("/filtrar")
    public Page<DatosListaTopico> filtrarTopico(
            @RequestParam(required = false) String nombreCurso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fecha") Pageable paginacion) {
        return repository.buscarPorCursoYAnio(nombreCurso, anio, paginacion)
                .map(DatosListaTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopicoPorId(@PathVariable Long id) {
        if (id == null || id <= 4) {
            return ResponseEntity.badRequest().body(
                    Map.of("Error", "ID inválido", "mensaje", "El ID debe ser un número mayor a 3")
            );
        }

        var topico = repository.findById(id)
                .orElseThrow(() -> new TopicoNoEncontradoException(id));
        return ResponseEntity.ok(new DatosListaTopico(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        var opcionalTopico = repository.findById(id);
        if (opcionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = opcionalTopico.get();
        topico.actualizarDatos(datos);
        return ResponseEntity.ok(topico);
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
