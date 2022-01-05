package com.example.todo.domain

import com.example.common.support.jpa.BaseEntity
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Todo(
	@Column(nullable = false)
	var name: String,
	var isCompleted: Boolean? = null,

	var createdAt: OffsetDateTime = OffsetDateTime.now(),
	var updatedAt: OffsetDateTime = OffsetDateTime.now(),
	var completedAt: OffsetDateTime? = null,

	) : BaseEntity() {
	fun complete() {
		apply {
			isCompleted = true
			updatedAt = OffsetDateTime.now()
			completedAt = OffsetDateTime.now()
		}
	}

	fun failComplete() {
		apply {
			isCompleted = false
			updatedAt = OffsetDateTime.now()
		}
	}
}