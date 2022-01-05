package com.example.todo.adapter.out.persistence

import com.example.common.PersistenceAdapter
import com.example.todo.application.port.out.LoadTodoPort
import com.example.todo.domain.Todo

@PersistenceAdapter
class TodoPersistenceAdapter(
	val todoRepository: SpringDataTodoRepository

): LoadTodoPort {
	override fun loadTodo(todoId: Long): Todo {
		return todoRepository.findById(todoId).get()
	}
}