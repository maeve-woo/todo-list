package com.example.todo.application.service

import com.example.todo.adapter.out.presenter.TodoRes
import com.example.todo.application.port.`in`.FindTodoUseCase
import com.example.todo.application.port.out.LoadTodoPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FindTodoService(
	val loadTodoPort: LoadTodoPort
) : FindTodoUseCase {
	override fun find(todoId: Long): TodoRes {
		return TodoRes.of(loadTodoPort.loadTodo(todoId))
	}
}