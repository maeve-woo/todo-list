package com.example.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
	runApplication<TodoApplication>(*args)
}

@SpringBootApplication
class TodoApplication