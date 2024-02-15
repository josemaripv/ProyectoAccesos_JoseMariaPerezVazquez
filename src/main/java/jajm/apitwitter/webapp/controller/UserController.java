package jajm.apitwitter.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jajm.apitwitter.webapp.dto.PublicationGetDto;
import jajm.apitwitter.webapp.dto.UserGetDto;
import jajm.apitwitter.webapp.dto.UserPostDto;
import jajm.apitwitter.webapp.service.UserService;
/**
 * Controlador para gestionar operaciones relacionadas con usuarios en la API.
 */
@RequestMapping("/api/user")
@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Recupera todos los usuarios disponibles.
	 *
	 * @return Lista de DTOs que representan a todos los usuarios.
	 */
	@GetMapping("/")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserGetDto> findAll() {
		return userService.findAll();
	}

	/**
	 * Recupera un usuario por su identificador.
	 *
	 * @param id El identificador del usuario.
	 * @return El DTO que representa al usuario correspondiente al identificador.
	 */
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserGetDto findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	/**
	 * Elimina un usuario por su identificador.
	 *
	 * @param id El identificador del usuario a eliminar.
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable Long id) {
		userService.deleteById(id);
	}

	/**
	 * Recupera un usuario por su nombre de usuario.
	 *
	 * @param username El nombre de usuario del usuario.
	 * @return El DTO que representa al usuario correspondiente al nombre de usuario.
	 */
	@GetMapping("/username/{username}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserGetDto findByUserName(@PathVariable String username) {
		return userService.findByUsername(username);
	}

	/**
	 * Recupera las personas que siguen al usuario por su identificador.
	 *
	 * @param id El identificador del usuario.
	 * @return Lista de DTOs que representan a las personas que siguen al usuario.
	 */
	@GetMapping("/{id}/followerpeople")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserGetDto> findFollowerPeopleById(@PathVariable Long id) {
		return userService.findFollowerPeopleById(id);
	}

	/**
	 * Recupera las personas seguidas por el usuario por su identificador.
	 *
	 * @param id El identificador del usuario.
	 * @return Lista de DTOs que representan a las personas seguidas por el usuario.
	 */
	@GetMapping("/{id}/followedpeople")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserGetDto> findFollowedPeopleById(@PathVariable Long id) {
		return userService.findFollowedPeopleById(id);
	}

	/**
	 * Actualiza la descripción de un usuario por su identificador.
	 *
	 * @param id          El identificador del usuario.
	 * @param description La nueva descripción del usuario.
	 */
	@PatchMapping("/{id}/description/{description}")
	@ResponseStatus(code = HttpStatus.OK)
	public void setDescriptionById(@PathVariable Long id, @PathVariable String description) {
		userService.setDescriptionById(id, description);
	}

	/**
	 * Recupera las publicaciones realizadas por el usuario por su identificador.
	 *
	 * @param id El identificador del usuario.
	 * @return Lista de DTOs que representan las publicaciones del usuario.
	 */
	@GetMapping("/{id}/publications")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PublicationGetDto> findPublicationsById(@PathVariable Long id) {
		return userService.findPublicationsById(id);
	}

	/**
	 * Recupera las publicaciones de las personas seguidas por el usuario por su identificador.
	 *
	 * @param id El identificador del usuario.
	 * @return Lista de DTOs que representan las publicaciones de las personas seguidas por el usuario.
	 */
	@GetMapping("/{id}/followedpeople/publications")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PublicationGetDto> findFollowedPeoplePublicationsById(@PathVariable Long id) {
		return userService.findFollowedPeoplePublicationsById(id);
	}
}
