package com.crossover.mymovie.anonymous.auth

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationRestServiceTest {

  @Autowired private val mvc: MockMvc = null
  @Autowired private val tokenService: TokenService = null

  // pre created user
  val email = "email@test.com"
  val password = "password123"

  @Test
  def authUser(): Unit = {
    // arrange
    val request = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}"

    // act
    val result = mvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON).content(request))
    val resultContent = result.andReturn().getResponse.getContentAsString
    val token = new ObjectMapper().readValue(resultContent, classOf[AuthenticationResponse]).token
    val userDetails = tokenService.validateToken(token)

    // assert
    result.andExpect(status().isOk)
    Assertions.assertThat(token).isNotNull()
    Assertions.assertThat(userDetails.getUsername).isEqualTo(email)
  }

  @Test
  def authUserWhichIsNotExist(): Unit = {

    val emailNotExist = "emailNotExist"
    val password = "password123"

    // arrange
    val request = "{\"email\":\"" + emailNotExist + "\",\"password\":\"" + password + "\"}"

    // act
    val result = mvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON).content(request))

    // assert
    result.andExpect(status().isBadRequest)
  }

}
