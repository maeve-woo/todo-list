package com.example.todo.application.service

import com.example.todo.adapter.out.persistence.SpringDataTodoRepository
import com.example.todo.application.port.`in`.RegisterTodoCommand
import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
internal class GetTodoListServiceTest @Autowired constructor(
	val registerTodoService: RegisterTodoService,
	val getTodoListService: GetTodoListService
) {
	@Test
	fun `todos 조회`() {
		registerTodoService.registerTodo(RegisterTodoCommand("Hello1", null))
		registerTodoService.registerTodo(RegisterTodoCommand("Hello2", null))
		registerTodoService.registerTodo(RegisterTodoCommand("Hello3", null))

		val todos = getTodoListService.getTodoList()
		assertThat(todos.size).isEqualTo(3)
	}
}