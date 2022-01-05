package com.example.todo.adapter.out

import com.example.todo.domain.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>