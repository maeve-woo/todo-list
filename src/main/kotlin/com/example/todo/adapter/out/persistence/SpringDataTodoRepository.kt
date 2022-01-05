package com.example.todo.adapter.out.persistence

import com.example.todo.domain.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataTodoRepository : JpaRepository<Todo, Long>