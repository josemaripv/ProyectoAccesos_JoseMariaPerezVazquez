package jajm.apitwitter.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jajm.apitwitter.webapp.dto.PublicationGetDto;
import jajm.apitwitter.webapp.dto.UserGetDto;
import jajm.apitwitter.webapp.dto.UserPostDto;
import jajm.apitwitter.webapp.exceptions.UserNotFoundException;
import jajm.apitwitter.webapp.persistence.model.User;
import jajm.apitwitter.webapp.persistence.repository.UserRepository;
/**
 * Servicio que gestiona operaciones relacionadas con los usuarios.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Obtiene un usuario por su identificador en formato DTO.
	 *
	 * @param id Identificador del usuario.
	 * @return DTO del usuario.
	 * @throws UserNotFoundException si no se encuentra el usuario.
	 */
	public UserGetDto findById(long id) {
		Optional<UserGetDto> optionalUserGetDto = userRepository.findUserGetDtoById(id);
		return optionalUserGetDto.orElseThrow(() -> new UserNotFoundException(id));
	}

	/**
	 * Obtiene un usuario por su nombre de usuario en formato DTO.
	 *
	 * @param username Nombre de usuario.
	 * @return DTO del usuario.
	 * @throws UserNotFoundException si no se encuentra el usuario.
	 */
	public UserGetDto findByUsername(String username) {
		Optional<UserGetDto> optionalUserGetDto = userRepository.findUserGetDtoByUsername(username);
		return optionalUserGetDto.orElseThrow(() -> new UserNotFoundException(username));
	}

	/**
	 * Elimina un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 */
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	/**
	 * Inserta un nuevo usuario.
	 *
	 * @param userPostDTO DTO con la información del nuevo usuario.
	 */
	public void insert(UserPostDto userPostDTO) {
		User user = new User(userPostDTO.getUserName(),
				userPostDTO.getEmail(),
				userPostDTO.getPassword(),
				userPostDTO.getDescription(),
				userPostDTO.getCreationDate());
		userRepository.save(user);
	}

	/**
	 * Establece la descripción de un usuario por su identificador.
	 *
	 * @param id          Identificador del usuario.
	 * @param description Nueva descripción del usuario.
	 * @throws UserNotFoundException si no se encuentra el usuario.
	 */
	public void setDescriptionById(Long id, String description) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setDescription(description);
			userRepository.save(user);
		} else {
			throw new UserNotFoundException(id);
		}
	}

	/**
	 * Obtiene todos los usuarios en formato DTO.
	 *
	 * @return Lista de DTO de usuarios.
	 */
	public List<UserGetDto> findAll() {
		return userRepository.findAllUserGetDto();
	}

	/**
	 * Obtiene los usuarios seguidores de un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de usuarios seguidores.
	 */
	public List<UserGetDto> findFollowerPeopleById(Long id) {
		return userRepository.findFollowerPeopleGetDtoById(id);
	}

	/**
	 * Obtiene los usuarios seguidos por un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de usuarios seguidos.
	 */
	public List<UserGetDto> findFollowedPeopleById(Long id) {
		return userRepository.findFollowedPeopleGetDtoById(id);
	}

	/**
	 * Obtiene las publicaciones de un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de publicaciones del usuario.
	 */
	public List<PublicationGetDto> findPublicationsById(Long id) {
		return userRepository.findPublicationsGetDtoById(id);
	}

	/**
	 * Obtiene las publicaciones de los usuarios seguidos por un usuario por su identificador.
	 *
	 * @param id Identificador del usuario.
	 * @return Lista de DTO de publicaciones de los usuarios seguidos.
	 */
	public List<PublicationGetDto> findFollowedPeoplePublicationsById(Long id) {
		return userRepository.findFollowedPeoplePublicationsGetDtoById(id);
	}
}