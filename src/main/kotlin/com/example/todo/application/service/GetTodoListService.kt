package com.example.todo.application.service

import com.example.todo.adapter.out.persistence.SpringDataTodoRepository
import com.example.todo.application.port.`in`.GetTodoListQuery
import com.example.todo.domain.Todo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetTodoListService(
	val todoRepository: SpringDataTodoRepository
) : GetTodoListQuery {
	override fun getTodoList(): List<Todo> {
		return todoRepository.findAll()
	}
}