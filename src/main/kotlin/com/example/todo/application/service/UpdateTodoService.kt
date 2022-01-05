package com.example.todo.application.service

import com.example.todo.application.port.`in`.UpdateTodoCommand
import com.example.todo.application.port.`in`.UpdateTodoUseCase
import com.example.todo.application.port.out.LoadTodoPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateTodoService(
	val loadTodoPort: LoadTodoPort
) : UpdateTodoUseCase {
	override fun updateTodo(command: UpdateTodoCommand) {
		val todo = loadTodoPort.loadTodo(command.todoId)

		if (todo.name != command.name) {
			throw MissMatchNameException(todo.id!!, command.name, todo.name)
		}

		if (command.isComplete!!) {
			todo.complete()
		} else {
			todo.failComplete()
		}
	}
}