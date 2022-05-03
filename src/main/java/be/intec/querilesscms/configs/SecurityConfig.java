package be.intec.querilesscms.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    BCryptPasswordEncoder passwordEncoder;
    UserDetailsService userDetailsService;

    public SecurityConfig(BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/admin/**", "/delete/**", "/search-results/deleteBeer/{id}" ,
                        "/search-results/deleteBrewer/{id}", "/search-results/deleteCategory/{id}").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/edit-beer/{id}", "/edit-brewer/{id}", "/edit-category/{id}",
                        "/add-beer", "/add-brewer", "/add-category").hasAnyRole("ROLE_EDITOR", "ROLE_ADMIN")
                .antMatchers("/profile", "/search-results").hasAnyRole("ROLE_USER", "ROLE_ADMIN", "ROLE_EDITOR")
                .antMatchers("/", "/signup", "/css/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/profile")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}