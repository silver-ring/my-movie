package com.crossover.mymovie.user.book

import com.crossover.mymovie.common.validators.Date
import org.hibernate.validator.constraints.{CreditCardNumber, NotBlank}

import scala.beans.BeanProperty

@CreditCardExpiry
class CreditCardInfo() {
  @NotBlank
  @BeanProperty var name: String = _
  @CreditCardNumber
  @BeanProperty var number: String = _
  @NotBlank
  @BeanProperty var cvv: String = _
  @NotBlank
  @Date(pattern = "MM", message = "month should be use 'MM' format")
  @BeanProperty var expiryMonth: String = _
  @NotBlank
  @Date(pattern = "yyyy", message = "year should be use 'YY' format")
  @BeanProperty var expiryYear: String = _
}
