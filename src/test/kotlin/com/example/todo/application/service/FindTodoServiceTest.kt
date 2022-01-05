package com.example.todo.application.service

import com.example.todo.adapter.out.persistence.SpringDataTodoRepository
import com.example.todo.application.port.`in`.RegisterTodoCommand
import org.assertj.core.api.Assertions
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class FindTodoServiceTest @Autowired constructor(
	val todoRepository: SpringDataTodoRepository,
	val registerTodoService: RegisterTodoService,
	val findTodoService: FindTodoService
) {
	@Test
	fun `todo regist 성공`() {
		registerTodoService.registerTodo(RegisterTodoCommand("Hello", null))

		val todos = todoRepository.findAll()
		val todo = todos[0]

		val findTodo = findTodoService.find(todo.id!!)
		assertThat(findTodo.name).isEqualTo("Hello")
	}
}