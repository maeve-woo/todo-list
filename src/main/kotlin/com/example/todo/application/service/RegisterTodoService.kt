package com.example.todo.application.service

import com.example.todo.application.port.`in`.RegisterTodoCommand
import com.example.todo.application.port.`in`.RegisterTodoUseCase
import com.example.todo.application.port.out.RegisterTodoPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterTodoService(
	val registerTodoPort: RegisterTodoPort
) : RegisterTodoUseCase {
	override fun registerTodo(command: RegisterTodoCommand) {
		registerTodoPort.registerTodo(command.name)
	}
}