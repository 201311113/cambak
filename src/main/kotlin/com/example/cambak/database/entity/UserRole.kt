package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import javax.persistence.*


@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["user_id","role_id"])])
class UserRole (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "role_id")
    var role: Role
): BaseEntity()