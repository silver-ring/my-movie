package com.crossover.mymovie.anonymous.auth

import scala.beans.BeanProperty

case class AuthenticationResponse(@BeanProperty token: String) {
  def this() = this(null)
}
