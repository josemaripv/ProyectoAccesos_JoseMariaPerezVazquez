package jajm.apitwitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jajm.apitwitter.webapp.dto.UserGetDto;
import jajm.apitwitter.webapp.persistence.model.Publication;
import jajm.apitwitter.webapp.persistence.model.User;
import jajm.apitwitter.webapp.persistence.repository.LinkRepository;
import jajm.apitwitter.webapp.persistence.repository.PublicationRepository;
import jajm.apitwitter.webapp.persistence.repository.UserRepository;
import jajm.apitwitter.webapp.service.LinkService;

/**
 * Clase principal de la aplicación Spring Boot. Implementa CommandLineRunner,
 * lo que significa que el método run se ejecutará automáticamente al iniciar la aplicación.
 */

@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private PublicationRepository publicationRepository;
	/**
	 * Método principal que se ejecuta al iniciar la aplicación.
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	/**
	 * Método run que se ejecuta automáticamente al iniciar la aplicación. Se encarga
	 * de crear y almacenar usuarios y publicaciones de ejemplo, así como establecer
	 * relaciones de seguimiento entre usuarios.
	 *
	 * @param args Argumentos de línea de comandos (no se utilizan en este caso).
	 */
	@Override
	public void run(String... args) {

		// Creación y almacenamiento de usuarios y publicaciones de ejemplo

		// Usuario 1
		User user1 = new User("Jose1", "jose1@example.es", "321", "Jose1 descrito", "2024-02-02");
		userRepository.save(user1);
		Publication publication1 = new Publication(user1, "Primera publicación de Jose1", "2024-02-03", "2024-02-03");
		user1.addPublication(publication1);
		publicationRepository.save(publication1);

		// Usuario 2
		User user2 = new User("Jose2", "jose2@example.es", "123", "Jose2 descrito", "2024-02-01");
		userRepository.save(user2);
		Publication publication2 = new Publication(user2, "Primera publicación de Jose2", "2024-02-02", "2024-02-05");
		user2.addPublication(publication2);
		publicationRepository.save(publication2);
		Publication publication3 = new Publication(user2, "Segunda publicación de Jose2", "2024-03-02", "2024-03-05");
		user2.addPublication(publication3);
		publicationRepository.save(publication3);

		// Usuario 3
		User user3 = new User("Jose3", "jose3@example.es", "101", "Jose3 descrito", "2024-02-10");
		userRepository.save(user3);
		Publication publication4 = new Publication(user3, "Primera publicación de Jose3", "2024-02-11", "2024-02-15");
		user2.addPublication(publication4);
		publicationRepository.save(publication4);
		Publication publication5 = new Publication(user3, "Segunda publicación de Jose3", "2024-02-12", "2024-02-14");
		user2.addPublication(publication5);
		publicationRepository.save(publication5);
		Publication publication6 = new Publication(user3, "Tercera publicación de Jose3", "2024-02-20", "2024-02-21");
		user2.addPublication(publication6);
		publicationRepository.save(publication6);

		// Establecer relaciones de seguimiento
		linkService.add(user3, user1);
		linkService.add(user3, user2);
		linkService.add(user2, user3);
		linkService.add(user1, user3);
		linkService.add(user2, user1);
	}
}