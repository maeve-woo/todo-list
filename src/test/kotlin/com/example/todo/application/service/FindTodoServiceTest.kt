package com.example.todo.application.service

import com.example.todo.adapter.out.persistence.SpringDataTodoRepository
import com.example.todo.application.port.`in`.RegisterTodoCommand
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import javax.transaction.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class FindTodoServiceTest(
	val todoRepository: SpringDataTodoRepository,
	val registerTodoService: RegisterTodoService,
	val findTodoService: FindTodoService
) {
	@Test
	fun `todo regist 성공`() {
		registerTodoService.registerTodo(RegisterTodoCommand("Hello", null))

		val todos = todoRepository.findAll()
		val todo = todos[0]

		val todoRes = findTodoService.find(todo.id!!)
		assertThat(todoRes.name).isEqualTo("Hello")
	}
}