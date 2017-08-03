package com.crossover.mymovie.common.entities

import java.util
import javax.persistence.{Column, Entity, FetchType, GeneratedValue, Id, ManyToMany}

@Entity
class User {

  @Id
  @GeneratedValue
  var id: Long = _

  @Column(unique = true, nullable = false)
  var email: String = _

  @Column(nullable = false)
  var password: String = _

  @Column(nullable = false)
  var enabled: Boolean = _

  @ManyToMany(fetch = FetchType.EAGER)
  var authorities: util.List[UserAuthority] = _
}
