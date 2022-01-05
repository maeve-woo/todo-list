package com.example.todo.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TodoTest {
	lateinit var todo: Todo;

	@BeforeEach
	fun setUpTodo() {
		todo = Todo("Hello Todo")
	}

	@Test
	fun `Create Todo`() {
		Assertions.assertThat(todo.isCompleted).isNull()
		Assertions.assertThat(todo.createdAt).isNotNull
		Assertions.assertThat(todo.updatedAt).isNotNull
		Assertions.assertThat(todo.completedAt).isNull()
	}

	@Test
	fun `Todo update - complete`() {
		todo.complete()

		Assertions.assertThat(todo.isCompleted).isTrue
		Assertions.assertThat(todo.createdAt).isNotNull
		Assertions.assertThat(todo.updatedAt).isNotNull
		Assertions.assertThat(todo.updatedAt > todo.createdAt).isTrue
		Assertions.assertThat(todo.completedAt).isNotNull
	}

	@Test
	fun `Todo update - fail complete`() {
		todo.failComplete()

		Assertions.assertThat(todo.isCompleted).isFalse
		Assertions.assertThat(todo.createdAt).isNotNull
		Assertions.assertThat(todo.updatedAt).isNotNull
		Assertions.assertThat(todo.updatedAt > todo.createdAt).isTrue
		Assertions.assertThat(todo.completedAt).isNull()
	}
}