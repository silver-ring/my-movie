package com.crossover.mymovie.user.book

import javax.validation.Valid

import com.crossover.mymovie.common.entities.{Book, BookRepository, Movie, MovieRepository, User, UserRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{PutMapping, RequestBody, RestController}

@RestController
class BookRestService {

  @Autowired private val userRepository:UserRepository = null
  @Autowired private val movieRepository:MovieRepository = null
  @Autowired private val bookRepository:BookRepository= null

  @PutMapping(value = Array("/book"))
  def buyTicket(@Valid @RequestBody bookRequest: BookRequest): ResponseEntity[BookResponse] = {
    val user: User = getUser(bookRequest)
    val movie: Movie = getMovie(bookRequest)

    val book = saveBook(user, movie)
    ResponseEntity.ok(BookResponse(book.id.toString))
  }

  private def saveBook(user: User, movie: Movie) = {
    val book = new Book
    book.move = movie
    book.user = user
    bookRepository.save(book)
  }

  private def getMovie(bookRequest: BookRequest) = movieRepository.findOne(bookRequest.movieId)

  private def getUser(bookRequest: BookRequest) = userRepository.findByEmail(bookRequest.userEmail)

}
