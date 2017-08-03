package com.crossover.mymovie.security

import java.util
import java.util.Date

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import com.fasterxml.jackson.annotation.JsonIgnore


private class JwtUser(val id: Long, val email: String, val password: String, val authorities: util.List[_ <: GrantedAuthority], val enabled: Boolean) extends UserDetails {
  @JsonIgnore def getId: Long = id

  override def getUsername: String = email

  def getEmail: String = email

  @JsonIgnore override def isAccountNonExpired = true

  @JsonIgnore override def isAccountNonLocked = true

  @JsonIgnore override def isCredentialsNonExpired = true

  @JsonIgnore override def getPassword: String = password

  override def getAuthorities: util.Collection[_ <: GrantedAuthority] = authorities

  override def isEnabled: Boolean = enabled
}
