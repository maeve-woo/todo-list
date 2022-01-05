package com.example.todo.application.service

import com.example.todo.Fixture
import com.example.todo.application.port.`in`.UpdateTodoCommand
import com.example.todo.application.port.out.LoadTodoPort
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FailCompleteTodoServiceTest {
	@MockK
	lateinit var loadTodoPort: LoadTodoPort

	lateinit var failCompleteTodoService: FailCompleteTodoService

	@BeforeEach
	fun setUp() {
		MockKAnnotations.init(this)
		failCompleteTodoService = FailCompleteTodoService(loadTodoPort)
	}

	@Test
	fun `failComplete 성공`() {
		val todo = Fixture.givenTodoWithId(1L)
		val command = UpdateTodoCommand(todo.id!!, todo.name, false)

		every { loadTodoPort.loadTodo(todo.id!!) } returns todo

		failCompleteTodoService.failCompleteTodo(command)

		assertThat(todo.isCompleted).isFalse
		assertThat(todo.createdAt).isNotNull
		assertThat(todo.updatedAt).isNotNull
		assertThat(todo.updatedAt > todo.createdAt).isTrue
		assertThat(todo.completedAt).isNull()
	}

	@Test
	fun `잘못된 요청 - 이름이 다름`() {
		val todo = Fixture.givenTodoWithId(1L)
		val command = UpdateTodoCommand(todo.id!!, "Help me", false)

		every { loadTodoPort.loadTodo(todo.id!!) } returns todo

		assertThatThrownBy { failCompleteTodoService.failCompleteTodo(command) }
			.isInstanceOf(MissMatchNameException::class.java)
	}

	@Test
	fun `잘못된 요청 - 잘못된 메소드 호출`() {
		val todo = Fixture.givenTodoWithId(1L)
		val command = UpdateTodoCommand(todo.id!!, todo.name, true)

		every { loadTodoPort.loadTodo(todo.id!!) } returns todo

		assertThatThrownBy { failCompleteTodoService.failCompleteTodo(command) }
			.isInstanceOf(IllegalArgumentException::class.java)
	}
}