package com.example.todo.application.port.`in`

import com.example.todo.adapter.out.presenter.TodoRes

interface FindTodoUseCase {
	fun find(todoId: Long): TodoRes
}