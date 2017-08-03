package com.crossover.mymovie.security

import java.util

import com.crossover.mymovie.common.entities.{UserAuthority, User}
import org.springframework.security.core.authority.SimpleGrantedAuthority

import scala.collection.JavaConverters._

private object JwtUserFactory {

  def create(user: User) = new JwtUser(user.id, user.email, user.password, mapToGrantedAuthorities(user.authorities), user.enabled)

  private def mapToGrantedAuthorities(authorities: util.List[UserAuthority]) = authorities.asScala.map((authority) => new SimpleGrantedAuthority(authority.name.toString)).asJava
}
