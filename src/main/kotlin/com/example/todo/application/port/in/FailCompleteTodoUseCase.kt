package com.example.todo.application.port.`in`

interface FailCompleteTodoUseCase {
	fun failCompleteTodo(command: UpdateTodoCommand);
}