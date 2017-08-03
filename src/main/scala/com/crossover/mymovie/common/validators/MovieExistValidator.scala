package com.crossover.mymovie.common.validators

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import com.crossover.mymovie.common.entities.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component class MovieExistValidator extends ConstraintValidator[MovieExist, Long] {

  @Autowired private val movieRepository: MovieRepository = null

  override def initialize(constraintAnnotation: MovieExist): Unit = {}

  override def isValid(id: Long, context: ConstraintValidatorContext): Boolean = movieRepository.exists(id)
}

