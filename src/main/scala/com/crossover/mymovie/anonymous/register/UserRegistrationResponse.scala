package com.crossover.mymovie.anonymous.register

import scala.beans.BeanProperty

case class UserRegistrationResponse (@BeanProperty token: String) {
  def this() = this(null)
}