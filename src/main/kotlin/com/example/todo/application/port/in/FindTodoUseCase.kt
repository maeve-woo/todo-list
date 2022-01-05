package com.example.todo.application.port.`in`

import com.example.todo.domain.Todo

interface FindTodoUseCase {
	fun find(todoId: Long): Todo
}