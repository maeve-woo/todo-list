package com.example.todo.adapter.`in`.request

import javax.validation.constraints.Null

data class TodoRegisterReq(
	val name: String,
	@field: Null
	val completed: Boolean?
)