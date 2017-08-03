package com.crossover.mymovie.common.entities

import java.lang

import org.springframework.data.jpa.repository.JpaRepository

trait MovieRepository extends JpaRepository[Movie, lang.Long] {

}
