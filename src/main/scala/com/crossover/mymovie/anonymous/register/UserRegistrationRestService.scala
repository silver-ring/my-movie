package com.crossover.mymovie.anonymous.register

import javax.validation.Valid

import com.crossover.mymovie.common.entities.{UserAuthority, User, UserRepository}
import com.crossover.mymovie.security.{TokenDetails, TokenService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.{PutMapping, RequestBody, RestController}

@RestController class UserRegistrationRestService {

  @Autowired private val userRepository : UserRepository = null
  @Autowired private val tokenService : TokenService = null
  @Autowired private val bcryptPasswordEncoder : BCryptPasswordEncoder = null

  @PutMapping(value = Array("/registration"))
  def registerNewUser(@Valid @RequestBody userRegistrationRequest: UserRegistrationRequest) : ResponseEntity[UserRegistrationResponse] = {
    saveUser(userRegistrationRequest)
    val token = createToken(userRegistrationRequest)
    ResponseEntity.ok(UserRegistrationResponse(token))
  }

  private def saveUser(userRegistrationRequest: UserRegistrationRequest) = {
    val user = new User()
    user.email = userRegistrationRequest.email
    user.password = bcryptPasswordEncoder.encode(userRegistrationRequest.password)
    user.enabled = true
    user.authorities = UserAuthority.RoleUserAuthority.getAuthorities
    userRepository.save(user)
  }

  private def createToken(userRegistrationRequest: UserRegistrationRequest) = {
    tokenService.createToken(TokenDetails(userRegistrationRequest.email, userRegistrationRequest.password))
  }
}