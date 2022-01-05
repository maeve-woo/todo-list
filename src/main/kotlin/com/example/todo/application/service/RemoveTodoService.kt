package com.example.todo.application.service

import com.example.todo.application.port.`in`.RemoveTodoUseCase
import com.example.todo.application.port.out.RemoveTodoPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RemoveTodoService(
	val removeTodoPort: RemoveTodoPort
) : RemoveTodoUseCase {
	override fun removeTodo(todoId: Long) {
		removeTodoPort.removeTodo(todoId)
	}
}