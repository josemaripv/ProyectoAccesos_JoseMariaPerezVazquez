package jajm.apitwitter.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Clase de transferencia de datos (DTO) para la actualización de publicaciones existentes.
 * Contiene información necesaria para actualizar una publicación existente.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationPutDto {

    /**
     * Identificador único de la publicación que se va a actualizar.
     */
    private Long id;

    /**
     * Identificador único del usuario que realiza la publicación.
     */
    private Long userId;

    /**
     * Texto actualizado de la publicación.
     */
    private String text;

    /**
     * Fecha de creación actualizada de la publicación.
     */
    private String creationDate;

    /**
     * Fecha de edición actualizada de la publicación.
     */
    private String editionDate;
}
