package com.crossover.mymovie.user.book

import scala.beans.BeanProperty

case class BookResponse(@BeanProperty id: String) {
  def this() = this(null)
}
