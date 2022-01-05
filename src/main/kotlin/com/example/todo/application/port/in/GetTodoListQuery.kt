package com.example.todo.application.port.`in`

import com.example.todo.adapter.out.presenter.TodoRes

interface GetTodoListQuery {
	fun getTodoList(): List<TodoRes>
}