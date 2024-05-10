package fr.pay.scim.server.conf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/favicon.ico");
    }

    @Bean
    UserDetailsService userDetailsService() {

		return new InMemoryUserDetailsManager(

			// FIXME: Externalser le mot de passe	
			User.builder()
				.username("user")
				.password(passwordEncoder().encode("yMAstMxkDwf1eUZi"))
				.roles("USER")
				.build()

			);
	}
    
    @Bean
    @Order(value = 40)
    SecurityFilterChain filterChainScim(HttpSecurity http) throws Exception {
		
		http
			.securityMatcher("/scim/v2/**")
				.cors(Customizer.withDefaults())
				.csrf((csrf) -> csrf.disable())
				.headers((headers) -> headers.frameOptions((frame) -> frame.disable()))
				.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
																	.anyRequest().hasRole("USER"))
				.httpBasic(Customizer.withDefaults());

        return http.build();
    }    

    
	@Bean
	@Order(value = 90)
	SecurityFilterChain filterChainSwagger(HttpSecurity http) throws Exception {

		http
			.securityMatcher("/v3/api-docs/**", "/swagger-ui/**", "/v2/api-docs/**", "/swagger-resources/**")
				.cors(Customizer.withDefaults())
				.csrf((csrf) -> csrf.disable())
				.headers((headers) -> headers.frameOptions((frame) -> frame.disable()))
				.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
																	.anyRequest().permitAll())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}


    @Bean
    @Order(value = 100)
    SecurityFilterChain filterChainDefault(HttpSecurity http) throws Exception {
		
		http
			.securityMatcher("/**")
				.cors(Customizer.withDefaults())
				.csrf((csrf) -> csrf.disable())
				.headers((headers) -> headers.frameOptions((frame) -> frame.disable()))
				.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
																	.anyRequest().denyAll())
				.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
