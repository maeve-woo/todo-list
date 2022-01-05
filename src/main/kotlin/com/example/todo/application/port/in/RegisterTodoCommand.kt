package com.example.todo.application.port.`in`

import com.example.todo.adapter.`in`.request.TodoRegisterReq

class RegisterTodoCommand(
	val name: String,
	val isComplete: Boolean? = null
) {
	companion object {
		fun of(todoRegisterReq: TodoRegisterReq): RegisterTodoCommand {
			return with(todoRegisterReq) {
				RegisterTodoCommand(name, completed)
			}
		}
	}
}