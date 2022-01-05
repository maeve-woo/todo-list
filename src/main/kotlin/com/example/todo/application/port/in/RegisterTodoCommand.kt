package com.example.todo.application.port.`in`

class RegisterTodoCommand(
	val name: String,
	val isComplete: Boolean? = null
) {
	init {
		require(isComplete == null) { throw IllegalArgumentException("Register Todo's complete state is null") }
	}
}