package alura.foro.controller;

import alura.foro.usuario.DatosCrearUsuario;
import alura.foro.usuario.Usuario;
import alura.foro.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public void registrarUsuario(@RequestBody @Valid DatosCrearUsuario datos) {
        if (repository.findByLogin(datos.login()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        String contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
        Usuario nuevoUsuario = new Usuario(null, datos.login(), contrasenaEncriptada);
        repository.save(nuevoUsuario);
    }
}
