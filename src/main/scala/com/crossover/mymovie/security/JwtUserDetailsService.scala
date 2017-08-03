package com.crossover.mymovie.security

import com.crossover.mymovie.common.entities.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
private class JwtUserDetailsService extends UserDetailsService {
  @Autowired private val userRepository : UserRepository = null

  override def loadUserByUsername(email: String): UserDetails = {
    val user = userRepository.findByEmail(email)
    if (user == null) throw new UsernameNotFoundException(raw"No user found with username $email.")
    else JwtUserFactory.create(user)
  }
}
