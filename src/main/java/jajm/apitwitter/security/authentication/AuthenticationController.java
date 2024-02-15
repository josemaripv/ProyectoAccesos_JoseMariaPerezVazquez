/**
 * Controlador que maneja las operaciones relacionadas con la autenticación.
 */
package jajm.apitwitter.security.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jajm.apitwitter.security.authentication.dto.AuthenticationResponseDTO;
import jajm.apitwitter.security.authentication.dto.LoginRequestDTO;
import jajm.apitwitter.security.authentication.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Maneja la solicitud de inicio de sesión.
     *
     * @param request DTO que contiene la información de inicio de sesión.
     * @return Respuesta con el token de autenticación generado.
     */
    @PostMapping("login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    /**
     * Maneja la solicitud de registro de usuario.
     *
     * @param request DTO que contiene la información de registro.
     * @return Respuesta con el token de autenticación generado.
     */
    @PostMapping("register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

}
