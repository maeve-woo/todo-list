package com.example.todo.application.service

class MissMatchNameException(
	val todoId: Long,
	val name: String,
	val actualName: String
) : RuntimeException() {
	override val message: String?
		get() = "$todoId : ${name } is missMatch. actual name is $actualName"
}