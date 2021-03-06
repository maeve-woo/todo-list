package com.example.todo.adapter.`in`.web

import com.example.common.extension.toFormatted
import com.example.todo.adapter.`in`.request.TodoRegisterReq
import com.example.todo.adapter.`in`.request.TodoUpdateReq
import com.example.todo.adapter.out.persistence.SpringDataTodoRepository
import com.example.todo.application.port.`in`.*
import com.example.todo.application.service.RegisterTodoService
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class TodoControllerTest @Autowired constructor(
	val todoRepository: SpringDataTodoRepository,
	val registerTodoService: RegisterTodoService
) {
	@Autowired
	lateinit var mockMvc: MockMvc

	@Autowired
	lateinit var wac: WebApplicationContext

	@Autowired
	lateinit var objectMapper: ObjectMapper


	@BeforeEach
	fun `UTF-8 설정 및 print 설정 뚱딱!`() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
			.addFilters<DefaultMockMvcBuilder?>(CharacterEncodingFilter("UTF-8", true))
			.alwaysDo<DefaultMockMvcBuilder?>(MockMvcResultHandlers.print())
			.build()
	}

	@Test
	fun `todos 조회`() {
		prepareTodos()
		val todosRes = mockMvc.get("/todos") {
			accept = MediaType.APPLICATION_JSON
		}.andExpect {
			status { isOk() }
			jsonPath("$.length()") { value(3) }
		}.andReturn()

		val todos = objectMapper.readValue(todosRes.response.contentAsString, List::class.java)
		assertThat(todos).extracting("name")
			.containsExactly("Hello1", "Hello2", "Hello3")
	}

	@Test
	fun `todo 조회`() {
		prepareTodos()
		val todo = todoRepository.findAll().first()
		val todoId = todo.id

		mockMvc.get("/todos/$todoId") {
			contentType = MediaType.APPLICATION_JSON
		}.andExpect {
			status { isOk() }
			jsonPath("$.id") { value(todo.id) }
			jsonPath("$.name") { value(todo.name) }
			jsonPath("$.createdAt") { value(todo.createdAt.toFormatted()) }
			jsonPath("$.completedAt") { value(todo.completedAt?.toFormatted()) }
			jsonPath("$.updatedAt") { value(todo.updatedAt.toFormatted()) }
		}
	}

	@Test
	fun `todo 등록`() {
		val beforeTodos = todoRepository.findAll()
		assertThat(beforeTodos).isEmpty()

		val req = objectMapper.writeValueAsString(TodoRegisterReq("Hello", null))

		mockMvc.post("/todos") {
			contentType = MediaType.APPLICATION_JSON
			content = req
		}.andExpect { status { isOk() } }

		val afterTodos = todoRepository.findAll()
		val todo = afterTodos[0]
		assertThat(afterTodos.size).isEqualTo(1)
		assertThat(todo.name).isEqualTo("Hello")
		assertThat(todo.isCompleted).isNull()
		assertThat(todo.completedAt).isNull()
		assertThat(todo.createdAt).isNotNull
		assertThat(todo.updatedAt).isNotNull
	}

	@Test
	fun `todo 등록 실패 - Req Validation`() {
		val req = objectMapper.writeValueAsString(TodoRegisterReq("Hello", true))

		mockMvc.post("/todos") {
			contentType = MediaType.APPLICATION_JSON
			content = req
		}.andExpect { status { isBadRequest() } }
	}

	@Test
	fun `todo 완료`() {
		prepareTodos()
		var todo = todoRepository.findAll().first()
		val todoId = todo.id
		val req = objectMapper.writeValueAsString(TodoUpdateReq(todo.name, true))

		mockMvc.put("/todos/$todoId") {
			contentType = MediaType.APPLICATION_JSON
			content = req
		}.andExpect { status { isOk() } }

		todo = todoRepository.findAll().first()

		assertThat(todo.isCompleted).isTrue
		assertThat(todo.completedAt).isNotNull
		assertThat(todo.createdAt).isNotNull
		assertThat(todo.updatedAt).isNotNull
	}


	@Test
	fun `todo 완료실패`() {
		prepareTodos()
		var todo = todoRepository.findAll().first()
		val todoId = todo.id
		val req = objectMapper.writeValueAsString(TodoUpdateReq(todo.name, false))

		mockMvc.put("/todos/$todoId") {
			contentType = MediaType.APPLICATION_JSON
			content = req
		}.andExpect {
			status { isOk() }
		}
		todo = todoRepository.findAll().first()

		assertThat(todo.isCompleted).isFalse
		assertThat(todo.completedAt).isNull()
		assertThat(todo.createdAt).isNotNull
		assertThat(todo.updatedAt).isNotNull
	}

	@Test
	fun `todo 삭제`() {
		prepareTodos()
		val todoId = todoRepository.findAll().first().id

		mockMvc.delete("/todos/$todoId") {
			contentType = MediaType.APPLICATION_JSON
		}.andExpect { status { isOk() } }

		assertThat(todoRepository.findByIdOrNull(todoId)).isNull()
	}

	private fun prepareTodos() {
		registerTodoService.registerTodo(RegisterTodoCommand("Hello1", null))
		registerTodoService.registerTodo(RegisterTodoCommand("Hello2", null))
		registerTodoService.registerTodo(RegisterTodoCommand("Hello3", null))
	}
}