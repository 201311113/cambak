package com.example.cambak.database

import com.example.cambak.cambak.common.util.CommonUtils
import org.apache.commons.lang3.builder.ToStringBuilder
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity: Serializable {
    @Id
    var id: String? = CommonUtils.uuid()

    @CreatedDate
    var created: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    var updated: LocalDateTime = LocalDateTime.now()

    @Column(columnDefinition = "boolean default true")
    var active: Boolean = true

    override fun toString(): String = ToStringBuilder.reflectionToString(this)

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false
        val otherEntity = (other as? BaseEntity) ?: return false
        return this.id == otherEntity.id
    }

    override fun hashCode(): Int {
        val prime = 59
        val result = 1

        return result * prime + (id.hashCode() ?: 43)
    }
}