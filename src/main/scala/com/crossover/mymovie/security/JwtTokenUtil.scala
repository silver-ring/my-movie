package com.crossover.mymovie.security

import java.util
import java.util.Date

import io.jsonwebtoken.{Jwts, SignatureAlgorithm}
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

object JwtTokenUtil {
  private val CLAIM_KEY_EMAIL = "sub"
}

@Component
private class JwtTokenUtil {

  @Value("${jwt.secret}") private val secret : String = null
  @Value("${jwt.expiration}") private val expiration : Long = 0L

  def getEmailFromToken(token: String) = getClaimsFromToken(token).getSubject()

  private def getClaimsFromToken(token: String) = Jwts.parser.setSigningKey(secret).parseClaimsJws(token).getBody()


  def generateToken(userDetails: UserDetails): String = {
    val claims = new util.HashMap[String, AnyRef]
    claims.put(JwtTokenUtil.CLAIM_KEY_EMAIL, userDetails.getUsername)
    doGenerateToken(claims)
  }

  private def doGenerateToken(claims: util.Map[String, AnyRef]) = {
    val now = new Date().getTime
    val expirationDate = new Date(now + expiration * 1000)
    Jwts.builder.setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact
  }

  def validateToken(token: String, userDetails: UserDetails) = {
    val user = userDetails.asInstanceOf[JwtUser]
    val email = getEmailFromToken(token)
    email == user.getEmail && !isTokenExpired(token)
  }

  private def isTokenExpired(token: String) = getExpirationDateFromToken(token).before(new Date())

  private def getExpirationDateFromToken(token: String) =  getClaimsFromToken(token).getExpiration()

}