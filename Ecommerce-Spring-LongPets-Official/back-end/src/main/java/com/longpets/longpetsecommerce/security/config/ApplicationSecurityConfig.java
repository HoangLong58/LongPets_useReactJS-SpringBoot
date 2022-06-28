package com.longpets.longpetsecommerce.security.config;

import com.longpets.longpetsecommerce.data.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails hoangLongAdmin = User.builder()
                .username("hoanglong")
                .password(passwordEncoder.encode("123123"))
                .roles(("ADMIN"))   //ROLE_ADMIN
                .build();

        UserDetails lindaCustomer = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("123123a"))
                .roles("CUSTOMER")     //ROLE_CUSTOMER
                .build();

        return new InMemoryUserDetailsManager(
                hoangLongAdmin,
                lindaCustomer
        );
    }
}
