package alura.foro.excepciones;

public class TopicoNoEncontradoException extends RuntimeException{
    public TopicoNoEncontradoException(Long id){
        super("El tópico con ID " + id + " no se encontró");
    }
}
