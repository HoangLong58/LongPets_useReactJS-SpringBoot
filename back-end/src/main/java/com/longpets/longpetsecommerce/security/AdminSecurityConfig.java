package com.longpets.longpetsecommerce.security;

import com.longpets.longpetsecommerce.filter.AdminCustomAuthenticationFilter;
import com.longpets.longpetsecommerce.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminSecurityConfig(@Autowired @Qualifier("EmployeeServiceImpl") UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AdminCustomAuthenticationFilter adminCustomAuthenticationFilter
                = new AdminCustomAuthenticationFilter(authenticationManagerBean());
        adminCustomAuthenticationFilter.setFilterProcessesUrl("/admin/login-admin");
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        http.authorizeRequests().antMatchers("/login-customer/**", "/customer/register", "/customer/token/refresh/**").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/customer/user/**").hasAuthority("ROLE_CUSTOMER");
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/customer/user/save/**").hasAuthority("ROLE_ADMIN");
//        http.authorizeRequests().anyRequest().authenticated();
//        http.authorizeRequests().anyRequest().permitAll();

        http.requestMatcher(new AntPathRequestMatcher("/admin/login-admin"))
                .authorizeRequests()
//                .anyRequest()
                .antMatchers("/admin*")
                .permitAll();
        http.addFilter(adminCustomAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean(name = "authenticationManagerBeanAdmin")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
