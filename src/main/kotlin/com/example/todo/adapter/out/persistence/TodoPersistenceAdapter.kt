package com.example.todo.adapter.out.persistence

import com.example.common.PersistenceAdapter
import com.example.todo.application.port.out.LoadTodoPort
import com.example.todo.application.port.out.RegisterTodoPort
import com.example.todo.application.port.out.RemoveTodoPort
import com.example.todo.domain.Todo

@PersistenceAdapter
class TodoPersistenceAdapter(
	val todoRepository: SpringDataTodoRepository

): LoadTodoPort, RegisterTodoPort, RemoveTodoPort {
	override fun loadTodo(todoId: Long): Todo {
		return todoRepository.findById(todoId).get()
	}

	override fun registerTodo(name: String) {
		todoRepository.save(Todo(name))
	}

	override fun removeTodo(todoId: Long) {
		todoRepository.deleteById(todoId)
	}
}