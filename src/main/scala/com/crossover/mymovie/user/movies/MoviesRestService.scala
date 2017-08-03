package com.crossover.mymovie.user.movies

import com.crossover.mymovie.common.entities.{AuthorityName, Movie, MovieRepository, UserAuthority}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PutMapping, RequestMapping, RestController}
import java.util

import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured

import collection.JavaConverters._

@RestController
class MoviesRestService {

  @Autowired private val movieRepository : MovieRepository = null

  @GetMapping(value = Array ("/movie"))
  def listAllMovies : ResponseEntity[util.List[MovieResponse]] = {
    val movies = movieRepository.findAll()
    val moviesResponse = movies.asScala.map((movie) => MovieResponse(movie.id.toString, movie.title, movie.description)).asJava
    ResponseEntity.ok(moviesResponse)
  }

}
