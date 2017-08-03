package com.crossover.mymovie.user.movies

import scala.beans.BeanProperty

case class MovieResponse(@BeanProperty id:String, @BeanProperty name:String, @BeanProperty description:String) {
  def this() = this(null, null, null)
}
