package com.crossover.mymovie.anonymous.auth

import com.crossover.mymovie.common.validators.UserExist
import org.hibernate.validator.constraints.NotBlank

import scala.beans.BeanProperty

private class AuthenticationRequest {
  @NotBlank
  @UserExist
  @BeanProperty var email: String = _
  @NotBlank
  @BeanProperty var password: String = _
}
