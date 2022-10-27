package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "users")
class User (

    @Column(length = 200)
    val userId: String ?= null,
    @Column(length = 200)
    var email: String? = null,

    @Column(length = 200)
    var password: String ?= null,
    @Column(length = 50)
    var mobileNo: String? = null,
    @Column(length = 200)
    var nickname: String ?= null,
    var isVendor: Boolean = false,
    var profileImageUrl: String ?= null,
    var bio: String ?= null,
    var instagram: String ?= null,
    var youtube: String ?= null,
    var blog: String ?= null,
    @Enumerated(value = EnumType.STRING)
    var signInType: SignInType,
    var credential: String ?= null,
    val snsToken: String ?= null,


    ):BaseEntity()

enum class SignInType{
    KAKAO, APPLE, EMAIL
}