package com.example.todo.application.service

import com.example.todo.application.port.`in`.FailCompleteTodoUseCase
import com.example.todo.application.port.`in`.UpdateTodoCommand
import com.example.todo.application.port.out.LoadTodoPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FailCompleteTodoService(
	val loadTodoPort: LoadTodoPort
) : FailCompleteTodoUseCase {
	override fun failCompleteTodo(command: UpdateTodoCommand) {
		val todo = loadTodoPort.loadTodo(command.todoId)

		Validation.validUpdateCommand(todo, command, false)

		todo.failComplete()
	}
}