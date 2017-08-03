package com.crossover.mymovie.security

import javax.servlet.FilterChain
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
private class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
  @Autowired val tokenService: TokenService = null
  @Value("${jwt.header}") private val tokenHeader: String = null

  override protected def doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain): Unit = {
    if (SecurityContextHolder.getContext.getAuthentication == null) {
      val authToken = request.getHeader(tokenHeader)
      if (authToken != null) {
        val userDetails = tokenService.validateToken(authToken)
        val authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities)
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request))
        SecurityContextHolder.getContext.setAuthentication(authentication)
      }
    }

    chain.doFilter(request, response)
  }

}