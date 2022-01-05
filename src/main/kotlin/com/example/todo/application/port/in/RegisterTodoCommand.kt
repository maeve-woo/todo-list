package com.example.todo.application.port.`in`

import com.example.todo.adapter.`in`.request.TodoRegisterReq
import com.example.todo.adapter.`in`.request.TodoUpdateReq

class RegisterTodoCommand(
	val name: String,
	val isComplete: Boolean? = null
) {
	init {
		require(isComplete == null) { throw IllegalArgumentException("Register Todo's complete state is null") }
	}

	companion object {
		fun of(todoRegisterReq: TodoRegisterReq): RegisterTodoCommand {
			return with(todoRegisterReq) {
				RegisterTodoCommand(name, completed)
			}
		}
	}
}