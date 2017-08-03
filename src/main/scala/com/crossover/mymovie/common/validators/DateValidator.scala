package com.crossover.mymovie.common.validators

import java.text.SimpleDateFormat
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.springframework.stereotype.Component


@Component class DateValidator extends ConstraintValidator[Date, String] {

  var pattern : String = null

  override def initialize(constraintAnnotation: Date): Unit = {
    pattern = constraintAnnotation.pattern()
  }

  override def isValid(date: String, context: ConstraintValidatorContext): Boolean = {
    try {
      val simpleDateFormat = new SimpleDateFormat(pattern)
      simpleDateFormat.setLenient(false)
      simpleDateFormat.parse(date)
      true
    } catch {
      case ex: Exception => false
    }
  }
}