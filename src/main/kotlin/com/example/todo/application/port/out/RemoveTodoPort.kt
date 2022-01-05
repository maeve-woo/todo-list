package com.example.todo.application.port.out

interface RemoveTodoPort {
	fun removeTodo(todoId: Long)
}