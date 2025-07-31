package alura.foro.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosCrearUsuario(
        @NotBlank String login,
        @NotBlank String contrasena) {
}
