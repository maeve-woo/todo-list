package com.example.todo.application.service

import com.example.todo.Fixture
import com.example.todo.application.port.`in`.UpdateTodoCommand
import com.example.todo.application.port.out.LoadTodoPort
import com.example.todo.domain.Todo
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CompleteTodoServiceTest {
	@MockK
	lateinit var loadTodoPort: LoadTodoPort

	lateinit var completeTodoService: CompleteTodoService

	@BeforeEach
	fun setUp() {
		MockKAnnotations.init(this)
		completeTodoService = CompleteTodoService(loadTodoPort)
	}

	@Test
	fun `complete 성공`() {
		val todo = Fixture.givenTodoWithId(1L)
		val command = UpdateTodoCommand(todo.id!!, "Hello", true)

		every { loadTodoPort.loadTodo(todo.id!!) } returns todo

		completeTodoService.completeTodo(command)

		assertThat(todo.isCompleted).isTrue
		assertThat(todo.createdAt).isNotNull
		assertThat(todo.updatedAt).isNotNull
		assertThat(todo.updatedAt > todo.createdAt).isTrue
		assertThat(todo.completedAt).isNotNull
	}
	@Test
	fun `잘못된 요청 - 이름이 다름`() {
		val todo = Fixture.givenTodoWithId(1L)
		val command = UpdateTodoCommand(todo.id!!, "Help me", true)

		every { loadTodoPort.loadTodo(todo.id!!) } returns todo

		Assertions.assertThatThrownBy { completeTodoService.completeTodo(command) }
			.isInstanceOf(MissMatchNameException::class.java)
	}

	@Test
	fun `잘못된 요청 - 잘못된 메소드 호출`() {
		val todo = Fixture.givenTodoWithId(1L)
		val command = UpdateTodoCommand(todo.id!!, todo.name, false)

		every { loadTodoPort.loadTodo(todo.id!!) } returns todo

		Assertions.assertThatThrownBy { completeTodoService.completeTodo(command) }
			.isInstanceOf(IllegalArgumentException::class.java)
	}
}