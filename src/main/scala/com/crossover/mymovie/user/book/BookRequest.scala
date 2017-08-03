package com.crossover.mymovie.user.book

import javax.validation.Valid
import javax.validation.constraints.{Min, NotNull}

import com.crossover.mymovie.common.validators.{MovieExist, UserExist}
import org.hibernate.validator.constraints.NotBlank

import scala.beans.BeanProperty

private class BookRequest() {
  @Min(value = 1)
  @MovieExist
  @BeanProperty var movieId: Long = _
  @NotBlank
  @UserExist
  @BeanProperty var userEmail:String = _
  @NotNull
  @Valid
  @BeanProperty var creditCardInfo:CreditCardInfo = _
}
