package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "users")
class User (
    @Column(length = 200)
    val email: String,
    @Column(length = 200)
    val password: String ?= null,
    @Column(length = 50)
    var mobileNo: String? = null,
    @Column(length = 200)
    val nickname: String ?= null,
    val isVendor: Boolean = false


    ):BaseEntity()