package com.crossover.mymovie.common.entities

import javax.persistence.{Column, Entity, GeneratedValue, Id}

@Entity
class Movie {
  @Id
  @GeneratedValue
  var id: Long = _

  @Column(unique = true, nullable = false)
  var title: String = _

  var description: String = _
}
