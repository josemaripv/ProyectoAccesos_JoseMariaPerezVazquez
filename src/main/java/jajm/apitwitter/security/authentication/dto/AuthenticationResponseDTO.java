/**
 * DTO (Data Transfer Object) para la respuesta de autenticación.
 */
package jajm.apitwitter.security.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponseDTO {
    /**
     * Token de autenticación generado.
     */
    private String token;
}
