package com.kelelas.restaurant.config;


import com.kelelas.restaurant.entity.RoleType;
import com.kelelas.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http ) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login", "/registration", "/")
                    .not().authenticated()
                    .antMatchers( "/admin/*", "/user/*").authenticated()
                    .antMatchers("/*.css", "/img/*.jpg").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").usernameParameter("email").defaultSuccessUrl("/afterlogin", true).permitAll()
                .and()
                    .logout().permitAll()
                .and().csrf().disable();
        ;
    }
    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
