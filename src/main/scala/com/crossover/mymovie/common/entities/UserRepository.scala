package com.crossover.mymovie.common.entities

import java.lang

import org.springframework.data.jpa.repository.JpaRepository

trait UserRepository extends JpaRepository[User, lang.Long] {
  def findByEmail(email : String) : User
  def existsByEmail(email : String) : Boolean
}
