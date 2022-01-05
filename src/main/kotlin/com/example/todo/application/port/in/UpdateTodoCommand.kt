package com.example.todo.application.port.`in`

import com.example.todo.adapter.`in`.request.TodoUpdateReq

class UpdateTodoCommand(
	val todoId: Long,
	val name: String,
	val isComplete: Boolean?
) {
	init {
		require(isComplete != null) { throw IllegalArgumentException("Register Todo's complete state is not null") }
	}

	companion object {
		fun of(id: Long, todoUpdateReq: TodoUpdateReq): UpdateTodoCommand {
			return with(todoUpdateReq) {
				UpdateTodoCommand(todoId = id, name, completed)
			}
		}
	}
}