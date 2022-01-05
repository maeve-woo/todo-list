package com.example.todo.domain

import org.assertj.core.api.Assertions.assertThat
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
		assertThat(todo.isCompleted).isNull()
		assertThat(todo.createdAt).isNotNull
		assertThat(todo.updatedAt).isNotNull
		assertThat(todo.completedAt).isNull()
	}

	@Test
	fun `Todo update - complete`() {
		todo.complete()

		assertThat(todo.isCompleted).isTrue
		assertThat(todo.createdAt).isNotNull
		assertThat(todo.updatedAt).isNotNull
		assertThat(todo.updatedAt > todo.createdAt).isTrue
		assertThat(todo.completedAt).isNotNull
	}

	@Test
	fun `Todo update - fail complete`() {
		todo.failComplete()

		assertThat(todo.isCompleted).isFalse
		assertThat(todo.createdAt).isNotNull
		assertThat(todo.updatedAt).isNotNull
		assertThat(todo.updatedAt > todo.createdAt).isTrue
		assertThat(todo.completedAt).isNull()
	}
}