package com.example.todo.application.port.`in`

import com.example.todo.domain.Todo

interface GetTodoListQuery {
	fun getTodoList(): List<Todo>
}