package com.example.common.support.jpa

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity(
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null

) : Serializable {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is BaseEntity) return false

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id?.hashCode() ?: 0
	}
}