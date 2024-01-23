package hellocalendar.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration

public class WebSecurityConfig {

    private final UserDetailsService userService;
    @Bean
    public WebSecurityCustomizer configure(){

        return (web) -> web.ignoring()
                .requestMatchers("/static/**","/resources/**","/css/**", "/scripts/**", "/plugin/**", "/fonts/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests((authorizeRequests)->authorizeRequests .requestMatchers("/login", "/signup", "/user","/","/css/**","/scripts/**","/plugin/**","/fonts/**","/js/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin((formLogin)->formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/calendar")
                )
                .logout((logoutConfig)->logoutConfig
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                )
                .csrf((csrfConfig)->csrfConfig.disable()
                )
                .build();
    }
    @Bean
    public DefaultSecurityFilterChain authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService)
        throws Exception{
        http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
