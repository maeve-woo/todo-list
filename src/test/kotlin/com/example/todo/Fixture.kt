package com.example.todo

import com.example.todo.domain.Todo

class Fixture {
	companion object {
		fun givenTodoWithId(todoId: Long): Todo {
			val name = "Hello"
			return Todo(name).apply { id = todoId }
		}
	}
}