/**
 * Configuración del filtro de seguridad de la cadena.
 */
package jajm.apitwitter.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jajm.apitwitter.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterChainConfiguration {

	@Autowired
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private final AuthenticationProvider authenticationProvider;

	/**
	 * Configura el filtro de seguridad de la cadena.
	 *
	 * @param httpSecurityFilterChainBuilder Configurador de la cadena de filtros de seguridad HTTP.
	 * @return Configuración de la cadena de filtros de seguridad.
	 * @throws Exception Si hay un error en la configuración de seguridad.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurityFilterChainBuilder) throws Exception {
		return httpSecurityFilterChainBuilder
				.csrf(csrfConfigurer -> csrfConfigurer.disable())

				.authorizeHttpRequests(registry -> registry
						.requestMatchers("/auth/**", "/swagger-ui/**").permitAll()
						.anyRequest().authenticated())

				.authenticationProvider(authenticationProvider)

				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

				.build();
	}

}
