package com.crossover.mymovie.common.validators

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import com.crossover.mymovie.common.entities.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component class UserExistValidator extends ConstraintValidator[UserExist, String] {

  @Autowired private val userRepository : UserRepository = null

  override def initialize(constraintAnnotation: UserExist): Unit = {
  }

  override def isValid(email: String, context: ConstraintValidatorContext): Boolean = userRepository.existsByEmail(email)
}
