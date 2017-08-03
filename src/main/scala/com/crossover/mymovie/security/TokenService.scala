package com.crossover.mymovie.security

import com.crossover.mymovie.common.exceptions.InvalidTokenException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.{AuthenticationManager, UsernamePasswordAuthenticationToken}
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.{UserDetails, UserDetailsService}
import org.springframework.stereotype.Service

@Service
class TokenService {

  @Autowired private val jwtTokenUtil: JwtTokenUtil = null
  @Autowired private val authenticationManager: AuthenticationManager = null
  @Autowired private val userDetailsService: UserDetailsService = null

  def createToken(tokenDetails:TokenDetails) : String = {
    val authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tokenDetails.email, tokenDetails.password))
    SecurityContextHolder.getContext.setAuthentication(authentication)
    val userDetails = userDetailsService.loadUserByUsername(tokenDetails.email)
    jwtTokenUtil.generateToken(userDetails)
  }

  def validateToken(token : String) : UserDetails = {
    try {
      val email = jwtTokenUtil.getEmailFromToken(token)
      val userDetails = userDetailsService.loadUserByUsername(email)
      val isValid = jwtTokenUtil.validateToken(token, userDetails)
      if (!isValid) throw new InvalidTokenException()
      else userDetails
    } catch {
      case ex: Exception => throw new InvalidTokenException(throwable = ex)
    }
  }

}
