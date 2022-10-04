package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Role(
    @Enumerated(EnumType.STRING)
    @Column(unique = true, length = 30)
    var roleType: RoleType,

    override var id: String? = null
): BaseEntity()
enum class RoleType{
    ROLE_ADMIN, ROLE_USER, ROLE_VENDOR
}