package tk.jeffersondev.moviegame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Properties;

@Configuration
public class BasicAuthConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .cors().and().csrf().disable();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/login");
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        final Properties users = new Properties();
        users.put("Player1", "{noop}password1,USER,enabled");
        users.put("Player2", "{noop}password2,USER,enabled");

        return new InMemoryUserDetailsManager(users);
    }

}
