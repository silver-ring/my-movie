package com.crossover.mymovie.anonymous.register

import com.crossover.mymovie.common.entities.UserRepository
import com.crossover.mymovie.security.TokenService
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationRestServiceTest {

  @Autowired private val mvc: MockMvc = null
  @Autowired private val userRepository: UserRepository = null
  @Autowired private val tokenService: TokenService = null

  // pre created user
  val email = "email@test.com"
  val password = "password123"

  @Test
  def registerNewUser(): Unit = {

    val newEmail = "newEmail@test.com"
    val newPassword = "newPassword123"

    // arrange
    val request = "{\"email\":\"" + newEmail + "\",\"password\":\"" + newPassword + "\"}"

    // act
    val result = mvc.perform(put("/registration").contentType(MediaType.APPLICATION_JSON).content(request))
    val resultContent = result.andReturn().getResponse.getContentAsString
    val token = new ObjectMapper().readValue(resultContent, classOf[UserRegistrationResponse]).token
    val userDetails = tokenService.validateToken(token)

    // assert
    result.andExpect(status().isOk)
    val user = userRepository.findByEmail(newEmail)
    Assertions.assertThat(user).isNotNull()
    Assertions.assertThat(token).isNotNull()
    Assertions.assertThat(userDetails.getUsername).isEqualTo(user.email)
  }

  @Test
  def registerExistUser(): Unit = {

    // arrange
    val request = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}"

    // act
    val result = mvc.perform(put("/registration").contentType(MediaType.APPLICATION_JSON).content(request))

    // assert
    result.andExpect(status().isBadRequest)
  }
}
