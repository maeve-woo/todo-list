package com.example.todo.application.service

import com.example.todo.application.port.`in`.UpdateTodoCommand
import com.example.todo.domain.Todo

class Validation {
	companion object {
		fun validUpdateCommand(todo: Todo, command: UpdateTodoCommand, isCompleteMethod: Boolean?) {
			if (todo.name != command.name) {
				throw MissMatchNameException(todo.id!!, command.name, todo.name)
			}
			UpdateTodoCommand.validMethodCall(command.isComplete, isCompleteMethod)
		}
	}
}