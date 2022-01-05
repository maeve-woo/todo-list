package com.example.todo.application.service

import com.example.todo.adapter.out.persistence.SpringDataTodoRepository
import com.example.todo.application.port.`in`.RegisterTodoCommand
import com.ninjasquad.springmockk.clear
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class RemoveTodoServiceTest @Autowired constructor(
	val todoRepository: SpringDataTodoRepository,
	val registerTodoService: RegisterTodoService,
	val removeTodoService: RemoveTodoService
) {
	@Test
	fun `todo remove 성공`() {
		registerTodoService.registerTodo(RegisterTodoCommand("Hello", null))
		val beforeTodos = todoRepository.findAll()
		val todo = beforeTodos[0]

		Assertions.assertThat(beforeTodos).isNotEmpty
		removeTodoService.removeTodo(todo.id!!)

		val afterTodos = todoRepository.findAll()
		Assertions.assertThat(afterTodos).isEmpty()
	}
}