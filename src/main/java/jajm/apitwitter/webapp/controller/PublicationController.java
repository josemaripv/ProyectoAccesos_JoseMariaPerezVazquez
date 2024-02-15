package jajm.apitwitter.webapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jajm.apitwitter.webapp.dto.PublicationGetDto;
import jajm.apitwitter.webapp.dto.PublicationPostDto;
import jajm.apitwitter.webapp.dto.PublicationPutDto;
import jajm.apitwitter.webapp.service.PublicationService;
/**
 * Controlador para gestionar operaciones relacionadas con las publicaciones en la API.
 */
@RequestMapping("/api/publication")
@RestController
@CrossOrigin
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	/**
	 * Recupera una publicación por su identificador.
	 *
	 * @param id El identificador de la publicación.
	 * @return La publicación correspondiente al identificador.
	 */
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public PublicationGetDto findById(@PathVariable long id) {
		return publicationService.findById(id);
	}

	/**
	 * Elimina una publicación por su identificador.
	 *
	 * @param id El identificador de la publicación a eliminar.
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable long id) {
		publicationService.deleteById(id);
	}

	/**
	 * Crea una nueva publicación.
	 *
	 * @param publicationPostDto DTO que contiene la información para crear la nueva publicación.
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void insert(@RequestBody PublicationPostDto publicationPostDto) {
		publicationService.insert(publicationPostDto);
	}

	/**
	 * Actualiza una publicación existente.
	 *
	 * @param publicationPutDto DTO que contiene la información para actualizar la publicación.
	 */
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody PublicationPutDto publicationPutDto) {
		publicationService.update(publicationPutDto);
	}

	/**
	 * Recupera todas las publicaciones disponibles.
	 *
	 * @return Lista de DTOs que representan todas las publicaciones.
	 */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<PublicationGetDto> getAll() {
		return publicationService.findAll();
	}
}

