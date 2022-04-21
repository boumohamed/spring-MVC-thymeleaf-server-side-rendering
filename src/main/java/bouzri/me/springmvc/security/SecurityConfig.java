package bouzri.me.springmvc.security;

import bouzri.me.springmvc.security.service.UserDeatilsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private DataSource dataSource;
    private UserDeatilsServiceImpl userDeatilsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        PasswordEncoder pe = passwordEncoder();
        /*
        System.out.println(pe.encode("bouzri"));
        auth.inMemoryAuthentication().withUser("bouzri").password(pe.encode("bouzri")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(pe.encode("bouzri")).roles("USER", "ADMIN");
         */
        /*
            auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username =? ")
                .authoritiesByUsernameQuery("select username as principal, role from user_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(pe);
         */

            auth.userDetailsService(userDeatilsService);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeHttpRequests().antMatchers("/").permitAll();
        http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
        http.authorizeHttpRequests().antMatchers("/user/**").hasRole("USER");
        http.authorizeHttpRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
