package com.example.todo.adapter.`in`.web

import com.example.todo.adapter.`in`.request.TodoRegisterReq
import com.example.todo.adapter.`in`.request.TodoUpdateReq
import com.example.todo.adapter.out.presenter.TodoRes
import com.example.todo.application.port.`in`.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController(
	val registerTodoUseCase: RegisterTodoUseCase,
	val updateTodoUseCase: UpdateTodoUseCase,
	val removeTodoUseCase: RemoveTodoUseCase,
	val findTodoUseCase: FindTodoUseCase,
	val getTodoList: GetTodoListQuery
) {
	@GetMapping
	fun findAllTodos(): List<TodoRes> {
		return getTodoList.getTodoList()
	}

	@GetMapping("/{todoId}")
	fun findTodo(@PathVariable todoId: Long): TodoRes {
		return findTodoUseCase.find(todoId)
	}

	@PostMapping
	fun registerTodo(@RequestBody req: TodoRegisterReq) {
		registerTodoUseCase.registerTodo(RegisterTodoCommand.of(req))
	}

	@PutMapping("/{todoId}")
	fun updateTodo(@RequestBody req: TodoUpdateReq, @PathVariable todoId: Long) {
		updateTodoUseCase.updateTodo(UpdateTodoCommand.of(todoId, req))
	}

	@DeleteMapping("/{todoId}")
	fun removeTodo(@PathVariable todoId: Long) {
		removeTodoUseCase.removeTodo(todoId)
	}
}