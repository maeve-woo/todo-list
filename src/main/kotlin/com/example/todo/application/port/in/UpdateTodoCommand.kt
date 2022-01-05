package com.example.todo.application.port.`in`

import org.springframework.util.Assert

class UpdateTodoCommand(
	val todoId: Long,
	val name: String,
	val isComplete: Boolean?
) {
	companion object {
		fun validMethodCall(isCompleteCommand: Boolean?, isCompleteMethod: Boolean?) {
			Assert.isTrue(isCompleteCommand == isCompleteMethod, "U Call isCompleteMethod : $isCompleteMethod Method With isCompleteCommand : $isCompleteCommand")
		}
	}
}