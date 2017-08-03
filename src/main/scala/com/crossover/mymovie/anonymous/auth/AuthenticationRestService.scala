package com.crossover.mymovie.anonymous.auth

import javax.validation.Valid

import com.crossover.mymovie.security.{TokenDetails, TokenService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{PostMapping, RequestBody, RestController}

@RestController class AuthenticationRestService {

  @Autowired private val tokenService: TokenService = null

  @PostMapping(value = Array("auth"))
  def createAuthenticationToken(@Valid @RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity[AuthenticationResponse] = {
    val token = tokenService.createToken(TokenDetails(authenticationRequest.email, authenticationRequest.password))
    ResponseEntity.ok(AuthenticationResponse(token))
  }
}
