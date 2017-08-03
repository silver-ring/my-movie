package com.crossover.mymovie.anonymous.register

import com.crossover.mymovie.common.validators.UniqueEmail
import org.hibernate.validator.constraints.{Email, NotBlank}

import scala.beans.BeanProperty

private class UserRegistrationRequest() {
  @NotBlank
  @Email
  @UniqueEmail
  @BeanProperty var email : String = _
  @NotBlank
  @BeanProperty var password : String = _
}
