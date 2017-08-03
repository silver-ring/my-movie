package com.crossover.mymovie.user.book

import java.text.SimpleDateFormat
import java.util.Date
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.springframework.stereotype.Component

@Component class CreditCardExpiryValidator extends ConstraintValidator[CreditCardExpiry, CreditCardInfo] {

  override def initialize(constraintAnnotation: CreditCardExpiry): Unit = {}

  override def isValid(creditCardInfo: CreditCardInfo, context: ConstraintValidatorContext): Boolean = {
    val month = creditCardInfo.expiryMonth
    val year = creditCardInfo.expiryYear
    try {
      val expiryDate = new SimpleDateFormat("MM/yyyy").parse(s"$month/$year")
      new Date().before(expiryDate)
    } catch {
      case exception: Exception => true
    }
  }

}
