package com.example.todo.adapter.out.presenter

import com.example.common.extension.toFormatted
import com.example.todo.domain.Todo

data class TodoRes(
	val id: Long,
	val name: String,
	val completed: Boolean?,
	val createdAt: String,
	val completedAt: String?,
	val updatedAt: String
) {
	companion object {
		fun of(todo: Todo): TodoRes {
			return with(todo) {
				TodoRes(
					id!!,
					name,
					completed = isCompleted,
					createdAt = createdAt.toFormatted(),
					completedAt = completedAt?.toFormatted(),
					updatedAt = updatedAt.toFormatted()
				)
			}
		}
	}
}