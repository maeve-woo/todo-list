package com.example.todo.application.port.`in`

interface CompleteTodoUseCase {
	fun completeTodo(command: UpdateTodoCommand);
}