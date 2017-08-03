package com.crossover.mymovie.common.entities

import java.util
import javax.persistence.{Column, Entity, EnumType, Enumerated, FetchType, GeneratedValue, Id, ManyToMany}

object UserAuthority {

  object RoleUserAuthority {

    val ID = 1
    val NAME =  "ROLE_USER"

    def getAuthorities : util.List[UserAuthority] = {
      val authorities = new util.ArrayList[UserAuthority]
      val authority = new UserAuthority
      authority.id = ID
      authority.name = AuthorityName.ROLE_USER
      authorities
    }
  }

}

@Entity
class UserAuthority {
  @Id
  @GeneratedValue
  var id : Long = _

  @Column(unique = true, nullable = false)
  @Enumerated(EnumType.STRING)
  var name : AuthorityName = _

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
  var users : util.List[User] = _
}