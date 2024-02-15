package jajm.apitwitter.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jajm.apitwitter.webapp.dto.PublicationGetDto;
import jajm.apitwitter.webapp.dto.PublicationPostDto;
import jajm.apitwitter.webapp.dto.PublicationPutDto;
import jajm.apitwitter.webapp.exceptions.PublicationIncorrectAuthorException;
import jajm.apitwitter.webapp.exceptions.PublicationNotFoundException;
import jajm.apitwitter.webapp.persistence.model.Publication;
import jajm.apitwitter.webapp.persistence.model.User;
import jajm.apitwitter.webapp.persistence.repository.PublicationRepository;
import jajm.apitwitter.webapp.persistence.repository.UserRepository;

/**
 * Servicio que gestiona operaciones relacionadas con las publicaciones.
 */
@Service
public class PublicationService {

	@Autowired
	private PublicationRepository publicationRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Obtiene todas las publicaciones en formato DTO.
	 *
	 * @return Lista de DTO de publicaciones.
	 */
	public List<PublicationGetDto> findAll() {
		return publicationRepository.findAllPublicationsGetDto();
	}

	/**
	 * Obtiene una publicación por su identificador en formato DTO.
	 *
	 * @param id Identificador de la publicación.
	 * @return DTO de la publicación.
	 * @throws PublicationNotFoundException si no se encuentra la publicación.
	 */
	public PublicationGetDto findById(Long id) {
		Optional<PublicationGetDto> optionalPublicationGetDto = publicationRepository.findPublicationGetDtoById(id);
		return optionalPublicationGetDto.orElseThrow(() -> new PublicationNotFoundException(id));
	}

	/**
	 * Elimina una publicación por su identificador.
	 *
	 * @param id Identificador de la publicación.
	 */
	public void deleteById(Long id) {
		publicationRepository.deleteById(id);
	}

	/**
	 * Inserta una nueva publicación.
	 *
	 * @param publicationPostDto DTO con la información de la nueva publicación.
	 * @throws PublicationNotFoundException si no se encuentra el usuario asociado a la publicación.
	 */
	public void insert(PublicationPostDto publicationPostDto) {
		Long userId = publicationPostDto.getUserId();
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow(() -> new PublicationNotFoundException(userId));

		Publication publication = new Publication(user,
				publicationPostDto.getText(),
				publicationPostDto.getCreationDate(),
				publicationPostDto.getEditionDate());

		publicationRepository.save(publication);
	}

	/**
	 * Actualiza una publicación existente.
	 *
	 * @param publicationPutDto DTO con la información actualizada de la publicación.
	 * @throws PublicationNotFoundException si no se encuentra la publicación.
	 * @throws PublicationIncorrectAuthorException si el autor de la publicación no coincide.
	 */
	public void update(PublicationPutDto publicationPutDto) {
		Long publicationId = publicationPutDto.getId();
		Optional<Publication> optionalPublication = publicationRepository.findById(publicationId);
		Publication publication = optionalPublication.orElseThrow(() -> new PublicationNotFoundException(publicationId));

		if (publication.getUser().getId().equals(publicationPutDto.getUserId())) {
			publication.setText(publicationPutDto.getText());
			publication.setCreationDate(publicationPutDto.getCreationDate());
			publication.setEditionDate(publicationPutDto.getEditionDate());
			publicationRepository.save(publication);
		} else {
			throw new PublicationIncorrectAuthorException(publicationId);
		}
	}
}