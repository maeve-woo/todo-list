package com.example.todo.application.port.out

import com.example.todo.domain.Todo

interface LoadTodoPort {
	fun loadTodo(todoId: Long): Todo
}