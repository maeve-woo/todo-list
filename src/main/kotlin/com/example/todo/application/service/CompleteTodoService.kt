package com.example.todo.application.service

import com.example.todo.application.port.`in`.CompleteTodoUseCase
import com.example.todo.application.port.`in`.UpdateTodoCommand
import com.example.todo.application.port.out.LoadTodoPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CompleteTodoService(
	val loadTodoPort: LoadTodoPort
) : CompleteTodoUseCase {
	override fun completeTodo(command: UpdateTodoCommand) {
		val todo = loadTodoPort.loadTodo(command.todoId)

		Validation.validUpdateCommand(todo, command, true)

		todo.complete()
	}
}