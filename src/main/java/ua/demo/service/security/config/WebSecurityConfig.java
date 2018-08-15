package ua.demo.service.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ua.demo.service.security.filters.TokenAuthFilter;


@ComponentScan("ua.demo")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private TokenAuthFilter tokenAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .antMatcher("/**")
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/buy/**").hasAuthority("USER")
                .antMatchers("/addBook/**").hasAuthority("ADMIN")
                .antMatchers("/list/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/logout/**").hasAnyAuthority("USER","ADMIN");
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
