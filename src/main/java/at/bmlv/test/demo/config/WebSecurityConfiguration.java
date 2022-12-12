package at.bmlv.test.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Bean
    @Order( Ordered.HIGHEST_PRECEDENCE )
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins( List.of( "http://localhost:4200" ) );
        configuration.setAllowedMethods( List.of( "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH" ) );
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials( true );
        // setAllowedHeaders is important! Without it, OPTIONS preflight request will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders( List.of( "Authorization", "Cache-Control", "Content-Type" ) );
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( "/**", configuration );
        return source;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable() // we are using jwt which is required everywhere and prevents csrf attacks
                .authorizeHttpRequests( auth ->
                        auth
                                .anyRequest().permitAll()
                )

                .httpBasic();
       return http.build();
    }
}
