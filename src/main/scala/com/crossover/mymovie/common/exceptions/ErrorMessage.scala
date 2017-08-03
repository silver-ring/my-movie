package com.crossover.mymovie.common.exceptions

import scala.beans.BeanProperty

case class ErrorMessage(@BeanProperty target:String, @BeanProperty message:String)
