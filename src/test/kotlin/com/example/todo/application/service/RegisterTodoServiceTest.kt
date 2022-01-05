package com.example.todo.application.service

import com.example.todo.adapter.out.persistence.SpringDataTodoRepository
import com.example.todo.application.port.`in`.RegisterTodoCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class RegisterTodoServiceTest @Autowired constructor(
	val todoRepository: SpringDataTodoRepository,
	val registerTodoService: RegisterTodoService
) {
	@Test
	fun `todo regist 성공`() {
		assertThat(todoRepository.findAll()).isEmpty()

		val command = RegisterTodoCommand("Hello", null)

		registerTodoService.registerTodo(command)

		val todos = todoRepository.findAll()
		assertThat(todos).isNotEmpty
		assertThat(todos[0].name).isEqualTo("Hello")
	}
}