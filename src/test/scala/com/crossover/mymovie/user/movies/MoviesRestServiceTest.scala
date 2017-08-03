package com.crossover.mymovie.user.movies

import java.util

import com.crossover.mymovie.common.entities.{BookRepository, MovieRepository, UserRepository}
import com.crossover.mymovie.security.{TokenDetails, TokenService}
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class MoviesRestServiceTest {


  @Autowired private val mvc: MockMvc = null
  @Autowired private val movieRepository: MovieRepository = null

  @Value("${jwt.header}") private val tokenHeader: String = null

  @Autowired private val tokenService: TokenService = null

  // pre created user
  val email = "email@test.com"
  val password = "password123"

  // pre created movie id
  val movieId = 1


  @Test
  def getAllAvailableMovies(): Unit = {

    val token = tokenService.createToken(TokenDetails(email, password))

    // act
    val result = mvc.perform(get("/movie").header(tokenHeader, token))
    result.andExpect(status().isOk)
    val resultContent = result.andReturn().getResponse.getContentAsString
    val response: util.List[MovieResponse] = new ObjectMapper().readValue(resultContent, new TypeReference[util.List[MovieResponse]]() {})

    // assert
    Assertions.assertThat(movieRepository.findAll().size()).isEqualTo(response.size())
  }

}
