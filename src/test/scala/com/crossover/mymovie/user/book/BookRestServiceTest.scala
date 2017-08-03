package com.crossover.mymovie.user.book

import com.crossover.mymovie.common.entities.BookRepository
import com.crossover.mymovie.security.{TokenDetails, TokenService}
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.{Autowired, Value}
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
class BookRestServiceTest {

  @Autowired private val mvc: MockMvc = null
  @Autowired private val bookRepository: BookRepository = null

  @Value("${jwt.header}") private val tokenHeader: String = null

  @Autowired private val tokenService: TokenService = null

  // pre created user
  val email = "email@test.com"
  val password = "password123"

  // pre created movie id
  val movieId = 1


  @Test
  def bookMovie(): Unit = {

    val token = tokenService.createToken(TokenDetails(email, password))

    // arrange
    val name = "user full name"
    val creditCardNumber = "343373602357474"
    val cvv = "123"
    val expiryMonth = "12"
    val expiryYear = "2022"
    val request = "{\"movieId\":\"" + movieId + "\",\"userEmail\":\"" + email + "\",\"creditCardInfo\":{\"name\":\"" + name + "\",\"number\":\"" + creditCardNumber + "\",\"cvv\":\"" + cvv + "\",\"expiryMonth\":\"" + expiryMonth + "\",\"expiryYear\":\"" + expiryYear + "\"}}"

    // act
    val result = mvc.perform(put("/book").contentType(MediaType.APPLICATION_JSON).header(tokenHeader, token).content(request))
    val resultContent = result.andReturn().getResponse.getContentAsString
    val response = new ObjectMapper().readValue(resultContent, classOf[BookResponse]).id

    // assert
    result.andExpect(status().isOk)
    Assertions.assertThat(bookRepository.findOne(response.toLong)).isNotNull
    Assertions.assertThat(response).isNotNull()
  }

  @Test
  def bookMovieWithInvalidCreditCard(): Unit = {
    val token = tokenService.createToken(TokenDetails(email, password))
    // arrange
    val name = "user full name"
    val invalidCreditCardNumber = "343983602329382"
    val cvv = "123"
    val expiryMonth = "12"
    val expiryYear = "2022"
    val request = "{\"movieId\":\"" + movieId + "\",\"userEmail\":\"" + email + "\",\"creditCardInfo\":{\"name\":\"" + name + "\",\"number\":\"" + invalidCreditCardNumber + "\",\"cvv\":\"" + cvv + "\",\"expiryMonth\":\"" + expiryMonth + "\",\"expiryYear\":\"" + expiryYear + "\"}}"

    // act
    val result = mvc.perform(put("/book").contentType(MediaType.APPLICATION_JSON).header(tokenHeader, token).content(request))

    // assert
    result.andExpect(status().isBadRequest)
  }

}
