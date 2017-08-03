package com.crossover.mymovie.common.entities

import javax.persistence.{Entity, GeneratedValue, Id, OneToOne}

@Entity
class Book {
    @Id
    @GeneratedValue
    var id : Long = _
    @OneToOne
    var user : User = _
    @OneToOne
    var move : Movie = _
}
