package shop.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.config.DBconfig;
import shop.config.WebMvcConfig;
import shop.service.UserServiceInterface;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@ComponentScan(basePackages = "shop")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private  final PasswordEncoder passwordEncoder;
    private final UserServiceInterface userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//        .passwordEncoder(passwordEncoder())
//        .withUser("user").password(passwordEncoder().encode("123456")).roles("USER")
//        .and()
//        .withUser("admin").password(passwordEncoder().encode("123456")).roles("USER", "ADMIN");
        auth.authenticationProvider(authenticationProvider());
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/login")
            .permitAll()
        .antMatchers("/account/**")
            .hasAnyRole("MANAGER", "CUSTOMER")
        .antMatchers("/account")
                .hasAnyRole("MANAGER", "CUSTOMER")
        .antMatchers("/manage")
//                .permitAll()
                .hasAnyRole("MANAGER")
        .antMatchers("/manage/**")
//                .permitAll()
                .hasAnyRole("MANAGER")
        .antMatchers("/products/create")
             .hasAnyRole("MANAGER")
        .antMatchers("/client")
            .permitAll()
        .and()
            .exceptionHandling().accessDeniedPage("/403")
        .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/customer/after-login", true)
            .failureUrl("/login?error=true")
            .permitAll()
        .and()
            .logout()
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .permitAll()
        .and()
            .csrf()
            .disable();
    }
}
