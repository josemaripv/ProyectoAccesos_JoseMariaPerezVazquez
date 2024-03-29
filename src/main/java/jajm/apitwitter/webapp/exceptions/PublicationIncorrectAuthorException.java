package jajm.apitwitter.webapp.exceptions;
/**
 * Excepción personalizada para indicar que el autor de una publicación es incorrecto.
 * Se lanza cuando se intenta realizar una operación que requiere un autor específico en una publicación.
 */
public class PublicationIncorrectAuthorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que acepta el identificador de la publicación incorrecta.
	 *
	 * @param id El identificador de la publicación incorrecta.
	 */
	public PublicationIncorrectAuthorException(long id) {
		super("Publicación no encontrada: id = " + id);
	}
}

