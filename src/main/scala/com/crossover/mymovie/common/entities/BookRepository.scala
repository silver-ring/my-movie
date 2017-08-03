package com.crossover.mymovie.common.entities

import org.springframework.data.jpa.repository.JpaRepository
import java.lang

trait BookRepository extends JpaRepository[Book, lang.Long] {

}
