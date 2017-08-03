package com.crossover.mymovie.common.exceptions

import org.springframework.security.core.AuthenticationException

class InvalidTokenException(val message : String = "invalid token", throwable: Throwable = null) extends AuthenticationException(message, throwable) {

}
