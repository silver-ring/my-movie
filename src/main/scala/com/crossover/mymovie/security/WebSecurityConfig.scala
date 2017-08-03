package com.crossover.mymovie.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private val jwtUserDetailsService : JwtUserDetailsService = null
  @Autowired private val authenticationTokenFilterBean : JwtAuthenticationTokenFilter =  null

  @Autowired
  def configureAuthentication(authenticationManagerBuilder: AuthenticationManagerBuilder): Unit = {
    authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder)
  }

  override protected def configure(httpSecurity: HttpSecurity): Unit = {
    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().csrf.disable.antMatcher("/**").authorizeRequests().antMatchers("/auth", "/registration").permitAll().anyRequest().authenticated()
    httpSecurity.addFilterBefore(authenticationTokenFilterBean, classOf[UsernamePasswordAuthenticationFilter])
  }

  @Bean def passwordEncoder = new BCryptPasswordEncoder
}